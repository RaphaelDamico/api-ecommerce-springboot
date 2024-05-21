package br.org.serratec.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.entities.Imagem;
import br.org.serratec.ecommerce.repositories.ImagemRepository;

@Service
public class ImagemService {
	
	@Autowired
	ImagemRepository imagemRepository;	
	
	public List<Imagem> findAll(){
		return imagemRepository.findAll();
	}
	
	public Imagem findById(Integer id) {
		return imagemRepository.findById(id).orElse(null);
	}
	
	public Imagem save(Imagem imagem) {
		return imagemRepository.save(imagem);
	}
	
	public Imagem update(Integer id, Imagem imagem) {
		Imagem novoImagem = imagemRepository.getReferenceById(id);
		updateData(novoImagem, imagem);
		return imagemRepository.save(novoImagem);
	}

	private void updateData(Imagem novoImagem, Imagem imagem) {
		novoImagem.setDados(imagem.getDados());
		novoImagem.setTipo(imagem.getTipo());
		novoImagem.setNome(imagem.getNome());
		novoImagem.setProduto(imagem.getProduto());
	}
	
	public Boolean delete(Integer id) {
		if(imagemRepository.existsById(id)) {
			imagemRepository.deleteById(id);
			Imagem imagemDeletado = imagemRepository.findById(id).orElse(null);
			if(imagemDeletado == null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public long count() {
		return imagemRepository.count();
	}
}
