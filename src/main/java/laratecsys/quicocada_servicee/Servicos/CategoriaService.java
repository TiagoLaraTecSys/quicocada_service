package laratecsys.quicocada_servicee.Servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import laratecsys.quicocada_servicee.DTO.CategoriaDTO;
import laratecsys.quicocada_servicee.Dominios.Categoria;
import laratecsys.quicocada_servicee.Repositorios.CategoriaRepositories;
import laratecsys.quicocada_servicee.Servicos.Exceptions.DataIntegrityException7;
import laratecsys.quicocada_servicee.Servicos.Exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepositories repo;

	
	public Categoria find(Integer id){
		
		Optional<Categoria> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrador. ID:" + id +", Tipo:" + Categoria.class.getName()) );
	}
	
	public Categoria insert(Categoria obj) {
		
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {

		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);

	}

	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException7("Não é possível deletar categoria que possui produtos");
		}
	}
	
	public List<Categoria> findAll(){
		
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO obj) {
		
		return new Categoria(obj.getId(), obj.getNome());
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		
		newObj.setNome(obj.getNome());
		
	}
}
