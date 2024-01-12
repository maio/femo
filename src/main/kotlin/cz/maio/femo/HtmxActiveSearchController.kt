package cz.maio.femo

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class HtmxActiveSearchController {
    data class Contact(
        val name: String,
        val email: String,
    )

    private val contacts =
        listOf(
            Contact("Venus", "lectus.rutrum@Duisa.edu"),
            Contact("Fletcher", "metus@Aenean.org"),
            Contact("TaShya", "tincidunt.orci.quis@nuncnullavulputate.co.uk"),
            Contact("Jennifer", "sapien.Aenean.massa@risus.com"),
            Contact("Jena", "non.cursus.non@Phaselluselit.com"),
        )

    @GetMapping("/htmx/active-search")
    fun contacts(@RequestParam(defaultValue = "") q: String): ModelAndView {
        return ModelAndView("htmx/active-search").apply {
            addObject("query", q)
            addObject("contacts", findContacts(q))
        }
    }

    @GetMapping("/htmx/active-search::table")
    fun contactsTable(@RequestParam(defaultValue = "") q: String): HtmxResponse {
        return HtmxResponse
            .builder()
            .pushUrl("/htmx/active-search?q=$q")
            .view(
                ModelAndView("htmx/active-search :: table").apply {
                    addObject("query", q)
                    addObject("contacts", findContacts(q))
                }
            ).build()
    }

    private fun findContacts(q: String): List<Contact> {
        if (q == "error") {
            throw RuntimeException("This is an error")
        }

        if (q.length == 1) {
            Thread.sleep(1000)
        }

        return contacts.filter {
            it.name.contains(q, ignoreCase = true) || it.email.contains(q, ignoreCase = true)
        }
    }
}