package top.huajieyu001.security.controller;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
//        SecurityProperties
        return "index";
    }
}
