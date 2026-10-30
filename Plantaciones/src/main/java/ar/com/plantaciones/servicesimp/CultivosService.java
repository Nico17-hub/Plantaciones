package ar.com.plantaciones.servicesimp;

import java.util.List;

import ar.com.plantaciones.dao.ICultivosDao;
import ar.com.plantaciones.entities.Cultivos;
import ar.com.plantaciones.services.ICultivosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CultivosService implements ICultivosService {

    @Autowired
    private ICultivosDao entityDao;

    public List<Cultivos> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "descripcion"));
    }

    public Page<Cultivos> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descripcion")));
    }

    public List<Cultivos> findByDescrip(String descrip) {
        return entityDao.findByDescrip("%" + descrip + "%");
    }

    public Cultivos get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Cultivos entity) {
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Cultivos entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
