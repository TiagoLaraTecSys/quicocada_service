package laratecsys.quicocada_servicee.Repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import laratecsys.quicocada_servicee.Dominios.Pagamento;

@Repository
public interface PagamentoRepositories extends JpaRepository<Pagamento, Integer>{

	
}
