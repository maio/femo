package cz.maio.femo.workshop

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.roundToInt
import kotlin.math.sin

@Controller
class Demo3Controller {
    data class Rainbow(
        val iter: Int = aIter.getAndIncrement(),
        val barCount: Int = 10,
    ) {
        companion object {
            val aIter = AtomicInteger(0)
        }

        private val barWidth = (100.00 / barCount)
        val bars: List<Bar> = (0..<barCount).map { i ->
            Bar(
                width = barWidth,
                translateY = (sin(iter / 10.0 + i / 5.0) * 50.0).roundToInt(),
                hue = ((360.0 / barCount * i - iter).roundToInt() % 360),
                rotation = ((iter + i) % 360),
                x = (barWidth * i)
            )
        }

        data class Bar(
            val translateY: Int,
            val hue: Int,
            val rotation: Int,
            val x: Double,
            val width: Double,
        ) {
            val color = "hsl(${hue},95%,55%)"
        }
    }

    @GetMapping("/workshop/demo-3")
    fun main(): ModelAndView {
        return ModelAndView("workshop/demo-3").apply {
            addObject("rainbow", Rainbow())
        }
    }

    @GetMapping("/workshop/demo-3/rainbow")
    fun rainbow(
        @RequestParam(defaultValue = "5") count: Int,
    ): ModelAndView {
        return ModelAndView("workshop/demo-3 :: rainbow").apply {
            addObject("rainbow", Rainbow(barCount = count))
        }
    }
}