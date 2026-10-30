package ar.com.plantaciones.servicesimp;

import ar.com.plantaciones.dao.ICosechasDao;
import ar.com.plantaciones.entities.Cosechas;
import ar.com.plantaciones.entities.Cultivos;
import ar.com.plantaciones.services.ICosechasService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CosechasService implements ICosechasService {

    @Autowired
    private ICosechasDao entityDao;

    public Cosechas get(Integer id){
        return entityDao.findById(id).orElse(null);
    }

    public List<Cosechas> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "fecha"));
    }

    public Page<Cosechas> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "fecha")));
    }
    public List<Cosechas> findByFecha(LocalDate desde, LocalDate hasta) {
        return entityDao.findByFecha(desde, hasta);
    }

    @Transactional
    public void save (Cosechas entity) { entityDao.save(entity); }

    @Transactional

    public String delete (Cosechas entity) {

        try {

            entityDao.delete(entity);
            return null;

        } catch (Exception e) {
            return e.getMessage().toString();
        }

    }

}
