package br.dh.ecommerce.DHcommerce.service;


import br.dh.ecommerce.DHcommerce.entity.Categoria;
import br.dh.ecommerce.DHcommerce.entity.Produto;
import br.dh.ecommerce.DHcommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    public Categoria atualizar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }
    public void excluir(Integer id) {
        categoriaRepository.deleteById(id);
    }

    public Optional<Categoria> buscarPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }

    // EndPoint get JSON com todas as categorias
    public List<String> buscarTodosNomes() {
        List<String> nomes = new ArrayList<>();

        for (Categoria categoria :  categoriaRepository.findAll()) {
           String nome = categoria.getNome();
           nomes.add(nome);
        }
        return nomes;
    }
    public List<Categoria> findByName(String nomeCategoria){

        List<Categoria> produtosSelecionados = new ArrayList<>();

        for (Categoria categoria :
                categoriaRepository.findAll()) {
            if (categoria.getNome().equalsIgnoreCase(nomeCategoria)) {
                produtosSelecionados.add(categoria);
            }
        }
        return produtosSelecionados;
    }

}
