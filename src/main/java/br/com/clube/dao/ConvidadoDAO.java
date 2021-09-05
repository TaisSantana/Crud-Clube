package br.com.clube.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clube.classes.Associado;
import br.com.clube.classes.Convidado;

public interface ConvidadoDAO extends JpaRepository<Convidado, Integer> {
	public Convidado findById(int id);
	
	public Convidado findByPessoaAssociada(Optional<Associado> optional);
	public List<Convidado> findAllByPessoaAssociada(Optional<Associado> optional);
}
