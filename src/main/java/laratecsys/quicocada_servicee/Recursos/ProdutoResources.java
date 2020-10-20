package laratecsys.quicocada_servicee.Recursos;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import laratecsys.quicocada_servicee.DTO.ProdutoDTO;
import laratecsys.quicocada_servicee.Dominios.Produto;
import laratecsys.quicocada_servicee.Recursos.Utils.url;
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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Produto> insertingProduct(@RequestBody Produto newProduto){
		
		Produto produtoAdded = produtoService.insert(newProduto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(produtoAdded.getId()).toUri();		
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/page",method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findAllPage(
			@RequestParam(value="nome", defaultValue = "") String nome, 
			@RequestParam(value="categorias", defaultValue = "") String categorias, 
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC") String direction){

			Page<Produto> list = produtoService.search(url.decodeUrl(nome),url.decoderString(categorias),page, linesPerPage, orderBy, direction);
			Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
			return ResponseEntity.ok().body(listDTO);
	}
}
