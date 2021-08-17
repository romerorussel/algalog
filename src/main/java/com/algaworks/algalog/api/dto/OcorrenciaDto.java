package com.algaworks.algalog.api.dto;

import com.algaworks.algalog.domain.model.Entrega;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class OcorrenciaDto {

    private Long id;
    private String descricao;
    private OffsetDateTime dataRegistro;

}
