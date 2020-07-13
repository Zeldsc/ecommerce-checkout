package github.zeldsc.test.ithappens.checkout.core.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import github.zeldsc.test.ithappens.checkout.core.domain.StatusCompra;
import github.zeldsc.test.ithappens.checkout.core.model.Cliente;
import github.zeldsc.test.ithappens.checkout.core.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>{

	public Optional<Compra> findByCliente(Cliente cliente);
	
	public Optional<Compra> findByClienteAndStatus(Cliente cliente, StatusCompra status);
	
	public List<Compra> findAllByCliente(Cliente cliente);
	
}
