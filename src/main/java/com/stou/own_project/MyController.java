package com.stou.own_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class MyController {
    private final MyService myService;

    @Autowired
    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("images", myService.getAllImages());
        return "index";
    }

    @GetMapping("/newform")
    public String newForm() {
        return "newform";
    }

    @PostMapping("/newform")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        myService.uploadImage(file);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, @RequestParam(required = false) String name){
        myService.deleteImage(id,name);
        return "redirect:/";
    }


    /*@GetMapping("/TheImages/{name}")
    public String theImages(@PathVariable String name, Model model) {
        System.out.println(name);
        model.addAttribute("images", myService.getAllImages());
        model.addAttribute("name", name);
        return "showImages";
    }*/
}
