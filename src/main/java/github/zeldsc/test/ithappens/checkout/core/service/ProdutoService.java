package github.zeldsc.test.ithappens.checkout.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.zeldsc.test.ithappens.checkout.core.model.Produto;
import github.zeldsc.test.ithappens.checkout.core.model.repository.ProdutoRepository;
import github.zeldsc.test.ithappens.checkout.exception.RuleExceprion;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	public List<Produto> findAll() {
		return repository.findAll();
	}
	
	public Produto findBy(Long id) throws RuleExceprion {
		Optional<Produto> produtoOp = repository.findById(id);
		if(produtoOp.isEmpty())
			throw new RuleExceprion("Produto não localizado");
		return produtoOp.get();
	}
	
	public List<Produto> findAllAvaliable() {
		return repository.findByQuantidadeGreaterThan(0L);
	}
	
	public Long countAllAvaliable() {
		return repository.countByQuantidadeGreaterThan(0L);
	}

	public Produto save(Produto produto) {
		return repository.save(produto);
	}
	
}
