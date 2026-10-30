package ar.com.plantaciones.dao;

import ar.com.plantaciones.entities.Compradores;
import ar.com.plantaciones.entities.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICompradoresDao extends JpaRepository<Compradores,Integer> {

    @Query("select c from Compradores c where c.name like ?1")
    public List<Compradores> findByName(String name);

    Page<Compradores> findAll(Pageable pageable);
}
