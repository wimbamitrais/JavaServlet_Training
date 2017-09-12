package com.example.thymeleafapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.thymeleafapp.domain.Post;
import com.example.thymeleafapp.domain.PostRepository;

@Controller
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostRepository repo;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String listPost(Model model) {
		model.addAttribute("posts", repo.findAll());
		return "posts/list";
	}
	
	@RequestMapping (value = "/{id}/delete", method = RequestMethod.GET)
	public ModelAndView delete (@PathVariable long id) {
		repo.delete(id);
		return new ModelAndView("redirect:/posts");
	}
	
	@RequestMapping (value = "/new", method = RequestMethod.GET)
	public String newProject (){
		return "posts/new"; 
	}
	
	@RequestMapping (value = "/create", method = RequestMethod.POST)
	public ModelAndView create (@RequestParam("message") String comment) {
		repo.save(new Post (comment));
		return new ModelAndView("redirect:/posts");
	}
	
	@RequestMapping (value = "/update", method = RequestMethod.POST)
	public ModelAndView update (@RequestParam("post_id") long id,
								@RequestParam("message") String message) {
		Post post = repo.findOne(id);
		post.setMessage(message);
		repo.save(post);
		return new ModelAndView("redirect:/posts");
	}
	
	@RequestMapping (value = "/{id}/edit", method = RequestMethod.GET)
	public String edit (@PathVariable long id, Model model) {
		Post post = repo.findOne(id);
		model.addAttribute("post", post);
		return "posts/edit";
	}
	
}
