package mz.ac.covid.app.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @GetMapping("admin")
    public String admin() {
        return "iframe";
    }

    @GetMapping("dashboard")
    public String home() {
        return "admin/pages/dashboard/dashboard";
    }
}
