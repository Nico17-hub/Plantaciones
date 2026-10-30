package ar.com.plantaciones.dao;

import java.util.List;

import ar.com.plantaciones.entities.Plantaciones;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlantacionesDao extends JpaRepository<Plantaciones, Integer> {
    @Query("select c from Plantaciones c where c.periodo like ?1")
    public List<Plantaciones> findByDescrip(String name);

    Page<Plantaciones> findAll(Pageable pageable);
}
