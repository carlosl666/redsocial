package com.caamal.ms.usuarios.entity;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "ROLES")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROLE")
    private Long idRole;

    @Column(unique = true)
    private String nombre;

    //@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    //private List<Usuario> usuarios;

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}