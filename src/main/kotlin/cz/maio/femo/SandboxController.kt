package cz.maio.femo

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class SandboxController(
    private val objectMapper: ObjectMapper
) {
    @GetMapping("/sandbox")
    fun sandbox() = "sandbox/sandbox"

    @GetMapping("/sandbox/spel")
    fun spel(): ModelAndView {
        val users = objectMapper.readTree(
            ClassPathResource("/data/users.json").inputStream
        )

        return ModelAndView("sandbox/spel").apply {
            addObject("users", users)
        }
    }
}