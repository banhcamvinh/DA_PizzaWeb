package main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {
	@RequestMapping(value="/")
	public String returnfirst(HttpSession session) {
		if(session.getAttribute("username")== null)
			session.setAttribute("numofcart", 0);
		if(session.getAttribute("admin")== "1" || session.getAttribute("admin")== "2")
			return "admin";
		else
			return "home";
	}
	@RequestMapping(value="home",method=RequestMethod.GET)
	public String returnhome(ModelMap model,HttpSession session) {
		model.addAttribute("Mes","");
		if(session.getAttribute("username")== null)
			session.setAttribute("numofcart", 0);
		return "home";
	}
	@RequestMapping(value="menu",method=RequestMethod.GET)
	public String returnmenu() {
		return "redirect:/menu-pizza-page-1-.htm";
	}
	@RequestMapping(value="reg",method=RequestMethod.GET)
	public String returnreg() {
		return "reg";
	}
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String returnlogin() {
		return "login";
	}
	@RequestMapping(value="food_detail",method=RequestMethod.GET)
	public String returnfood_detail() {
		return "food_detail";
	}
}
