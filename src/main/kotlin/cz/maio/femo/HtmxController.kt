package cz.maio.femo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.util.concurrent.atomic.AtomicReference

@Controller
class HtmxController {

    private data class State(
        val items: List<Item>
    ) {
        data class Item(
            val id: Int,
            val name: String,
        )
    }

    private val defaultState = State(
        items = listOf(
            State.Item(id = 1, name = "Item #1"),
            State.Item(id = 2, name = "Item #2"),
            State.Item(id = 3, name = "Item #3"),
            State.Item(id = 4, name = "Item #4"),
            State.Item(id = 5, name = "Item #5"),
        )
    )

    private val state = AtomicReference(defaultState)

    @GetMapping("/htmx")
    fun htmx() = "htmx/htmx"

    @GetMapping("/htmx/view-transitions")
    fun viewTransitions() = ModelAndView("htmx/view-transitions").apply {
        addObject("items", state.get().items)
    }

    @PostMapping("/htmx/view-transitions/reset")
    fun reset(): ModelAndView {
        state.swap { defaultState }
        return table()
    }

    @GetMapping("/htmx/view-transitions::table")
    fun table() = ModelAndView("htmx/view-transitions :: table").apply {
        addObject("items", state.get().items)
    }

    @GetMapping("/htmx/view-transitions::item-detail")
    fun itemDetail(@RequestParam itemId: Int) = ModelAndView("htmx/view-transitions :: item-detail").apply {
        addObject("item", state.get().items.firstOrNull { it.id == itemId })
    }

    @DeleteMapping("/htmx/view-transitions/{itemId}")
    fun deleteItem(@PathVariable itemId: Int): ModelAndView {
        state.swap { copy(items = items.filterNot { it.id == itemId }) }
        return table()
    }
}