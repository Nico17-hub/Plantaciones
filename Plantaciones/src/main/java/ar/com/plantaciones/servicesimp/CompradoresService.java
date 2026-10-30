package ar.com.plantaciones.servicesimp;

import ar.com.plantaciones.dao.ICompradoresDao;
import ar.com.plantaciones.entities.Compradores;
import ar.com.plantaciones.entities.Roles;
import ar.com.plantaciones.services.ICompradoresService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CompradoresService implements ICompradoresService {

    @Autowired

    private ICompradoresDao entityDao;

    public List<Compradores> getAll(){ return entityDao.findAll(Sort.by(Sort.Direction.ASC,"name"));}

    public List<Compradores> findByName(String name){return entityDao.findByName("%" + name + "%");}

    public Compradores get(Integer id) {

        return entityDao.findById(id).orElse(null);

    }
    public Page<Compradores> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "name")));
    }
    @Transactional
    public void save (Compradores entity) {entityDao.save(entity);}

    @Transactional
    public String delete (Compradores entity) {

        try {
            entityDao.delete(entity);
            return null;

        } catch (Exception e) {
            return e.getMessage().toString();
        }

    }


}
