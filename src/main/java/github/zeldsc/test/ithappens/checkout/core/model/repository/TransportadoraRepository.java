package github.zeldsc.test.ithappens.checkout.core.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import github.zeldsc.test.ithappens.checkout.core.model.Transportadora;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, Long>{
	
}
