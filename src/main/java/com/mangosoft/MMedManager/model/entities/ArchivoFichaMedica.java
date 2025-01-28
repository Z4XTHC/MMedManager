package com.mangosoft.MMedManager.model.entities;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "archivos_ficha_medica")
public class ArchivoFichaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ficha_medica_id", nullable = false)
    private FichaMedica fichaMedica;

    @Column(nullable = false)
    private String nombre; // Nombre original del archivo

    @Column(nullable = false)
    private String tipoArchivo; // Tipo MIME (ej. "image/png", "application/pdf")

    @Column(nullable = false)
    private String urlArchivo;

    @Column(nullable = false)
    private LocalDate fechaSubida; // Fecha de subida del archivo

    public ArchivoFichaMedica() {
    }

    public ArchivoFichaMedica(Long id, FichaMedica fichaMedica, String nombre, String tipoArchivo, String urlArchivo,
            LocalDate fechaSubida) {
        this.id = id;
        this.fichaMedica = fichaMedica;
        this.nombre = nombre;
        this.tipoArchivo = tipoArchivo;
        this.urlArchivo = urlArchivo;
        this.fechaSubida = fechaSubida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FichaMedica getFichaMedica() {
        return fichaMedica;
    }

    public void setFichaMedica(FichaMedica fichaMedica) {
        this.fichaMedica = fichaMedica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getUrlArchivo() {
        return urlArchivo;
    }

    public void setUrlArchivo(String urlArchivo) {
        this.urlArchivo = urlArchivo;
    }

    public LocalDate getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDate fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

}
