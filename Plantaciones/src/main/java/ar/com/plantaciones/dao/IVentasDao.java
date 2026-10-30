package ar.com.plantaciones.dao;

import ar.com.plantaciones.entities.Compradores;
import ar.com.plantaciones.entities.Cultivos;
import ar.com.plantaciones.entities.Ventas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IVentasDao extends JpaRepository<Ventas,Integer> {


    Page<Ventas> findAll(Pageable pageable);

    @Query("select v from Ventas v where v.compradores = ?1 and v.fecha between ?2 and ?3")
    List<Ventas> getCompradoresFechas(Compradores compradores, LocalDate fechaDesde, LocalDate fechaHasta);

    @Query("select count(v) from Ventas v where v.compradores.id = ?1 and v.fecha between ?2 and ?3")
    Long getCantidadVentas(
            Integer idComprador,
            LocalDate fechaDesde,
            LocalDate fechaHasta);

}
