package whiskey.session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/session/akashi")
@SessionAttributes("akashi")
public class AkashiController {

    @ModelAttribute("controller")
    public String controller() {
        return "akashi";
    }

    @GetMapping
    public String index() {
        return "session/index";
    }

    @PostMapping
    public String post(SomeFormBean form, Model model) {
        model.addAttribute("akashi", form.getValue());
        return "redirect:/session/akashi";
    }

    @PostMapping("complete")
    public String complete(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/session/akashi";
    }
}
