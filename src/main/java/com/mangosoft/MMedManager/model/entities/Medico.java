package com.mangosoft.MMedManager.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "especialidad", nullable = false, length = 100)
    private String especialidad;

    @Column(name = "matricula", nullable = false, unique = true, length = 50)
    private String matricula;

    @ManyToOne
    @JoinColumn(name = "area_medica_id", nullable = false)
    private AreaMedica areaMedica;

    public Medico() {
    }

    public Medico(Long id, String nombre, String apellido, String especialidad, String matricula,
            AreaMedica areaMedica) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.matricula = matricula;
        this.areaMedica = areaMedica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public AreaMedica getAreaMedica() {
        return areaMedica;
    }

    public void setAreaMedica(AreaMedica areaMedica) {
        this.areaMedica = areaMedica;
    }
}
