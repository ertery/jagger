package com.ertery.jagger.util

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.io.File
import java.net.URL


@RunWith(SpringRunner::class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
internal class FileUtilTest{

    @Test
    fun downLoadFile(){
        FileUtil.uploadFile("http://httpbin.org/bytes/32768", "hello.tmp", File("/tmp"))
        Thread.sleep(5000)
    }


    @Test
    fun prepareFile(){
        FileUtil.prepareFile("http://httpbin.org/bytes/32768")
        Thread.sleep(5000)
    }
}