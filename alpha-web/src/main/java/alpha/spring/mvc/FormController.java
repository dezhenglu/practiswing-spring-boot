package alpha.spring.mvc;

import alpha.spring.mvc.model.MyForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/forms")
public class FormController {

    @ModelAttribute("inputForm")
    public MyForm inputForm() {
        return new MyForm();
    }

    @GetMapping
    public String init(Model model) {
        return "forms";
    }

    @PostMapping
    public String submit(@Validated @ModelAttribute("inputForm") MyForm inputForm, BindingResult bindingResult,
                         @RequestBody MultiValueMap<String, ?> requestForm,
                         Model model) {
        model.addAttribute("outputForm", inputForm);
        model.addAttribute("requestForm", requestForm);

        if (bindingResult.hasErrors()) {
            return "forms";
        }

        // 正常な時だけ何かするなら。。。

        return "forms";
    }
}
