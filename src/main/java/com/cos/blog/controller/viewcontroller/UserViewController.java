package com.cos.blog.controller.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserViewController {

    @GetMapping("/user/updateForm/{id}")
    public String getUserUpdateForm(@PathVariable Integer id) {
        return "user/updateform";
    }
}
