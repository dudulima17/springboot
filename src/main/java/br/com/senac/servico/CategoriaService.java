package br.com.senac.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.dominio.Categoria;
import br.com.senac.repositorio.CategoriaRepositorio;

@Service
public class CategoriaService {

	
		@Autowired
		private CategoriaRepositorio repoCat;
		
		public Categoria buscar (Integer id) {
			Optional<Categoria> objCategoria = repoCat.findById(id);
			return objCategoria.orElseThrow(() -> new ObjectNotFoundException(
					"Categoria não encontrada ! Id: " + id +", Tipo " + Categoria.class.getName()));
		}
		public Categoria inserir (Categoria objCategoria) {
			//estou colocando um objeto novo então o id precisa ser null
			objCategoria.setId(null);
			return repoCat.save(objCategoria);
		
		}
		
		public Categoria alterar(Categoria objCategoria) {
			Categoria objCategoriaEncontrado = buscar(objCategoria.getId());
			objCategoriaEncontrado.setNome(objCategoria.getNome());
			return repoCat.save(objCategoriaEncontrado);
		
		}
		public void excluir (Integer id) {
			repoCat.deleteById(id);
		}
		
		public List<Categoria> listaCategorias(){
			return repoCat.findAll();
			
		}

}

