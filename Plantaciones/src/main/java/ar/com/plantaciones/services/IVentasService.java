package ar.com.plantaciones.services;

import ar.com.plantaciones.entities.Compradores;
import ar.com.plantaciones.entities.Cultivos;
import ar.com.plantaciones.entities.Ventas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IVentasService {

    List<Ventas> getAll();

    Long getCantidadVentas(Integer idComprador,LocalDate fechaDesde,LocalDate fechaHasta);

    List<Ventas> getCompradoresFechas(Compradores compradores, LocalDate fechaDesde, LocalDate fechaHasta);

    Page<Ventas> findAll(Pageable pageable);

    Ventas get(Integer id);

    void save(Ventas entity);

    String delete(Ventas entity);
}
