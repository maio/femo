package cz.maio.femo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class ComponentsController(
    private val usersService: UsersService
) {
    @GetMapping("/components")
    fun components(): ModelAndView {
        val users = usersService.getUsers()

        return ModelAndView("components/components").apply {
            addObject("users", users.take(3))
        }
    }
}