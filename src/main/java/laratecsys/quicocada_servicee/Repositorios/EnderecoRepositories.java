package laratecsys.quicocada_servicee.Repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import laratecsys.quicocada_servicee.Dominios.Endereco;

@Repository
public interface EnderecoRepositories extends JpaRepository<Endereco, Integer>{

	
}
