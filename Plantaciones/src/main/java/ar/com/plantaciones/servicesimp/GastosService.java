package ar.com.plantaciones.servicesimp;

import ar.com.plantaciones.dao.IGastosDao;
import ar.com.plantaciones.entities.Cultivos;
import ar.com.plantaciones.entities.Gastos;
import ar.com.plantaciones.services.IGastosService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GastosService implements IGastosService {

    @Autowired
    private IGastosDao entityDao;

    public List<Gastos> getAll(){ return entityDao.findAll(Sort.by(Sort.Direction.ASC,"concepto"));}

    public Gastos get(Integer id) {return entityDao.findById(id).orElse(null);}

    public Page<Gastos> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "concepto")));
    }

    public List<Gastos>findByConcepto(String concepto)  {
        return entityDao.findByConcepto("%" + concepto + "%");}

    @Transactional
    public void save (Gastos entity) {entityDao.save(entity);}

    @Transactional
    public String delete (Gastos entity) {

        try {
            entityDao.delete(entity);
            return null;

        } catch (Exception e) {
            return e.getMessage().toString();
        }

    }







}
