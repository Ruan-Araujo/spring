package br.dh.ecommerce.DHcommerce.repository;

import br.dh.ecommerce.DHcommerce.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
