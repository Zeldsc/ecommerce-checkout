package github.zeldsc.test.ithappens.checkout.core.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.zeldsc.test.ithappens.checkout.core.domain.StatusCompra;
import github.zeldsc.test.ithappens.checkout.core.model.Cliente;
import github.zeldsc.test.ithappens.checkout.core.model.Compra;
import github.zeldsc.test.ithappens.checkout.core.model.Endereco;
import github.zeldsc.test.ithappens.checkout.core.model.Entrega;
import github.zeldsc.test.ithappens.checkout.core.model.Frete;
import github.zeldsc.test.ithappens.checkout.core.model.ItemCompra;
import github.zeldsc.test.ithappens.checkout.core.model.Produto;
import github.zeldsc.test.ithappens.checkout.core.model.Promocao;
import github.zeldsc.test.ithappens.checkout.core.model.repository.CompraRepository;
import github.zeldsc.test.ithappens.checkout.core.model.repository.EntregaRepository;
import github.zeldsc.test.ithappens.checkout.core.model.repository.FreteRepository;
import github.zeldsc.test.ithappens.checkout.core.model.repository.ItemCompraRepository;
import github.zeldsc.test.ithappens.checkout.core.model.repository.PromocaoRepository;
import github.zeldsc.test.ithappens.checkout.exception.RuleExceprion;

@Service
public class CompraService {
	
	@Autowired
	private CompraRepository repository;
	
	@Autowired
	private PromocaoRepository promocaoRepository;
	
	@Autowired
	private FreteRepository freteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired 
	private ItemCompraRepository itemCompraRepository; 
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	public Compra save(Compra compra) {
		return repository.save(compra);
	}
	
	public Compra findByCliente(Long clienteId) throws RuleExceprion {
		Cliente cliente = clienteService.isLoggedIn(clienteId);
		Optional<Compra> compraOp = repository
			.findByClienteAndStatus(cliente, StatusCompra.EXPECTATIVA_COMPRA);
		Compra compra = compraOp.isPresent() ? compraOp.get() : initCompra(cliente);
		save(compra);
		return compra;
	}
	
	public List<Compra> findAllBy(Long clienteId) throws RuleExceprion {
		Cliente cliente = clienteService.isLoggedIn(clienteId);
		return repository.findAllByCliente(cliente);
	}
	
	public Compra findBy(Long clienteId, Long compraId) throws RuleExceprion {
		Cliente cliente = clienteService.isLoggedIn(clienteId);
		Optional<Compra> compraOp = repository.findByCliente(cliente);
		if (compraOp.isEmpty())
			throw new RuleExceprion("Compra não localizada");
		return compraOp.get();
	}
	
	public Compra addItem(Long clienteId, Long compraId, Long produtoId, Long quantidade) throws RuleExceprion {
		Long qtdDisponivel = produtoService.countAllAvaliable();
		boolean semEstoque = qtdDisponivel == 0;
		if (semEstoque || qtdDisponivel < quantidade)
			throw new RuleExceprion(
				semEstoque
						? "Não há estoque disponível do produto no momento"
						: String.format(
							"No momento temos apenas %d unidade%s deste produto",
							qtdDisponivel,
							qtdDisponivel > 1 ? "s" : ""
						)
			);
		Compra compra = this.findBy(clienteId, compraId);
		if (!compra.getStatus().equals(StatusCompra.EXPECTATIVA_COMPRA))
			throw new RuleExceprion("Está compra já esta fechada, por isso não suporta mais adições");
		Produto produto = produtoService.findBy(produtoId);
		ItemCompra item = initItem(compra, produto,quantidade);
		if (compra.getItens().contains(item)) {
			int index = compra.getItens().indexOf(item);
			ItemCompra itemFinded = compra.getItens().get(index);
			itemFinded.setQuantidade(quantidade + itemFinded.getQuantidade());
			compra.getItens().set(index, itemFinded);
		} else {
			item = itemCompraRepository.save(item);
			compra.getItens().add(item);
		}
		save(compra);
		produto.setQuantidade(qtdDisponivel - quantidade);
		produtoService.save(produto);
		return compra;
	}
	
	public Compra removeItem(Long clienteId, Long compraId, Long produtoId, Long quantidade) throws RuleExceprion {
		Compra compra = this.findBy(clienteId, compraId);
		if (!compra.getStatus().equals(StatusCompra.EXPECTATIVA_COMPRA))
			throw new RuleExceprion("Está compra já esta fechada, por isso não suporta mais remoções");
		Produto produto = produtoService.findBy(produtoId);
		ItemCompra item = initItem(compra, produto, quantidade);
		if (!compra.getItens().isEmpty() && compra.getItens().contains(item)) {
			
			int index = compra.getItens().indexOf(item);
			ItemCompra itemFinded = compra.getItens().get(index);
			if (itemFinded.getQuantidade() - quantidade > 0) {
				itemFinded.setQuantidade(itemFinded.getQuantidade() - quantidade);
				itemFinded = itemCompraRepository.save(itemFinded);
				compra.getItens().set(index, itemFinded);
			} else {
				itemCompraRepository.delete(itemFinded);
				compra.getItens().remove(index);
			}
			save(compra);
			produto.setQuantidade(produto.getQuantidade() + quantidade);
			produtoService.save(produto);
		}
		return compra;
	}
	
