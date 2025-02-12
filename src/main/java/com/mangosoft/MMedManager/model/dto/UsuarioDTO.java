package com.mangosoft.MMedManager.model.dto;

import java.util.HashSet;
import java.util.Set;

public class UsuarioDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Set<Long> rolesIds = new HashSet<>();

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String username, String email, String password, Set<Long> rolesIds) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.rolesIds = rolesIds;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Long> getRolesIds() {
        return rolesIds;
    }

    public void setRolesIds(Set<Long> rolesIds) {
        this.rolesIds = rolesIds;
    }

}
