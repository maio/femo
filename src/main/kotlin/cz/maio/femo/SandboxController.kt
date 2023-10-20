package cz.maio.femo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class SandboxController {
    @GetMapping("/sandbox")
    fun sandbox() = "sandbox/sandbox"
}