package br.com.clube.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clube.classes.Associado;

public interface AssociadoDAO extends JpaRepository<Associado, Integer> {
	
	public Associado findById(int id);
}
