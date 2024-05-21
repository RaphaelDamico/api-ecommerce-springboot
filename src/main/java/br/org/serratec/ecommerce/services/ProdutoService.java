package br.org.serratec.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.entities.Produto;
import br.org.serratec.ecommerce.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;	
	
	public List<Produto> findAll(){
		return produtoRepository.findAll();
	}
	
	public Produto findById(Integer id) {
		return produtoRepository.findById(id).orElse(null);
	}
	
	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto update(Integer id, Produto produto) {
		Produto novoProduto = produtoRepository.getReferenceById(id);
		updateData(novoProduto, produto);
		return produtoRepository.save(novoProduto);
	}

	private void updateData(Produto novoProduto, Produto produto) {
		novoProduto.setNome(produto.getNome());
		novoProduto.setDescricao(produto.getDescricao());
		novoProduto.setQtdEstoque(produto.getQtdEstoque());
		novoProduto.setDataCadastro(produto.getDataCadastro());
		novoProduto.setValorUnitario(produto.getValorUnitario());
		novoProduto.setImagem(produto.getImagem());
		novoProduto.setCategoria(produto.getCategoria());
		
	}
	
	public Boolean delete(Integer id) {
		if(produtoRepository.existsById(id)) {
			produtoRepository.deleteById(id);
			Produto produtoDeletado = produtoRepository.findById(id).orElse(null);
			if(produtoDeletado == null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public long count() {
		return produtoRepository.count();
	}
}
