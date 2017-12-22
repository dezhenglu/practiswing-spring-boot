package whiskey.download;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
@RequestMapping("download")
public class FileDownloadController {

    @GetMapping
    public String index() {
        return "download/index";
    }

    @PostMapping
    public String text(@RequestParam("size") int size, @RequestParam("filename") String fileName, Model model) {
        char[] chars = new char[size];
        Arrays.fill(chars, 'x');

        model.addAttribute("download-filename", fileName);
        model.addAttribute("download-content", new String(chars));

        return "downloader";
    }
}
