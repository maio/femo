package cz.maio.femo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class HtmxController {
    data class Item(
        val id: Int,
        val name: String,
    )

    @Volatile
    private var items = listOf<Item>()

    private fun getItems() = items

    @Synchronized
    private fun updateItems(fn: (List<Item>) -> List<Item>) {
        items = fn(items)
    }

    @GetMapping("/htmx")
    fun htmx() = "htmx/htmx"

    @GetMapping("/htmx/view-transitions")
    fun viewTransitions() = ModelAndView("htmx/view-transitions").apply {
        addObject("items", getItems())
    }

    @GetMapping("/htmx/view-transitions::table")
    fun table() = ModelAndView("htmx/view-transitions :: table").apply {
        addObject("items", getItems())
    }

    @GetMapping("/htmx/view-transitions::item-detail")
    fun itemDetail(@RequestParam itemId: Int) = ModelAndView("htmx/view-transitions :: item-detail").apply {
        addObject("item", getItems().firstOrNull { it.id == itemId })
    }

    @PostMapping("/htmx/view-transitions::table/add-many")
    fun tableAddMany() {
        updateItems { emptyList() }

        while (items.size < 10) {
            updateItems { addNextItem() }
            Thread.sleep(1000)
        }
    }

    @PostMapping("/htmx/view-transitions::table/add-one")
    fun tableAddOne() {
        updateItems { addNextItem() }
    }

    private fun addNextItem(): List<Item> {
        val nextId = (items.firstOrNull()?.id ?: 999) + 1

        return buildList {
            add(Item(nextId, "Item #${nextId}"))
            addAll(items.take(9))
        }
    }
}