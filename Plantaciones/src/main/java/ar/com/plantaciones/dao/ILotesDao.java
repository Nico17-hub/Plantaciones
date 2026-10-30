package ar.com.plantaciones.dao;

import ar.com.plantaciones.entities.Lotes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILotesDao extends JpaRepository<Lotes,Integer> {

    @Query("select c from Lotes c where c.descrip like ?1")
    public List<Lotes> findByDescrip(String name);

    Page<Lotes> findAll(Pageable pageable);
}
