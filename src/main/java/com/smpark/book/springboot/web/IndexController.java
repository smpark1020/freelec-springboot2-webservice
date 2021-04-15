package com.smpark.book.springboot.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.smpark.book.springboot.config.auth.dto.SessionUser;
import com.smpark.book.springboot.service.posts.PostsService;
import com.smpark.book.springboot.web.dto.PostsResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
	
	private final PostsService postsService;
	private final HttpSession httpSession;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("posts", postsService.findAllDesc());
		
		SessionUser user = (SessionUser)httpSession.getAttribute("user");
		if (user != null) {
			model.addAttribute("userName", user.getName());
		}
		
		return "index";
	}
	
	@GetMapping("/posts/save")
	public String postsSave() {
		return "posts-save";
	}
	
	@GetMapping("/posts/update/{id}")
	public String postsUpdate(@PathVariable Long id, Model model) {
		PostsResponseDto dto = postsService.findById(id);
		model.addAttribute("post", dto);
		
		return "posts-update";
	}
	
}
