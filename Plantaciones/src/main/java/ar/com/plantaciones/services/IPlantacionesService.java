package ar.com.plantaciones.services;

import ar.com.plantaciones.entities.Plantaciones;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPlantacionesService {
    List<Plantaciones> getAll();
    Page<Plantaciones> findAll(Pageable pageable);
    List<Plantaciones> findByDescrip(String descrip);
    Plantaciones get(Integer id);
    void save(Plantaciones entity);
    String delete(Plantaciones entity);
}
