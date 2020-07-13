package github.zeldsc.test.ithappens.checkout.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import github.zeldsc.test.ithappens.checkout.api.consumes.ClienteDto;
import github.zeldsc.test.ithappens.checkout.api.consumes.EnderecoDto;
import github.zeldsc.test.ithappens.checkout.core.model.Cliente;
import github.zeldsc.test.ithappens.checkout.core.service.ClienteService;
import github.zeldsc.test.ithappens.checkout.exception.RuleExceprion;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@ApiOperation(value = "Cadastrar usuarios")
	@PostMapping(value = "cadastrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@Valid @RequestBody ClienteDto cliente) throws RuleExceprion {
		Cliente c = cliente.parse();
		c.setLogado(false);
		service.save(c);
	}
	
	@ApiOperation(value = "Login usuarios")
	@ApiResponse(code = 200, message = "Retorna o cliente atualizado")
	@GetMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
	public Cliente login(
		@RequestParam String email,
		@RequestParam String senha) throws RuleExceprion {
		return service.login(email, senha);
	}
	
	@ApiOperation(value = "Logout usuarios")
	@GetMapping(value = "logout", produces = MediaType.APPLICATION_JSON_VALUE)
	public void logout(
		@RequestParam Long clienteId) throws RuleExceprion {
		service.logout(clienteId);;
	}
	
	@ApiOperation(value = "Adicionar endereco de usuario")
	@ApiResponse(code = 200, message = "Retorna o cliente atualizado")
	@PostMapping(value = "endereco", produces = MediaType.APPLICATION_JSON_VALUE)
	public Cliente addEndereco(
		@RequestParam Long clienteId,
		@Valid @RequestBody EnderecoDto endereco) throws RuleExceprion {
		return service.addEndereco(clienteId, endereco.parse());
	}
}
