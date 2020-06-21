package br.com.validator.infra.repository;

import br.com.validator.infra.entity.RequisicaoPreco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisicaoPrecoRepository extends JpaRepository<RequisicaoPreco, Long>{

    RequisicaoPreco findByDataFimIsNull();
}
