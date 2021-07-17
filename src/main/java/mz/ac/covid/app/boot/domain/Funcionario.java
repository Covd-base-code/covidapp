package mz.ac.covid.app.boot.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@Entity
@Table(name = "FUNCIONARIOS")
public class Funcionario extends AbstractEntity<Long> {

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String nacionalidade;

	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_nascimento", nullable = false, columnDefinition = "DATE")
	private Date data_nasc;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id_fk", nullable = false)
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(name = "departamento_id_fk")
	private Departamento departamento;

	@ManyToOne
	@JoinColumn(name = "instituicao_id_fk")
	private Instituicao instituicao;

	@OneToMany
	private List<Telefone> telefones;

	@Column(name = "email")
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
