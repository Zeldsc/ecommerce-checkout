package github.zeldsc.test.ithappens.checkout.core.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.zeldsc.test.ithappens.checkout.core.domain.StatusCompra;
import github.zeldsc.test.ithappens.checkout.core.domain.StatusPagamento;
import github.zeldsc.test.ithappens.checkout.core.domain.TipoPagamento;
import github.zeldsc.test.ithappens.checkout.core.model.Cliente;
import github.zeldsc.test.ithappens.checkout.core.model.Compra;
import github.zeldsc.test.ithappens.checkout.core.model.Entrega;
import github.zeldsc.test.ithappens.checkout.core.model.Pagamento;
import github.zeldsc.test.ithappens.checkout.core.model.PagamentoCartao;
import github.zeldsc.test.ithappens.checkout.core.model.repository.PagamentoRepository;
import github.zeldsc.test.ithappens.checkout.exception.RuleExceprion;

@Service
public class PagamentoService {
	
	@Autowired
	private PagamentoRepository repository;
	
	@Autowired
	private CompraService compraService;
	
	@Autowired
	private ClienteService clienteService;
	
	public List<TipoPagamento> findAllTipoPagamento() {
		return Arrays.asList(TipoPagamento.values());
	}
	
	public Entrega doPayment(
		Long clienteId,
		Long compraId,
		Long pagamentoId,
		Pagamento pagamento) throws RuleExceprion {
		Compra compra = compraService.findBy(clienteId, compraId);
		Cliente cliente = clienteService.findBy(clienteId).get();
		pagamento.setData(LocalDate.now());
		pagamento.setHora(LocalDateTime.now());
		pagamento.setPagador(cliente);
		pagamento.setCompra(compra);
		pagamento.setValor(compra.getValor());
		if(TipoPagamento.valueOf(pagamentoId).equals(TipoPagamento.CREDIT_CARD))
			return doCardPayment((PagamentoCartao) pagamento, compra);
		else return null;
	}
	
	public Entrega doCardPayment(PagamentoCartao pagamento, Compra compra){
		pagamento.setDataConfirmacao(LocalDate.now());
		pagamento.setHoraConfirmacao(LocalDateTime.now());
		pagamento.setStatus(StatusPagamento.CONFIRMADO);
		repository.save(pagamento);
		compra.setStatus(StatusCompra.PAGAMENTO_CONFIRMADO);
		compraService.save(compra);
		return compraService.send(compra);
	}
	
}
