package ar.com.plantaciones.services;

import ar.com.plantaciones.entities.Cultivos;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICultivosService {
    List<Cultivos> getAll();
    Page<Cultivos> findAll(Pageable pageable);
    List<Cultivos> findByDescrip(String descrip);
    Cultivos get(Integer id);
    void save(Cultivos entity);
    String delete(Cultivos entity);
}
