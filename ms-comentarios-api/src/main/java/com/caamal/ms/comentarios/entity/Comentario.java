package com.caamal.ms.comentarios.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "COMENTARIOS")
public class Comentario {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(updatable = false)
    private String id;

    private String comentario;

    @Column(name = "ID_PUBLICACION")
    private String idPublicacion;

    @Column(name = "FECHA_CREACION")
    private String fechaCreacion;


}
