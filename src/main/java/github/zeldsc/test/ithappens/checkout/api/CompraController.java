package github.zeldsc.test.ithappens.checkout.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import github.zeldsc.test.ithappens.checkout.api.produces.CompraDto;
import github.zeldsc.test.ithappens.checkout.core.model.Frete;
import github.zeldsc.test.ithappens.checkout.core.service.CompraService;
import github.zeldsc.test.ithappens.checkout.exception.RuleExceprion;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("compra")
public class CompraController {
	
	@Autowired
	private CompraService service;
	
	@ApiOperation(value = "Retorna carrinho de compras para o cliente")
	@ApiResponse(code = 200, message = "Caso haja uma compra iniciaca a retorna, se não retorna um novo carrinho de compras")
	@GetMapping(value = "cesta", produces = MediaType.APPLICATION_JSON_VALUE)
	public CompraDto buscaCesta(@RequestParam Long clienteId) throws RuleExceprion {
		return new CompraDto(service.findByCliente(clienteId));
	}
	
	@ApiOperation(value = "Retorna todas as compras do cliente")
	@ApiResponse(code = 200, message = "Lista de compras")
	@GetMapping(value = "todas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CompraDto> buscaTodas(@RequestParam Long clienteId) throws RuleExceprion {
		return service.findAllBy(clienteId).stream().map(CompraDto::new).collect(Collectors.toList());
	}
	
	@ApiOperation(value = "Retorna a Compra selecionada do cliente")
	@ApiResponse(code = 200, message = "Compra selecionada")
	@GetMapping(value = "detalhes", produces = MediaType.APPLICATION_JSON_VALUE)
	public CompraDto busca(@RequestParam Long clienteId, @RequestParam Long compraId) throws RuleExceprion {
		return new CompraDto(service.findBy(clienteId, compraId));
	}
	
	@ApiOperation(value = "Adiciona item a Compra do cliente")
	@ApiResponse(code = 200, message = "Compra Atualizada")
	@GetMapping(value = "add", produces = MediaType.APPLICATION_JSON_VALUE)
	public CompraDto addItem(
		@RequestParam Long clienteId, 
		@RequestParam Long compraId, 
		@RequestParam Long produtoId, 
		@RequestParam Long quantidade) throws RuleExceprion {
		return new CompraDto(service.addItem(clienteId, compraId, produtoId, quantidade));
	}
	
	@ApiOperation(value = "Remove item a Compra do cliente")
	@ApiResponse(code = 200, message = "Compra Atualizada")
	@GetMapping(value = "remover", produces = MediaType.APPLICATION_JSON_VALUE)
	public CompraDto removerItem(
		@RequestParam Long clienteId, 
		@RequestParam Long compraId, 
		@RequestParam Long produtoId, 
		@RequestParam Long quantidade) throws RuleExceprion {
		return new CompraDto(service.removeItem(clienteId, compraId, produtoId, quantidade));
	}
	
	@ApiOperation(value = "Simula adição de cupom de desconto a Compra do cliente")
	@ApiResponse(code = 200, message = "Compra com simulação")
	@GetMapping(value = "simularcupom", produces = MediaType.APPLICATION_JSON_VALUE)
	public CompraDto simularAddCupom(
		@RequestParam Long clienteId, 
		@RequestParam Long compraId, 
		@RequestParam String codigoPromo) throws RuleExceprion {
		return new CompraDto(service.simulatePromoadd(clienteId, compraId, codigoPromo));
	}
	
	@ApiOperation(value = "Adiciona cupom de desconto a Compra do cliente")
	@ApiResponse(code = 200, message = "Compra com desconto aplicado")
	@GetMapping(value = "cupom", produces = MediaType.APPLICATION_JSON_VALUE)
	public CompraDto addCupom(
		@RequestParam Long clienteId, 
		@RequestParam Long compraId, 
		@RequestParam String codigoPromo) throws RuleExceprion {
		return new CompraDto(service.addPromo(clienteId, compraId, codigoPromo));
	}
	
	@ApiOperation(value = "Retorna opções de frete para endereco selecionado")
	@ApiResponse(code = 200, message = "Lista de fretes")
	@GetMapping(value = "fretes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Frete> buscaFrete(@RequestParam Long clienteId, @RequestParam(required = false) Long enderocoId) throws RuleExceprion {
		return service.findAllFreteBy(clienteId, enderocoId);
	}
	
	@ApiOperation(value = "Seleciona opção de frete para envio")
	@ApiResponse(code = 200, message = "Compra atualizada")
	@GetMapping(value = "frete", produces = MediaType.APPLICATION_JSON_VALUE)
	public CompraDto addFrete(
		@RequestParam Long clienteId,
		@RequestParam Long compraId,
		 @RequestParam Long enderocoId,
		@RequestParam Long freteId) throws RuleExceprion {
		return new CompraDto(service.addFrete(clienteId, compraId, freteId, enderocoId));
	}
}
