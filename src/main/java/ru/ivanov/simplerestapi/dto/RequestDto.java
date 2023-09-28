package ru.ivanov.simplerestapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDto {

    @NotBlank
    private String inputString;
}
