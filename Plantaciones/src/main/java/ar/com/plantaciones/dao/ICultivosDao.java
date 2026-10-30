package ar.com.plantaciones.dao;

import java.util.List;
import ar.com.plantaciones.entities.Cultivos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICultivosDao extends JpaRepository<Cultivos, Integer> {
    @Query("select c from Cultivos c where c.descripcion like ?1")
    public List<Cultivos> findByDescrip(String name);

    Page<Cultivos> findAll(Pageable pageable);
}
