package br.com.clube.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.clube.classes.Admin;
import br.com.clube.dao.AdminDAO;

@Controller
public class LoginController {
	@Autowired
	private AdminDAO adminDAO;
	
	
	@PostMapping("/efetuarLogin")
	public String efetuarLogin(Admin adm, RedirectAttributes ra, HttpSession session) {
		adm = this.adminDAO.findByLoginAndSenha(adm.getLogin(), adm.getSenha());
		System.out.println("---------------------------------");
		if (adm != null) {
			session.setAttribute("usuarioLogado", adm);
			return "redirect:/listarAssociado";
		}else {
			ra.addFlashAttribute("mensagemErro", "Usuário/senha inválidos");
			return "redirect:/";
		}	
		//return "redirect:/";
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	
	
}