	public Compra simulatePromoadd(Long clienteId, Long compraId, String codigoPromo)
		throws RuleExceprion {
		Compra compra = this.findBy(clienteId, compraId);
		if (!compra.getStatus().equals(StatusCompra.EXPECTATIVA_COMPRA))
			throw new RuleExceprion("Está compra já esta fechada");
		if (Objects.nonNull(compra.getPromocao()))
			throw new RuleExceprion("Não é possivel adicionar outra promoção a esta compra");
		Optional<Promocao> promocaoOp = promocaoRepository
			.findByCodigoIgnoreCase(codigoPromo);
		if (promocaoOp.isEmpty())
			throw new RuleExceprion("Promoção não localizada");
		Promocao promocao = promocaoOp.get();
		LocalDate now = LocalDate.now();
		if (promocao.getDisponivel() < 1 ||
				!now.isAfter(promocao.getInicio()) ||
				!now.isBefore(promocao.getFim()))
			throw new RuleExceprion("Promoção não está mais disponivel");
		compra.setPromocao(promocao);
		return compra;
	}
	
	public Compra addPromo(Long clienteId, Long compraId, String codigoPromo)
		throws RuleExceprion {
		Compra compra = this.simulatePromoadd(clienteId, compraId, codigoPromo);
		Promocao promocao = compra.getPromocao();
		promocao.setDisponivel(promocao.getDisponivel() - 1);
		promocaoRepository.save(promocao);
		save(compra);
		return compra;
	}
	
	public List<Frete> findAllFreteBy(Long clienteId, Long enderocoId) throws RuleExceprion {
		Cliente cliente = clienteService.isLoggedIn(clienteId);
		
//		Endereco endereco = getEnderecoCliente(cliente, enderocoId);
//		
//		Long cepLoja = 65074115L;
//
//		FretePk fretePk = new FretePk();
//		fretePk.setCepOrigem(cepLoja);
//		fretePk.setCepDestino(endereco.getCep());
//		List<Frete> fretes = freteRepository.findByPk(fretePk);
//		
//		if (fretes.isEmpty())
//			throw new RuleExceprion("Infelizmete não atendemos à sua região", HttpStatus.OK);
		return freteRepository.findAll();
	}
	
	public Compra addFrete(Long clienteId, Long compraId, Long freteId, Long enderocoId) throws RuleExceprion {
		Cliente cliente = clienteService.isLoggedIn(clienteId);
		Compra compra = this.findBy(clienteId, compraId);
		if (!compra.getStatus().equals(StatusCompra.EXPECTATIVA_COMPRA))
			throw new RuleExceprion("Está compra já esta fechada");
		Optional<Frete> freteOp = freteRepository.findById(freteId);
		
		if (freteOp.isEmpty())
			throw new RuleExceprion("Opção de Frete não localizada");
		
		Endereco enderoco = getEnderecoCliente(cliente, enderocoId);
		compra.setEntrega(enderoco);
		compra.setFrete(freteOp.get());
		save(compra);
		return compra;
	}
	
	public Entrega send(Compra compra) {
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		Entrega entrega = new Entrega();
		entrega.setFrete(compra.getFrete());
		entrega.setDestino(compra.getEntrega());
		entrega.setCompra(compra);
		
		long total = entregaRepository.count();
		String pedido = now.format(formatter) + total;
		entrega.setPedido(Long.valueOf(pedido));
		entrega.setCodigoRastreio(String.format("BR%sRL", pedido));
		entregaRepository.save(entrega);
		compra.setStatus(StatusCompra.ENVIADO);
		save(compra);
		return entrega;
	}
	
	private Endereco getEnderecoCliente(Cliente cliente, Long enderocoId) throws RuleExceprion {
		if (cliente.getEndereco().isEmpty())
			throw new RuleExceprion("Endereço não Informado");
		Optional<Endereco> enderecoOp = cliente.getEndereco()
				.stream()
				.filter(endereco -> endereco.getId().equals(enderocoId))
				.findFirst();
		if (enderecoOp.isEmpty())
			throw new RuleExceprion("Endereço invalido");
		return enderecoOp.get();
	}
	
	private Compra initCompra(Cliente cliente) {
		Compra compra = new Compra();
		compra.setCliente(cliente);
		compra.setDataInicio(LocalDate.now());
		compra.setDataUltimaAtualizacao(LocalDate.now());
		compra.setStatus(StatusCompra.EXPECTATIVA_COMPRA);
		compra.setItens(new ArrayList<>());
		return compra;
	}
	
	private ItemCompra initItem(Compra compra, Produto produto, Long quantidade) {
		ItemCompra item = new ItemCompra();
		item.setCompra(compra);
		item.setProduto(produto);
		item.setQuantidade(quantidade);
		return item;
	}
}
