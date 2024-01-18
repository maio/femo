package cz.maio.femo.workshop

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest
import net.datafaker.Faker
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import java.util.*

@Controller
class Demo2Controller {
    data class Person(
        val id: Int,
        val first: String,
        val last: String,
        val handle: String
    )

    @GetMapping("/workshop/demo-2")
    fun main(
        @RequestParam("page", defaultValue = "1") page: Int,
    ): ModelAndView {
        return ModelAndView("workshop/demo-2").apply {
            addObject("people", getPage(page = page, fromPage = 1))
            addObject("page", page)
        }
    }

    @HxRequest
    @GetMapping("/workshop/demo-2")
    fun rows(
        @RequestParam("page", defaultValue = "1") page: Int,
    ): HtmxResponse {
        Thread.sleep(1000)
        return HtmxResponse.builder()
            .view(ModelAndView("workshop/demo-2 :: rows").apply {
                addObject("people", getPage(page))
                addObject("page", page)
            })
            .replaceUrl("/workshop/demo-2?page=$page")
            .build()
    }

    private fun getPage(page: Int, fromPage: Int = page): List<Person> {
        if (page != fromPage) {
            return (fromPage..page).flatMap {
                getPage(it)
            }
        }

        val faker = Faker(Random(page.toLong()))

        val from = ((fromPage - 1) * 100) + 1
        val to = page * 100

        return (from..to).map {
            Person(
                id = it,
                first = faker.name().firstName(),
                last = faker.name().lastName(),
                handle = "@${faker.internet().username()}"
            )
        }
    }
}