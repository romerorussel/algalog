package com.algaworks.algalog.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ErrorObject {

    private Integer status;
    private OffsetDateTime dateTime;
    private String description;
    private List<Field> fieldList;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Field {
        private String fieldName;
        private String message;
    }


}
