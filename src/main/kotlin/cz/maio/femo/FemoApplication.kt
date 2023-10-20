package cz.maio.femo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FemoApplication

fun main(args: Array<String>) {
	runApplication<FemoApplication>(*args)
}
