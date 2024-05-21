package br.org.serratec.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.entities.Categoria;
import br.org.serratec.ecommerce.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;	
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}
	
	public Categoria findById(Integer id) {
		return categoriaRepository.findById(id).orElse(null);
	}
	
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria update(Integer id, Categoria categoria) {
		Categoria novoCategoria = categoriaRepository.getReferenceById(id);
		updateData(novoCategoria, categoria);
		return categoriaRepository.save(novoCategoria);
	}

	private void updateData(Categoria novoCategoria, Categoria categoria) {
		novoCategoria.setNome(categoria.getNome());
		novoCategoria.setDescricao(categoria.getDescricao());
		novoCategoria.setProduto(categoria.getProduto());
	}
	
	public Boolean delete(Integer id) {
		if(categoriaRepository.existsById(id)) {
			categoriaRepository.deleteById(id);
			Categoria categoriaDeletado = categoriaRepository.findById(id).orElse(null);
			if(categoriaDeletado == null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public long count() {
		return categoriaRepository.count();
	}
}
