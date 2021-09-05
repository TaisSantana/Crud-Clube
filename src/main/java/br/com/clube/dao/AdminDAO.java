package br.com.clube.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clube.classes.Admin;
import br.com.clube.classes.Associado;

public interface AdminDAO extends JpaRepository<Admin, Integer>  {
	
	public Admin findByLoginAndSenha(String login,String senha);
}
