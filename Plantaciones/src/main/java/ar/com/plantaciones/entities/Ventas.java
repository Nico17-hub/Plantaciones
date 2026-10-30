package ar.com.plantaciones.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "ventas")
public class Ventas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate fecha;
    private double cant_vendida;
    private double precio;

    @ManyToOne
    @JoinColumn(name ="plantaciones_id", referencedColumnName = "id", nullable = false)
    private Plantaciones plantaciones;

    @ManyToOne
    @JoinColumn(name = "compradores_id", referencedColumnName = "id", nullable = false)
    private Compradores compradores;

    public Ventas() {}
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

    public double getCant_vendida() {
        return cant_vendida;
    }

    public void setCant_vendida(double cant_vendida) {
        this.cant_vendida = cant_vendida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Plantaciones getPlantaciones() {
        return plantaciones;
    }

    public void setPlantaciones(Plantaciones plantaciones) {
        this.plantaciones = plantaciones;
    }

    public Compradores getCompradores() {
        return compradores;
    }

    public void setCompradores(Compradores compradores) {
        this.compradores = compradores;
    }
}
