package com.algaworks.algalog.api.dto;

import com.algaworks.algalog.domain.model.Ocorrencia;
import com.algaworks.algalog.domain.model.StatusEntregaEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EntregaDto {

    private Long id;
    private String nomeCliente;
    private DestinatarioDto destinatario;
    private BigDecimal taxa;
    private StatusEntregaEnum status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
    private List<OcorrenciaDto> ocorrencias = new ArrayList<>();

}
