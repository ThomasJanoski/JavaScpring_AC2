package com.facens.ac1_parte2.Repositories;

import com.facens.ac1_parte2.Entities.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiretorRepository extends JpaRepository<Diretor, Long> {
    List<Diretor> findByNomeStartingWith(String prefix);

    @Query("SELECT d FROM Diretor d LEFT JOIN FETCH d.filmes WHERE d.id = :id")
    Diretor findDiretorByIdWithFilmes(Integer id);
}