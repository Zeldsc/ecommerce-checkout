package github.zeldsc.test.ithappens.checkout.core.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import github.zeldsc.test.ithappens.checkout.core.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	public List<Produto> findByQuantidadeGreaterThan(Long value);
	
	public Long countByQuantidadeGreaterThan(Long value);
}
