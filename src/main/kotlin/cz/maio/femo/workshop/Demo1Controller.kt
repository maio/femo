package cz.maio.femo.workshop

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class Demo1Controller {
    var isLiked = false

    @GetMapping("/workshop/demo-1")
    fun main(): ModelAndView {
        return ModelAndView("workshop/demo-1").apply {
            addObject("isLiked", isLiked)
        }
    }

    @PatchMapping("/workshop/demo-1")
    fun patch(): ModelAndView {
        isLiked = !isLiked
        return ModelAndView("workshop/demo-1 :: like").apply {
            addObject("isLiked", isLiked)
        }
    }
}