package hu.bme.aut.onlab.couchsurfing_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CouchsurfingBackendApplication

fun main(args: Array<String>) {
    runApplication<CouchsurfingBackendApplication>(*args)
}
