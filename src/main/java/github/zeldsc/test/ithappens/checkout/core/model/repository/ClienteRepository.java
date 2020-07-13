package github.zeldsc.test.ithappens.checkout.core.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import github.zeldsc.test.ithappens.checkout.core.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	public List<Cliente> findByEmailIgnoreCaseAndSenha(String email, String senha);
	
}
