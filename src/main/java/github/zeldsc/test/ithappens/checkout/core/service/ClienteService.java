package github.zeldsc.test.ithappens.checkout.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import github.zeldsc.test.ithappens.checkout.core.model.Cliente;
import github.zeldsc.test.ithappens.checkout.core.model.Endereco;
import github.zeldsc.test.ithappens.checkout.core.model.repository.ClienteRepository;
import github.zeldsc.test.ithappens.checkout.exception.RuleExceprion;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente save(Cliente cliente) {
		return repository.save(cliente);
	}

	public Optional<Cliente> findBy(Long id) {
		return repository.findById(id);
	}
	
	public Optional<Cliente> findBy(String email, String senha){
		List<Cliente> clientes = repository.findByEmailIgnoreCaseAndSenha(email, senha);
		return clientes.isEmpty() ? Optional.empty() : Optional.of(clientes.get(0)) ;
	}
	
	public Cliente login(String email, String senha) throws RuleExceprion {
		Optional<Cliente> clienteOp = this.findBy(email, senha);
		if(clienteOp.isEmpty())
			throw new RuleExceprion("Cliente não autorizado", HttpStatus.FORBIDDEN);
		Cliente cliente = clienteOp.get();
		cliente.setLogado(true);
		repository.save(cliente);
		return cliente;
	}
	
	public Cliente isLoggedIn(Long id) throws RuleExceprion {
		Optional<Cliente> clienteOp = this.findBy(id);
		if(clienteOp.isEmpty() || !clienteOp.get().isLogado())
			throw new RuleExceprion("Cliente não autorizado", HttpStatus.FORBIDDEN);
		return clienteOp.get();
	}
	
	public void logout(Long id) throws RuleExceprion {
		Cliente cliente = isLoggedIn(id);
		cliente.setLogado(false);
		repository.save(cliente);
	}

	public Cliente addEndereco(Long clienteId, @Valid Endereco endereco) throws RuleExceprion {
		Cliente cliente = isLoggedIn(clienteId);
		if(Objects.isNull(cliente.getEndereco()))
			cliente.setEndereco(new ArrayList<>());
		cliente.getEndereco().add(endereco);
		save(cliente);
		return cliente;
	}

}
