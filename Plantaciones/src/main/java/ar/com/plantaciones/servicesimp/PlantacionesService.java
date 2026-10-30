package ar.com.plantaciones.servicesimp;

import java.util.List;

import ar.com.plantaciones.dao.IPlantacionesDao;
import ar.com.plantaciones.entities.Plantaciones;
import ar.com.plantaciones.services.IPlantacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlantacionesService implements IPlantacionesService {

    @Autowired
    private IPlantacionesDao entityDao;

    public List<Plantaciones> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "periodo"));
    }

    public Page<Plantaciones> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "periodo")));
    }

    public List<Plantaciones> findByDescrip(String descrip) {
        return entityDao.findByDescrip("%" + descrip + "%");
    }

    public Plantaciones get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Plantaciones entity) {
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Plantaciones entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
