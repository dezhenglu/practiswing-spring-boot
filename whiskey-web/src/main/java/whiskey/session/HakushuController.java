package whiskey.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/session/hakushu")
@SessionAttributes("hakushu")
public class HakushuController {

    @Autowired
    SomeSessionBean someSessionBean;

    @ModelAttribute("controller")
    public String controller() {
        return "hakushu";
    }

    @GetMapping
    public String index() {
        return "session/index";
    }

    @PostMapping
    public String post(SomeFormBean form,
                       @ModelAttribute("controller") String controller,
                       Model model) {
        model.addAttribute("hakushu", form.getValue());
        someSessionBean.setKey(controller);
        someSessionBean.setValue(form.getValue());
        return "redirect:/session/hakushu";
    }

    @PostMapping("complete")
    public String complete(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/session/hakushu";
    }
}
