package laratecsys.quicocada_servicee.Repositorios;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import laratecsys.quicocada_servicee.Dominios.Categoria;
import laratecsys.quicocada_servicee.Dominios.Produto;

@Repository
public interface ProdutoRepositories extends JpaRepository<Produto, Integer>{

	//@Query("SELECT DISTINCT obj from Produto obj"
	//		+ " inner join obj.categorias cat where"
	//		+ " obj.nome LIKE %:nome% AND cat IN :categorias")
	
	//Page<Produto> search(@Param(value = "nome") String nome,@Param(value = "categorias") List<Categoria> categorias, Pageable pageRequest);
	
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome,List<Categoria> categorias, Pageable pageRequest);
}
