package br.com.autoStock.api.autoStock_api.repository;

import br.com.autoStock.api.autoStock_api.model.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    
    Optional<Fornecedor> findByCnpj(String cnpj);
    
    Page<Fornecedor> findByAtivoTrue(Pageable pageable);
    
    Page<Fornecedor> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}