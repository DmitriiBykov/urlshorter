package ru.bykov.urlshorter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.bykov.urlshorter.services.UrlService;

import java.net.http.HttpClient;

@Controller
@RequestMapping("/")
public class MainController {
    private UrlService urlService;

    @GetMapping("/main")
    public String mainPage(Model model){
        return "mainPage";
    }
    @PostMapping("/main")
    public String shorten(@RequestParam String longUrl, Model model){

        String shortUrl = urlService.save(longUrl);
        model.addAttribute("shortUrl", "http://localhost:8080/"+shortUrl);
        return "mainPage";
    }
    @GetMapping("{path}")
    private RedirectView redirect(@PathVariable("path") String path){
        return new RedirectView(urlService.get(path));
    }

    @Autowired
    public MainController(UrlService urlService) {
        this.urlService = urlService;
    }

    public MainController() {
    }

    public UrlService getUrlService() {
        return urlService;
    }

    public void setUrlService(UrlService urlService) {
        this.urlService = urlService;
    }
}
