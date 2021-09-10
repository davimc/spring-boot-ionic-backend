package com.davimc.cursomc.repositories;

import com.davimc.cursomc.domain.Cidade;
import com.davimc.cursomc.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Cidade obj WHERE obj.estado.id = : estado ORDER BY obj.nome")
    List<Cidade> findCidades(Long estado);
}
