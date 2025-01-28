package com.mangosoft.MMedManager.model.entities;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "archivos_historia_clinica")
public class ArchivoHistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "historia_clinica_id", nullable = false)
    private HistoriaClinica historiaClinica;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String tipoArchivo;

    @Lob
    @Column(nullable = false)
    private byte[] datos;

    @Column(nullable = false)
    private LocalDate fechaSubida;

    public ArchivoHistoriaClinica() {
    }

    public ArchivoHistoriaClinica(Long id, HistoriaClinica historiaClinica, String nombre, String tipoArchivo,
            byte[] datos,
            LocalDate fechaSubida) {
        this.id = id;
        this.historiaClinica = historiaClinica;
        this.nombre = nombre;
        this.tipoArchivo = tipoArchivo;
        this.datos = datos;
        this.fechaSubida = fechaSubida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getDatos() {
        return datos;
    }

    public void setDatos(byte[] datos) {
        this.datos = datos;
    }

    public LocalDate getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDate fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }
}
