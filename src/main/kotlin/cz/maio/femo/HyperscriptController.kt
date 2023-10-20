package cz.maio.femo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HyperscriptController {
    @GetMapping("/hyperscript")
    fun hyperscript() = "hyperscript/hyperscript"
}