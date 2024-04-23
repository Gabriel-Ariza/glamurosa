package com.store.api.repository.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "MEDIUMINT UNSIGNED")
    private Long id_cliente;


    @Column(columnDefinition = "VARCHAR(60)", length = 60, nullable = false)
    private String nombres;


    @Column(columnDefinition = "VARCHAR(60)", length = 60, nullable = false)
    private String apellidos;


    @Column(columnDefinition = "VARCHAR(15)", length = 15, nullable = false)
    private String dni;


    @Column(columnDefinition = "VARCHAR(15)", length = 15, nullable = false)
    private String telefono;


    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;


    @JoinColumn(name = "id_usuario", columnDefinition = "MEDIUMINT UNSIGNED")
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Usuario usuario;


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Venta> compras;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }
}


