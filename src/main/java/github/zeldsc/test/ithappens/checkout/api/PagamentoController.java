package github.zeldsc.test.ithappens.checkout.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import github.zeldsc.test.ithappens.checkout.api.consumes.PagamentoCartaoDto;
import github.zeldsc.test.ithappens.checkout.core.domain.TipoPagamento;
import github.zeldsc.test.ithappens.checkout.core.model.Entrega;
import github.zeldsc.test.ithappens.checkout.core.service.PagamentoService;
import github.zeldsc.test.ithappens.checkout.exception.RuleExceprion;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("pagamento")
public class PagamentoController {
	
	@Autowired
	private PagamentoService service;
	
	@ApiOperation(value = "Retorna lista de opcões de pagamento")
	@ApiResponse(code = 200, message = "Lista de opcões de pagamento")
	@GetMapping(value = "opcoesPagamento", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TipoPagamento> buscaOpcoesPagamento() {
		return service.findAllTipoPagamento();
	}
	
	@ApiOperation(value = "Realiza pagamento da compra (checkout)", 
			notes = "objeto pagamento deve ser enviado apenas para pagamentos em cartão")
	@ApiParam
	@ApiResponse(code = 200, message = "Dados da Entrega")
	@PostMapping(value = "pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
	public Entrega buscaPagamento(
		@RequestParam Long clienteId, 
		@RequestParam Long compraId,
		@RequestParam Long pagamentoId,
		@Valid @RequestBody(required = false) PagamentoCartaoDto pagamento) throws RuleExceprion {
		return service.doPayment(clienteId, compraId, pagamentoId, pagamento.parse());
	}
}
