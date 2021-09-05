package br.com.clube.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.clube.classes.Admin;
import br.com.clube.dao.AdminDAO;

@Controller
public class AdminController {
	@Autowired
	private AdminDAO adminDAO;
	
	@GetMapping("/paginaInicial")
	public String paginaInicial() {
		return "/";
	} 
	@GetMapping("/formCadastrarAdm")
	public String formCadastrarAdmin(Admin admin, Model model) {
		return "cadastrar-admin";
	}
	
	@PostMapping("/salvarAdm")
	public String salvarAdm(@Valid Admin admin,BindingResult result, Model model) {
		System.out.println("ADMIIIIIIIIN SALVAAAAAR");
		if (result.hasErrors()) {
			return formCadastrarAdmin(admin, model);
		}
		this.adminDAO.save(admin);
		return "redirect:/";
	}
}
