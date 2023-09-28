package ru.ivanov.simplerestapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.ivanov.simplerestapi.controller.SimpleApiController;
import ru.ivanov.simplerestapi.service.SimpleApiService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SimpleApiController.class)
@AutoConfigureMockMvc
public class SimpleApiControllerTest {

    @MockBean
    private SimpleApiService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetCharacterRepetitions() throws Exception {
        String inputString = "“aaaaabcccc";
        String responseString = "a: 5, c: 4, b: 1";
        when(service.getCharacterRepetitionsResult(inputString)).thenReturn(responseString);

        mockMvc.perform(post("/")
                        .content(String.format("{\"inputString\": \"%s\"}", inputString))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("a: 5, c: 4, b: 1"));
        verify(service, times(1)).getCharacterRepetitionsResult(inputString);
    }

    @Test
    void testGetCharacterRepetitionsWithMultipleWordsString() throws Exception {
        String inputString = "“aaaaa b cccc";

        String responseString = "a: 5, c: 4, b: 1";

        when(service.getCharacterRepetitionsResult(inputString)).thenReturn(responseString);

        mockMvc.perform(post("/")
                        .content(String.format("{\"inputString\": \"%s\"}", inputString))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("a: 5, c: 4, b: 1"));
        verify(service, times(1)).getCharacterRepetitionsResult(inputString);
    }

    @Test
    void shouldFailOnEmptyString() throws Exception {
        String emptyString = "";

        mockMvc.perform(post("/")
                        .content(emptyString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(service, times(0)).getCharacterRepetitionsResult(emptyString);
    }

    @Test
    void shouldFailOnWhiteSpacedOnlyString() throws Exception {
        String blankString = " ";

        mockMvc.perform(post("/")
                        .content(blankString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(service, times(0)).getCharacterRepetitionsResult(blankString);
    }
}
