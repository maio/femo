package cz.maio.femo.workshop

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class WorkshopController {
    @GetMapping("/workshop")
    fun workshop(): ModelAndView {
        return ModelAndView("workshop/workshop")
    }
}