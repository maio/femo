package cz.maio.femo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ComponentsController {
    @GetMapping("/components")
    fun components() = "components/components"
}