package alpha.spring.mvc;

import alpha.domain.Account;
import alpha.spring.data.AccountEntity;
import alpha.spring.data.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author irof
 */
@Controller
public class IndexController {

    @Autowired
    AccountRepository repository;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "hoge");

        List<AccountEntity> list = repository.findAll();
        List<Account> accounts = list.stream()
                .map(entity -> Account.builder()
                        .username(entity.getName())
                        .mailAddress(entity.getMailAddress())
                        .createdDate(entity.getCreatedDate())
                        .build())
                .collect(toList());
        model.addAttribute("accounts", accounts);

        return "index";
    }
}
