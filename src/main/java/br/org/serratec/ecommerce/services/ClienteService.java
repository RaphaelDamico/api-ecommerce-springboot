package br.org.serratec.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.entities.Cliente;
import br.org.serratec.ecommerce.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;	
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Cliente findById(Integer id) {
		return clienteRepository.findById(id).orElse(null);
	}
	
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente update(Integer id, Cliente cliente) {
		Cliente novoCliente = clienteRepository.getReferenceById(id);
		updateData(novoCliente, cliente);
		return clienteRepository.save(novoCliente);
	}

	private void updateData(Cliente novoCliente, Cliente cliente) {
		novoCliente.setEmail(cliente.getEmail());
		novoCliente.setNomeCompleto(cliente.getNomeCompleto());
		novoCliente.setCpf(cliente.getCpf());
		novoCliente.setTelefone(cliente.getTelefone());
		novoCliente.setDataNascimento(cliente.getDataNascimento());
		novoCliente.setEndereco(cliente.getEndereco());
		novoCliente.setPedido(cliente.getPedido());
		
	}
	
	public Boolean delete(Integer id) {
		if(clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
			Cliente clienteDeletado = clienteRepository.findById(id).orElse(null);
			if(clienteDeletado == null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public long count() {
		return clienteRepository.count();
	}
}
