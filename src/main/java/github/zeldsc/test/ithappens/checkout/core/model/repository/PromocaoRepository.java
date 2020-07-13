package github.zeldsc.test.ithappens.checkout.core.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import github.zeldsc.test.ithappens.checkout.core.model.Promocao;

@Repository
public interface PromocaoRepository extends JpaRepository<Promocao, Long>{
	
	public Optional<Promocao> findByCodigoIgnoreCase(String codigo);
}
