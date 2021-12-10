package br.dh.ecommerce.DHcommerce.service;


import br.dh.ecommerce.DHcommerce.dto.ProdutoDto;
import br.dh.ecommerce.DHcommerce.entity.Categoria;
import br.dh.ecommerce.DHcommerce.entity.Produto;
import br.dh.ecommerce.DHcommerce.repository.CategoriaRepository;
import br.dh.ecommerce.DHcommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Produto salvar(ProdutoDto produtoDto) {
        Categoria categoria = categoriaRepository.findById(produtoDto.getCategoria()).get();
        Produto produto = new Produto(
                produtoDto.getTitulo(),
                produtoDto.getPreco(),
                produtoDto.getDescricao(),
                produtoDto.getImagem(),
                categoria
        );
        produtoRepository.save(produto);
        return produto;
    }
    public Produto atualizar(ProdutoDto produtoDto){
        Categoria categoria = categoriaRepository.findById(produtoDto.getCategoria()).get();
        Produto produto = new Produto(
                produtoDto.getTitulo(),
                produtoDto.getPreco(),
                produtoDto.getDescricao(),
                produtoDto.getImagem(),
                categoria
        );
        return produtoRepository.save(produto);
    }
    public void excluir(Integer id) {
        produtoRepository.deleteById(id);
    }

    public Optional<Produto> buscarPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }


    // Endpoit get JSON produtos por nome de categoria
    public List<Produto> findByName(String nomeCategoria){

        List<Produto> produtosSelecionados = new ArrayList<>();

        for (Produto produto :
             produtoRepository.findAll()) {
            if (produto.getCategoria().getNome().equalsIgnoreCase(nomeCategoria)) {
                produtosSelecionados.add(produto);
            }
        }
        return produtosSelecionados;
    }
    public List<Produto> findByTitulo(String nomeTitulo){

        List<Produto> produtosSelecionados =  new ArrayList<>();

        for (Produto produto :
                produtoRepository.findAll()) {
            if (produto.getTitulo().equalsIgnoreCase(nomeTitulo)) {
                produtosSelecionados.add(produto);
            }
        }
        return produtosSelecionados;
    }

}
