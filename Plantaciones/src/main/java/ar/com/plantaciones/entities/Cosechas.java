package ar.com.plantaciones.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "cosechas")
public class Cosechas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private LocalDate fecha;
    private double rendimientos;

    @ManyToOne
    @JoinColumn(name = "plantaciones_id")
    private Plantaciones plantaciones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getRendimientos() {
        return rendimientos;
    }

    public void setRendimientos(double rendimientos) {
        this.rendimientos = rendimientos;
    }

    public Plantaciones getPlantaciones() {
        return plantaciones;
    }

    public void setPlantaciones(Plantaciones plantaciones) {
        this.plantaciones = plantaciones;
    }
}
