package github.zeldsc.test.ithappens.checkout.api.consumes;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import github.zeldsc.test.ithappens.checkout.core.model.Cliente;
import io.swagger.annotations.ApiModelProperty;

public class ClienteDto {
	
	@NotEmpty
	@ApiModelProperty(
		value = "Nome do Cliente",
		name = "nome",
		dataType = "String",
		example = "Jose Ribamar Nunes"
	)
	private String nome;
	
	@CPF
	@NotEmpty
	@ApiModelProperty(
		value = "CPF do Cliente",
		name = "cpf",
		dataType = "String",
		example = "20401923010"
	)
	private String cpf;
	
	@Email
	@NotEmpty
	@ApiModelProperty(
		value = "Email do Cliente",
		name = "email",
		dataType = "String",
		example = "teste@teste.com"
	)
	private String email;
	
	@NotEmpty
	@Size(min = 6, max = 12)
	@ApiModelProperty(
		value = "Senha do Cliente",
		name = "senha",
		dataType = "String",
		example = "teste1234"
	)
	private String senha;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
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
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Cliente parse() {
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		cliente.setNome(nome);
		cliente.setEmail(email);
		cliente.setSenha(senha);
		return cliente;
	}
}
