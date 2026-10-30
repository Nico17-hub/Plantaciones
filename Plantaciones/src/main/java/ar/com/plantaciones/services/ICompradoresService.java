package ar.com.plantaciones.services;

import ar.com.plantaciones.entities.Compradores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICompradoresService {

    List<Compradores> getAll();

    List<Compradores> findByName(String name);

    Compradores get(Integer id);

    void save(Compradores entity);

    String delete(Compradores entity);

    Page<Compradores> findAll(Pageable pageable);

}
