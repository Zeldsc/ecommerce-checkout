package github.zeldsc.test.ithappens.checkout.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import github.zeldsc.test.ithappens.checkout.core.model.Produto;
import github.zeldsc.test.ithappens.checkout.core.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@ApiOperation(value = "Retorna lista de opcões de pagamento")
	@ApiResponse(code = 200, message = "Lista de opcões de pagamento")
	@GetMapping(value = "lista", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Produto> buscaTodos(){
		return service.findAllAvaliable();
	}
	
	@ApiOperation(value = "Retorna o valor disponível do produto selecionado")
	@ApiResponse(code = 200, message = "Quantidade disponivel do produto")
	@GetMapping(value = "disponibilidade", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long verificaDisponibilidade(@RequestParam Long produtoId){
		return service.countAllAvaliable();
	}
	
}
