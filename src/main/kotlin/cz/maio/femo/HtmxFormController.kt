package cz.maio.femo

import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class HtmxFormController {

    class MyForm {
        @NotNull
        @Size(min = 10, max = 3000)
        var inputText: String = "a b c"
    }

    data class FormResult(
        val wordCount: Int
    )

    @GetMapping("/htmx/form")
    fun main(myForm: MyForm): ModelAndView {
        return ModelAndView("htmx/form").apply {
            addObject("myForm", myForm)
        }
    }

    @PostMapping("/htmx/form")
    fun processForm(
        @Valid myForm: MyForm,
        bindingResult: BindingResult,
        response: HttpServletResponse
    ): ModelAndView {
        if (bindingResult.hasErrors()) {
            response.addHeader("HX-Retarget", "#myForm")
            response.addHeader("HX-Reswap", "outerHTML")

            return ModelAndView("htmx/form :: myForm")
        }

        Thread.sleep(500)

        return ModelAndView("htmx/form :: result").apply {
            addObject("result", FormResult(wordCount = myForm.inputText.split("\\s".toRegex()).size))
        }
    }
}