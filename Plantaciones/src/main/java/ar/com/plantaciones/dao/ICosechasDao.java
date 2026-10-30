package ar.com.plantaciones.dao;

import ar.com.plantaciones.entities.Cosechas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ICosechasDao extends JpaRepository<Cosechas,Integer> {
    @Query("select c from Cosechas c where c.fecha between ?1 and ?2")
    public List<Cosechas> findByFecha(LocalDate desde, LocalDate hasta);
    Page<Cosechas> findAll(Pageable pageable);


}
