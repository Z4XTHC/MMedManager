package com.mangosoft.MMedManager.model.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER) // Carga los roles al traer un usuario
    @JoinTable(name = "usuario_roles", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "usuario_id"), // Clave foránea en usuario_roles hacia Usuario
            inverseJoinColumns = @JoinColumn(name = "rol_id") // Clave foránea en usuario_roles hacia Rol
    )
    private Set<Rol> roles = new HashSet<>();

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Medico medico;

    private Boolean activo;

    public Usuario() {
        activo = true;
    }

    public Usuario(Long id, String username, String password, String email, Set<Rol> roles, Medico medico,
            Boolean activo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.medico = medico;
        this.activo = activo;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public void addRol(Rol rol) {
        this.roles.add(rol);
    }

    public void removeRol(Rol rol) {
        this.roles.remove(rol);
    }

}
