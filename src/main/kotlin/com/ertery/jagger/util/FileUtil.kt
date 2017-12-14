package com.ertery.jagger.util

import com.github.kittinunf.fuel.Fuel
import java.io.File
import java.net.MalformedURLException
import java.net.URISyntaxException
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.ThreadLocalRandom

open class FileUtil {

    companion object {

        private val executor = Executors.newFixedThreadPool(5)

        fun prepareFile(url: String): File? {
            val parcedUrl = URL(url)
            val name = parcedUrl.file + ThreadLocalRandom.current().nextInt(0, 100)
            val dir = File("tmp/")
            if (!dir.exists()) dir.mkdir()
            val fileFeature = uploadFile(url, name, dir)
            while (!fileFeature?.isDone!!) Thread.sleep(200)
            return fileFeature.get();
        }

        fun uploadFile(ur1: String, name: String, dir: File): Future<File>? {
            return executor.submit(Callable<File> {
                Fuel.download(ur1).destination {response, url ->
                    File(dir, name)
                }.response { req, res, result ->
                    println(res)
                }
                File(dir, name)
            })
        }

        fun isURL(url: String): Boolean {
            return try {
                URL(url).toURI()
                true
            } catch (exception: URISyntaxException) {
                false
            } catch (exception: MalformedURLException) {
                false
            }

        }

    }
}