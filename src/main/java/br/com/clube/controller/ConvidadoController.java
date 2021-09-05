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

import br.com.clube.classes.Associado;
import br.com.clube.classes.Convidado;
import br.com.clube.dao.AssociadoDAO;
import br.com.clube.dao.ConvidadoDAO;

@Controller
public class ConvidadoController {
	@Autowired
	private AssociadoDAO associadoDAO;
	@Autowired
	private ConvidadoDAO convidadoDAO;

	@GetMapping("/listarConvidado")
	public String exibirLista(Model model) {
		model.addAttribute("listaConvidados", convidadoDAO.findAll(Sort.by("nome")));
		return "convidados/convidados-list";
	}

	@GetMapping("/exibirFormCadastrarConvidado")
	public String exibirFormCadastrarConvidado(Convidado convidado, Model model) {
		model.addAttribute("listaAssociados", associadoDAO.findAll(Sort.by("nome")));
		return "convidados/cadastro-convidados";
	}

	@PostMapping("/salvarConvidado")
	public String salvarConvidado(@Valid Convidado convidado,BindingResult result, Model model) {

		if (result.hasErrors()) {
			return exibirFormCadastrarConvidado(convidado, model);
		}
		//a validade do convite sempre é de 1 mês desde o cadastro do convidado.
		LocalDate dataVenda = LocalDate.now().plusMonths(1);
		System.out.println(dataVenda);
		convidado.setDataValidadeConvite(dataVenda);
		this.convidadoDAO.save(convidado);
		return "redirect:/listarConvidado";
	}

	@GetMapping("/editarConvidado")
	public String editarConvidado(Integer id, Model model) {
		model.addAttribute("convidado", this.convidadoDAO.findById(id));
		model.addAttribute("listaAssociados", this.associadoDAO.findAll(Sort.by("nome")));
		return "convidados/cadastro-convidados";
	}

	@GetMapping("/removerConvidado")
	public String removerConvidado(Integer id) {
		this.convidadoDAO.deleteById(id);
		return "redirect:/listarConvidado";
	}
}
