package cz.maio.femo

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class ComponentsController(
    private val objectMapper: ObjectMapper
) {
    @GetMapping("/components")
    fun components(): ModelAndView {
        val users = objectMapper.readTree(
            ClassPathResource("/data/users.json").inputStream
        )

        return ModelAndView("components/components").apply {
            addObject("users", users)
        }
    }
}