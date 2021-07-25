package mz.ac.covid.app.boot.domain;

import java.util.Date;

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

	@Column(name = "tipo_documento")
	private String tipoDocumento;

	@Column(name = "numero_documento")
	private String numeroDocumento;

	@Column(name = "genero")
	private String sexo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id_fk", nullable = false)
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(name = "departamento_id_fk")
	private Departamento departamento;

	@ManyToOne
	@JoinColumn(name = "instituicao_id_fk")
	private Instituicao instituicao;

	@Column(name = "contacto")
	private String telefone;

	@Column(name = "email")
	private String email;

	// add chave de lista de vacinacao

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

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
