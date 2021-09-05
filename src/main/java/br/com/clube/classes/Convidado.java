package br.com.clube.classes;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Convidado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message="O nome do convidado deve ser informado!")
	@Column(length=80, nullable=false)
	private String nome;
	@NotNull(message="A pessoa associada deve ser informada!")
	//@ManyToOne(cascade = CascadeType.REMOVE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	private Associado pessoaAssociada;
	//@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="data_validade_convite")
	private LocalDate dataValidadeConvite;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Associado getPessoaAssociada() {
		return pessoaAssociada;
	}
	public void setPessoaAssociada(Associado pessoaAssociada) {
		this.pessoaAssociada = pessoaAssociada;
	}
	public LocalDate getDataValidadeConvite() {
		return dataValidadeConvite;
	}
	public void setDataValidadeConvite(LocalDate dataValidadeConvite) {
		this.dataValidadeConvite = dataValidadeConvite;
	}
	
	
}
