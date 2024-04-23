package com.store.api.repository.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "MEDIUMINT UNSIGNED")
    private Long id_usuario;


    @Column(columnDefinition = "VARCHAR(60)", length=60, nullable=false, unique=true)
    private String email;


    @Column(columnDefinition = "VARCHAR(60)", length = 60, nullable = false)
    private String contrasena;


    public enum Rol {
        REGISTRADO,
        MODERADOR,
        ADMIN
    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;


    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Cliente cliente;

}


