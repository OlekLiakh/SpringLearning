package com.servingwebcontent.controllers;

import com.servingwebcontent.models.Post;
import com.servingwebcontent.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blogMain(Model model) {

        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);

        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String postAddForm (Model model){
        return "post-add";
    }

    @PostMapping ("/blog/add")
    public String postAdd (@RequestParam String title, @RequestParam String announcement,
                           @RequestParam String fullText, Model model){
        Post post = new Post(title, announcement, fullText);
        postRepository.save(post);
        return "redirect:/blog";
    }

}
