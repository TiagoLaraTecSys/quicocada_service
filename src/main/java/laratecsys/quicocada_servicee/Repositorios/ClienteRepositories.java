package laratecsys.quicocada_servicee.Repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import laratecsys.quicocada_servicee.Dominios.Cliente;

@Repository
public interface ClienteRepositories extends JpaRepository<Cliente, Integer>{

	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
}
