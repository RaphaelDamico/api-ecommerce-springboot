package br.org.serratec.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.entities.Pedido;
import br.org.serratec.ecommerce.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidoRepository;	
	
	public List<Pedido> findAll(){
		return pedidoRepository.findAll();
	}
	
	public Pedido findById(Integer id) {
		return pedidoRepository.findById(id).orElse(null);
	}
	
	public Pedido save(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public Pedido update(Integer id, Pedido pedido) {
		Pedido novoPedido = pedidoRepository.getReferenceById(id);
		updateData(novoPedido, pedido);
		return pedidoRepository.save(novoPedido);
	}

	private void updateData(Pedido novoPedido, Pedido pedido) {
		novoPedido.setDataPedido(pedido.getDataPedido());
		novoPedido.setDataEntrega(pedido.getDataEntrega());
		novoPedido.setDataEnvio(pedido.getDataEnvio());
		novoPedido.setStatus(pedido.getStatus());
		novoPedido.setValorTotal(pedido.getValorTotal());
		novoPedido.setCliente(pedido.getCliente());
		
	}
	
	public Boolean delete(Integer id) {
		if(pedidoRepository.existsById(id)) {
			pedidoRepository.deleteById(id);
			Pedido pedidoDeletado = pedidoRepository.findById(id).orElse(null);
			if(pedidoDeletado == null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public long count() {
		return pedidoRepository.count();
	}
}
