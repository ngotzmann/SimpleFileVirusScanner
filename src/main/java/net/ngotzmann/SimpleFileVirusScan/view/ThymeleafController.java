package net.ngotzmann.SimpleFileVirusScan.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ThymeleafController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String serveIndex(Model model) {
        model.addAttribute("description", "Upload files and archives, to test if it is infected by a virus. Binaries are not supported");
        return "index";
    }
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String serveErrorPage() {
        return "error";
    }
}
