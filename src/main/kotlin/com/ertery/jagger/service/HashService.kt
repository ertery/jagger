package com.ertery.jagger.service

import com.ertery.jagger.entity.Task
import com.ertery.jagger.entity.User
import com.ertery.jagger.enum.AlgoType
import com.ertery.jagger.enum.Status
import com.ertery.jagger.util.FileUtil
import org.springframework.stereotype.Service
import java.io.File
import org.apache.commons.codec.digest.DigestUtils
import org.springframework.transaction.annotation.Transactional

import java.lang.Thread.sleep
import java.util.concurrent.*
import java.net.MalformedURLException
import java.net.URISyntaxException
import java.net.URL


@Service
class HashService(val taskService: TaskService) {

    private val executor: ExecutorService = Executors.newCachedThreadPool()
    private val statusExecutor: ExecutorService = Executors.newCachedThreadPool()
    private val runningTasks: HashMap<Task, Future<String>> = HashMap()


    fun startProcess(algo: AlgoType, filePath: String){
        var file: File
/*        if (FileUtil.isURL(filePath)) file = FileUtil.uploadFile(filePath, "test", )?.get()!!*/
    }


    fun stopProcess(task: Task){
        val future = runningTasks[task]
        future?.cancel(true)
    }

    @Transactional
    fun processStatusCheck(checker: Pair<Task, Future<String>>) {
        val future = checker.second
        val task = checker.first
        statusExecutor.submit({
            do {
                if (future.isCancelled) {
                    taskService.updateStatus(taskId = task.taskId, status = Status.CANCELED)
                    taskService.finishTask(task.taskId)
                }
            } while (!future.isDone)

            sleep(2000)

            if (future.isDone) {
                taskService.updateStatus(taskId = task.taskId, status = Status.FINISHED)
                taskService.finishTask(taskId = task.taskId)
                taskService.setResult(taskId = task.taskId, result = future.get())
                runningTasks.remove(task)
            }

        })
    }

    @Transactional
    fun createProcess(algo: AlgoType, file: File, owner: User): Pair<Task, Future<String>> {
        val task = taskService.createTaskWithOwner(algo, owner)

        val future = executor.submit(Callable<String> {
            taskService.updateStatus(task.taskId, status = Status.INPROGRESS)
            getSHA(algo, file)
        })
        runningTasks.put(task, future)
        return Pair(task, future)
    }

    fun getSHA(alg: AlgoType, file: File): String = when (alg) {
        AlgoType.MD5 -> DigestUtils.md5Hex(file.inputStream())
        AlgoType.SHA256 -> DigestUtils.sha256Hex(file.inputStream())
        AlgoType.SHA1 -> DigestUtils.shaHex(file.inputStream())
    }


}
