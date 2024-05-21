package br.org.serratec.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.entities.ItemPedido;
import br.org.serratec.ecommerce.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;	
	
	public List<ItemPedido> findAll(){
		return itemPedidoRepository.findAll();
	}
	
	public ItemPedido findById(Integer id) {
		return itemPedidoRepository.findById(id).orElse(null);
	}
	
	public ItemPedido save(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}
	
	public ItemPedido update(Integer id, ItemPedido itemPedido) {
		ItemPedido novoItemPedido = itemPedidoRepository.getReferenceById(id);
		updateData(novoItemPedido, itemPedido);
		return itemPedidoRepository.save(novoItemPedido);
	}

	private void updateData(ItemPedido novoItemPedido, ItemPedido itemPedido) {
		novoItemPedido.setQuantidade(itemPedido.getQuantidade());
		novoItemPedido.setPrecoVenda(itemPedido.getPrecoVenda());
		novoItemPedido.setPercentualDesconto(itemPedido.getPercentualDesconto());
		novoItemPedido.setValorBruto(itemPedido.getValorBruto());
		novoItemPedido.setValorLiquido(itemPedido.getValorLiquido());	
	}
	
	public Boolean delete(Integer id) {
		if(itemPedidoRepository.existsById(id)) {
			itemPedidoRepository.deleteById(id);
			ItemPedido itemPedidoDeletado = itemPedidoRepository.findById(id).orElse(null);
			if(itemPedidoDeletado == null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public long count() {
		return itemPedidoRepository.count();
	}
}
