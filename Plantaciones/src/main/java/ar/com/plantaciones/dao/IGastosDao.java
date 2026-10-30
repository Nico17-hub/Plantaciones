package ar.com.plantaciones.dao;

import ar.com.plantaciones.entities.Gastos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGastosDao extends JpaRepository<Gastos,Integer> {
    Page<Gastos> findAll(Pageable pageable);
    @Query("select c from Gastos c where c.concepto like ?1")
    public List<Gastos> findByConcepto(String concepto);


}
