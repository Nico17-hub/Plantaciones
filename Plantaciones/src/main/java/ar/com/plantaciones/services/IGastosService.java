package ar.com.plantaciones.services;

import ar.com.plantaciones.entities.Cultivos;
import ar.com.plantaciones.entities.Gastos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGastosService {

    List<Gastos> getAll();

    Gastos get(Integer id);

    void save(Gastos entity);

    List<Gastos>findByConcepto(String concepto);

    String delete(Gastos entity);

    Page<Gastos> findAll(Pageable pageable);
}
