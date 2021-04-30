package com.example.demo.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
	@Autowired
	ItemRepository repository;
	
	@Autowired
	HttpSession session;
	
	
	@RequestMapping("/items/findAll")
	public String showItemList(Model model) {
		model.addAttribute("items", repository.findAll());
		System.out.println(model);
		return "items/item_list";
	}
	
	@RequestMapping("items/findAllByOrderPriceDesc")
	public String showItemListByOrderByPriceDesc(Model model) {
		model.addAttribute("items", repository.findAllByOrderByPriceDesc());
		return "items/item_list"; 
	}
	
	@RequestMapping("/items/getOne/{id}")
	public String showItem(@PathVariable int id, Model model) {
		model.addAttribute("item", repository.getOne(id));
		return "items/item";
	}
	
	@RequestMapping("/items/findByPrice/{price}")
	public String showItemListByPrice(@PathVariable int price, Model model) {
		model.addAttribute("items", repository.findByPrice(price));
		return "items/item_list";
	}
	
	@RequestMapping("items/findByNameAndPrice/{name}/{price}")
	public String findByNameAndPrice(@PathVariable String name, @PathVariable int price, Model model) {
		model.addAttribute("items", repository.findByNameAndPrice(name, price));
		return "items/item_list";
	}
	
	@RequestMapping("items/findByNameLike/{name}")
	public String findByNameLike(@PathVariable String name, Model model) {
		model.addAttribute("items", repository.findByNameLike("%" + name + "%"));
		return "items/item_list";
	}
	
	@RequestMapping("/items/findAllAndSetDropdown")
	public String ItemListSetDropdown(Model model) {
		session.setAttribute("items", repository.findAll());
		return "items/item_list_dropdown";
	}
	
	@RequestMapping("/items/create/input")
	public String createInput() {
		return "items/create_input";
	}
	
	@PostMapping("/items/create/complete")
	public String createComplete(@ModelAttribute Item item) {
		repository.save(item);
		return "redirect:/items/getOne/" + item.getId();
	}
}










