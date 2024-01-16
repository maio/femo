package cz.maio.femo

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import kotlin.math.roundToInt

@Controller
class SandboxController {
    @GetMapping("/sandbox")
    fun sandbox(
        @RequestParam(defaultValue = "255") red: Int,
        @RequestParam(defaultValue = "10") count: Int,
    ): ModelAndView {
        return ModelAndView("sandbox/sandbox").apply {
            addObject("red", red)
            addObject("count", count)
            addObject("spec", LinesView(count = count))
        }
    }

    data class LinesView(
        val width: Int = 200,
        val count: Int = 10,
    ) {
        private val dx: Double = width / 2.0 / count

        val deltas: List<Int> = (1..count).map { (it * dx).roundToInt() }
    }

    @GetMapping("/sandbox/my-svg")
    fun mySvg(
        @RequestParam(defaultValue = "255") red: Int,
        @RequestParam(defaultValue = "10") count: Int,
    ): HtmxResponse {
        return HtmxResponse.builder()
            .view(ModelAndView("sandbox/sandbox :: my-svg").apply {
                addObject("red", red)
                addObject("count", count)
                addObject("spec", LinesView(count = count))
            })
            .pushUrl("/sandbox?red=$red&count=$count")
            .build()
    }
}