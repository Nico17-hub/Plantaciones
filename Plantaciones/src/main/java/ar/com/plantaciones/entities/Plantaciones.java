/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plantaciones.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="plantaciones")
public class Plantaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String periodo;
    private LocalDate fecha;
    @JoinColumn(name= "id_cultivo", referencedColumnName = "id", nullable=false)
    @ManyToOne
    private Cultivos cultivo;
    private Double porcentajeLote;
    private LocalDate fechaCierre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cultivos getCultivo() {
        return cultivo;
    }

    public void setCultivo(Cultivos cultivo) {
        this.cultivo = cultivo;
    }

    public Double getPorcentajeLote() {
        return porcentajeLote;
    }

    public void setPorcentajeLote(Double porcentajeLote) {
        this.porcentajeLote = porcentajeLote;
    }

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }
}
