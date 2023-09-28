package ru.ivanov.simplerestapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ru.ivanov.simplerestapi.dto.RequestDto;
import ru.ivanov.simplerestapi.service.SimpleApiService;

@RestController
@RequestMapping
@Validated
@RequiredArgsConstructor
@Slf4j
public class SimpleApiController {

    private final SimpleApiService apiService;

    @PostMapping
    @Operation(summary = "Получение частоты встречи символов переданной строки",
            description = "Возвращает количество повторений символов переданной строки в виде \"a: 5, c: 4, b: 1\" для входной строки \"aaaaabcccc\"")
    @Parameter(name = "inputString",
            description = "Входная строка, для которой нужно получить в ответ количество повторений символов в ней, отсортированных по убыванию",
            example = "aaaaabcccc")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ОК"),
            @ApiResponse(responseCode = "400", description = "Передана строка не проходящая валидацию (пустая или состоящая только из пробелов)")})
    public ResponseEntity<String> getCharacterRepetitions(@RequestBody @Valid RequestDto input) {
        log.info("Get number of character repetitions for input string {}", input.getInputString());
        return ResponseEntity.ok(apiService.getCharacterRepetitionsResult(input.getInputString()));
    }
}
