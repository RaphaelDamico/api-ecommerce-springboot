package br.org.serratec.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.entities.Endereco;
import br.org.serratec.ecommerce.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	EnderecoRepository enderecoRepository;	
	
	public List<Endereco> findAll(){
		return enderecoRepository.findAll();
	}
	
	public Endereco findById(Integer id) {
		return enderecoRepository.findById(id).orElse(null);
	}
	
	public Endereco save(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public Endereco update(Integer id, Endereco endereco) {
		Endereco novoEndereco = enderecoRepository.getReferenceById(id);
		updateData(novoEndereco, endereco);
		return enderecoRepository.save(novoEndereco);
	}

	private void updateData(Endereco novoEndereco, Endereco endereco) {
		novoEndereco.setCep(endereco.getCep());
		novoEndereco.setRua(endereco.getRua());
		novoEndereco.setBairro(endereco.getBairro());
		novoEndereco.setCidade(endereco.getCidade());
		novoEndereco.setNumero(endereco.getNumero());
		novoEndereco.setComplemento(endereco.getComplemento());
		novoEndereco.setUf(endereco.getUf());
		
	}
	
	public Boolean delete(Integer id) {
		if(enderecoRepository.existsById(id)) {
			enderecoRepository.deleteById(id);
			Endereco enderecoDeletado = enderecoRepository.findById(id).orElse(null);
			if(enderecoDeletado == null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public long count() {
		return enderecoRepository.count();
	}
}
