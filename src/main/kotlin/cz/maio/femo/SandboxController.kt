package cz.maio.femo

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.roundToInt
import kotlin.math.sin

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
            addObject("rainbow", Rainbow())
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
            .replaceUrl("/sandbox?red=$red&count=$count")
            .build()
    }

    data class Rainbow(
        val iter: Int = aIter.getAndIncrement(),
        val barCount: Int = 5,
    ) {
        companion object {
            val aIter = AtomicInteger(0)
        }

        private val barWidth = (1000.0 / barCount).roundToInt()
        val bars: List<Bar> = (1..barCount).map { i ->
            Bar(
                width = barWidth,
                translateY = (sin(iter / 10.0 + i / 5.0) * 50.0).roundToInt(),
                hue = ((360.0 / barCount * i - iter).roundToInt() % 360),
                rotation = ((iter + i) % 360),
                x = barWidth * i
            )
        }

        data class Bar(
            val translateY: Int,
            val hue: Int,
            val rotation: Int,
            val x: Int,
            val width: Int,
        ) {
            val color = "hsl(${hue},95%,55%)"
        }
    }

    @GetMapping("/sandbox/rainbow")
    fun rainbow(
        @RequestParam(defaultValue = "5") count: Int,
    ): ModelAndView {
        return ModelAndView("sandbox/sandbox :: rainbow").apply {
            addObject("rainbow", Rainbow(barCount = count))
        }
    }
}