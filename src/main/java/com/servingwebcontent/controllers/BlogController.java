package com.servingwebcontent.controllers;

import com.servingwebcontent.models.Post;
import com.servingwebcontent.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

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

    @GetMapping ("/blog/{ID}")
    public String blogDetails (@PathVariable(value = "ID") long ID, Model model){
        if(!postRepository.existsById(ID)){
            return "redirect:/blog";
        }

        Optional<Post> post = postRepository.findById(ID);
        ArrayList<Post> result = new ArrayList<>();
        post.ifPresent(result::add);
        model.addAttribute("post",result);

        return "blog-details";
    }

    @GetMapping("/blog/{ID}/edit")
    public String postEdit (@PathVariable(value="ID") long ID, Model model){
        if(!postRepository.existsById(ID)){
            return "redirect:/blog";
        }

        Optional<Post> post = postRepository.findById(ID);
        ArrayList<Post> result = new ArrayList<>();
        post.ifPresent(result::add);
        model.addAttribute("post", result);

        return "post-edit";
    }

    @PostMapping ("/blog/{ID}/edit")
    public String postEdit (@PathVariable(value="ID") long ID, @RequestParam String title, @RequestParam String announcement,
                           @RequestParam String fullText, Model model) {
        Post post = postRepository.findById(ID).orElseThrow();
        post.setTitle(title);
        post.setAnnouncement(announcement);
        post.setFullText(fullText);
        postRepository.save(post);
        return "redirect:/blog";
    }
    @PostMapping ("/blog/{ID}/remove")
    public String postDelete (@PathVariable(value="ID") long ID, Model model) {
        Post post = postRepository.findById(ID).orElseThrow();
            postRepository.delete(post);
        return "redirect:/blog";
    }

}
