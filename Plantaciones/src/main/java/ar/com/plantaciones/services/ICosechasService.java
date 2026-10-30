package ar.com.plantaciones.services;

import ar.com.plantaciones.entities.Cosechas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ICosechasService {

    List<Cosechas> getAll();

    Cosechas get(Integer id);

    void save(Cosechas entity);

    String delete(Cosechas entity);

    List<Cosechas> findByFecha(LocalDate desde, LocalDate hasta);

    Page<Cosechas> findAll(Pageable pageable);
}
