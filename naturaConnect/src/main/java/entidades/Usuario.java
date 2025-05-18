package entidades;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nome_completo")
	private String nome;

	@Column(name = "nome_social")
	private String nomeSocial;

	private String cpf;
	private String email;

	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;

	private String logradouro;

	@Column(name = "numero_casa")
	private int numeroCasa;

	private String complemento;
	private String senha;

	@OneToOne
	@JoinColumn(name = "codigo_consultor")
	private Consultor codigoConsultor;

	@ManyToOne
	@JoinColumn(name = "id_bairro")
	private Bairro bairro;

	@OneToOne
	@JoinColumn(name = "id_cliente")
	private Cliente idCliente;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(int numeroCasa) {
		this.numeroCasa = numeroCasa;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Consultor getCodigoConsultor() {
		return codigoConsultor;
	}

	public void setCodigoConsultor(Consultor codigoConsultor) {
		this.codigoConsultor = codigoConsultor;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

}
