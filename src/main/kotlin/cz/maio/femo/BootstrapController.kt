package cz.maio.femo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class BootstrapController {
    @GetMapping("/bootstrap/admin")
    fun admin(): ModelAndView {
        return ModelAndView("bootstrap/admin")
    }
}