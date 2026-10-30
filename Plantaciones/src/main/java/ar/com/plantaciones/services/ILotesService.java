package ar.com.plantaciones.services;
import ar.com.plantaciones.entities.Lotes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILotesService {

    List<Lotes> getAll();

    List<Lotes> findByDescrip(String descrip);

    Lotes get(Integer id);

    void save(Lotes entity);

    String delete(Lotes entity);

    Page<Lotes> findAll(Pageable pageable);
}
