package github.zeldsc.test.ithappens.checkout.core.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import github.zeldsc.test.ithappens.checkout.core.model.Frete;
import github.zeldsc.test.ithappens.checkout.core.model.FretePk;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long>{
	
	public List<Frete> findByPk(FretePk fretePk);
}
