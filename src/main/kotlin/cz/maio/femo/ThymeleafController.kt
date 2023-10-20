package cz.maio.femo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class ThymeleafController(
    private val usersService: UsersService
) {
    @GetMapping("/thymeleaf/spel")
    fun spel(): ModelAndView {
        val users = usersService.getUsersAsJsonNode()

        return ModelAndView("thymeleaf/spel").apply {
            addObject("users", users)
        }
    }
}