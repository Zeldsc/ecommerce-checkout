package github.zeldsc.test.ithappens.checkout.core.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long id;
	
	@NotEmpty
	@Column(nullable = false)
	private String nome;
	
	@NotEmpty
	@CPF
	@Column(nullable = false, unique = true)
	private String cpf;
	
	@NotEmpty
	@Email
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotEmpty
	@Size(min = 6, max = 12)
	@Column(nullable = false)
	private String senha;
	
	@OneToMany(cascade=CascadeType.ALL)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	@JoinColumn(name = "endereco", nullable = false)
	private List<Endereco> endereco;
	
	@JsonIgnore
	private boolean logado = false;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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
	
	public List<Endereco> getEndereco() {
		return endereco;
	}
	
	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}
	
	public boolean isLogado() {
		return logado;
	}
	
	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} 
		else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
	
}
