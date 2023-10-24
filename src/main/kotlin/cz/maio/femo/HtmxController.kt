package cz.maio.femo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmxController {
    @GetMapping("/htmx")
    fun htmx() = "htmx/htmx"
}