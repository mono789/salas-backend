package co.edu.udea.salasinfo.controller.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/home")
public class HomeController {
    @GetMapping
    public String welcome() {
        return "index";
    }
}
