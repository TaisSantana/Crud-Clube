package br.com.clube.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Associado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message="O nome deve ser informado!")
	@Column(length=80, nullable=false)
	private String nome;
	@NotBlank(message="O telefone deve ser informado!")
	@Column(length=16)
	private String telefone;
	@Column(name="pagamento_em_dia")
	private boolean pagamentoEmDia;
	

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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public boolean getPagamentoEmDia() {
		return pagamentoEmDia;
	}
	public void setPagamentoEmDia(boolean pagamentoEmDia) {
		pagamentoEmDia = pagamentoEmDia;
	}	
	
}
