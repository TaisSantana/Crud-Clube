package br.com.clube.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.clube.classes.Associado;
import br.com.clube.classes.Convidado;
import br.com.clube.dao.AssociadoDAO;
import br.com.clube.dao.ConvidadoDAO;



@Controller
public class AssociadoController {
	@Autowired
	private AssociadoDAO associadoDAO;
	@Autowired
	private ConvidadoDAO convidadoDAO;
	
	@GetMapping("/listarAssociado")
	public String exibirLista(Model model) {
		model.addAttribute("listaAssociados", associadoDAO.findAll(Sort.by("nome")));
		return "associados/associados-list";
	}
	
	@GetMapping("/exibirFormCadastrarAssociado")
	public String exibirFormCadastrarAssociado(Associado associado, Model model) {
		return "associados/cadastro-associado";
	}
	
	@PostMapping("/salvarAssociado")
	public String salvarAssociado(@Valid Associado associado, 			
			BindingResult result, Model model) {
		System.out.println("------------------");
		System.out.println(associado.getPagamentoEmDia());
		if (result.hasErrors()) {
			return exibirFormCadastrarAssociado(associado, model);
		}
		this.associadoDAO.save(associado);
		return "redirect:/listarAssociado";
	}

	//com thymeleaf arrumar um modo de listar isso na listagem de associados, como mais um campo.
	@GetMapping("/verListaConvidadosporAssociado")
	public String verListaConvidadosporAssociado(Integer id, Model model) {
		model.addAttribute("listaConvidados", this.convidadoDAO.findByPessoaAssociada(this.associadoDAO.findById(id)));
		return "associados/associados-convidados-list";
	}
	
	@GetMapping("/editarAssociado")
	public String editarAssociado(Integer id, Model model) {
		model.addAttribute("associado", this.associadoDAO.findById(id));
		model.addAttribute("listaAssociados", this.associadoDAO.findAll(Sort.by("nome")));
		return "associados/cadastro-associado";
	}

	@GetMapping("/removerAssociado")
	public String removerAssociado(Integer id, RedirectAttributes ra) {
		/*(List<Convidado> convidados= this.convidadoDAO.findAllByPessoaAssociada(this.associadoDAO.findById(id));
		if (convidados != null) {
			this.associadoDAO.deleteById(id);		
		}else {
			ra.addFlashAttribute("mensagemErro", "Associados com Convidados associados não podem ser excluídos!");		
		}*/
		this.associadoDAO.deleteById(id);	
		return "redirect:/listarAssociado";
	}
	
}
