package whiskey.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/session/hibiki")
@SessionAttributes("scopedTarget.someSessionBean")
public class HibikiController {

    @Autowired
    SomeSessionBean someSessionBean;

    @ModelAttribute("controller")
    public String controller() {
        return "hibiki";
    }

    @GetMapping
    public String index() {
        return "session/index";
    }

    @PostMapping
    public String post(SomeFormBean form,
                       @ModelAttribute("controller") String controller) {
        someSessionBean.setKey(controller);
        someSessionBean.setValue(form.getValue());
        return "redirect:/session/hibiki";
    }

    @PostMapping("complete")
    public String complete(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/session/hibiki";
    }
}
