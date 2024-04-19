package com.store.api.repository.entityDTO;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.store.api.repository.entity.Cliente;
import lombok.Data;


@Data
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id_venta"
)
public class VentaDTO {


    private Long id_venta;
    private Date createdAt;
    private BigDecimal total;
    private Cliente cliente;


}