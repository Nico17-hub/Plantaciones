package ar.com.plantaciones.servicesimp;

import ar.com.plantaciones.dao.IVentasDao;
import ar.com.plantaciones.entities.Compradores;
import ar.com.plantaciones.entities.Cultivos;
import ar.com.plantaciones.entities.Ventas;
import ar.com.plantaciones.services.IVentasService;
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
public class VentasService implements IVentasService {

    @Autowired
    private IVentasDao entityDao;

    public List<Ventas> getAll(){ return entityDao.findAll(Sort.by(Sort.Direction.ASC,"id"));}

    public Ventas get(Integer id) {

        return entityDao.findById(id).orElse(null);

    }

    public Page<Ventas> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "id")));
    }

    public Long getCantidadVentas(Integer idComprador, LocalDate fechaDesde, LocalDate fechaHasta)
    {
        return  entityDao.getCantidadVentas(idComprador, fechaDesde, fechaHasta);
    }

    public List<Ventas> getCompradoresFechas(Compradores compradores, LocalDate fechaDesde, LocalDate fechaHasta)
    {
        return entityDao.getCompradoresFechas(compradores, fechaDesde, fechaHasta);
    }

    @Transactional
    public void save (Ventas entity) {entityDao.save(entity);}

    @Transactional
    public String delete (Ventas entity) {

        try {
            entityDao.delete(entity);
            return null;

        } catch (Exception e) {
            return e.getMessage().toString();
        }

    }

}
