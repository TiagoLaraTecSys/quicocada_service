package laratecsys.quicocada_servicee.Recursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import laratecsys.quicocada_servicee.Dominios.Produto;
import laratecsys.quicocada_servicee.Servicos.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResources {

	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Produto> findProductById(@PathVariable Integer id){
		
		Produto obj = produtoService.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
}
