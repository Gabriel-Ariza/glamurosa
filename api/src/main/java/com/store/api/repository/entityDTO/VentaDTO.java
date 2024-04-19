package com.store.api.repository.entityDTO;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

    @JsonInclude(Include.NON_NULL)
    private ClienteDTO cliente;

    @JsonInclude(Include.NON_NULL)
    private Long id_cliente;


}