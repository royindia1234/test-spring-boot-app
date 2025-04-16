package com.test.app.test.spring.boot.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.app.test.spring.boot.app.service.DataAnalysisService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DataAnalysisController.class)
public class DataAnalysisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataAnalysisService dataAnalysisService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void calculateAverage_WithValidNumbers_ReturnsAverage() throws Exception {
        // Arrange
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        double expectedAverage = 3.0;
        when(dataAnalysisService.calculateAverage(numbers)).thenReturn(expectedAverage);

        // Act & Assert
        mockMvc.perform(post("/api/data/average")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(numbers)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(expectedAverage)));

        Mockito.verify(dataAnalysisService).calculateAverage(numbers);
    }

    @Test
    public void calculateAverage_WithEmptyList_ReturnsServiceResult() throws Exception {
        // Arrange
        List<Integer> emptyList = Collections.emptyList();
        when(dataAnalysisService.calculateAverage(emptyList)).thenReturn(0.0);

        // Act & Assert
        mockMvc.perform(post("/api/data/average")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emptyList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(0.0)));

        Mockito.verify(dataAnalysisService).calculateAverage(emptyList);
    }

    @Test
    public void findMax_WithValidNumbers_ReturnsMax() throws Exception {
        // Arrange
        List<Integer> numbers = Arrays.asList(1, 5, 3, 9, 2);
        int expectedMax = 9;
        when(dataAnalysisService.findMax(numbers)).thenReturn(expectedMax);

        // Act & Assert
        mockMvc.perform(post("/api/data/max")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(numbers)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(expectedMax)));

        Mockito.verify(dataAnalysisService).findMax(numbers);
    }

    @Test
    public void findMax_WithEmptyList_ReturnsServiceResult() throws Exception {
        // Arrange
        List<Integer> emptyList = Collections.emptyList();
        when(dataAnalysisService.findMax(emptyList)).thenThrow(new IllegalArgumentException("List cannot be empty"));

        // Act & Assert
        mockMvc.perform(post("/api/data/max")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emptyList)))
                .andExpect(status().isBadRequest());

        Mockito.verify(dataAnalysisService).findMax(emptyList);
    }

    @Test
    public void findMin_WithValidNumbers_ReturnsMin() throws Exception {
        // Arrange
        List<Integer> numbers = Arrays.asList(5, 3, 8, 1, 9);
        int expectedMin = 1;
        when(dataAnalysisService.findMin(numbers)).thenReturn(expectedMin);

        // Act & Assert
        mockMvc.perform(post("/api/data/min")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(numbers)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(expectedMin)));

        Mockito.verify(dataAnalysisService).findMin(numbers);
    }

    @Test
    public void findMin_WithEmptyList_ReturnsServiceResult() throws Exception {
        // Arrange
        List<Integer> emptyList = Collections.emptyList();
        when(dataAnalysisService.findMin(emptyList)).thenThrow(new IllegalArgumentException("List cannot be empty"));

        // Act & Assert
        mockMvc.perform(post("/api/data/min")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emptyList)))
                .andExpect(status().isBadRequest());

        Mockito.verify(dataAnalysisService).findMin(emptyList);
    }

    @Test
    public void calculateSum_WithValidNumbers_ReturnsSum() throws Exception {
        // Arrange
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int expectedSum = 15;
        when(dataAnalysisService.calculateSum(numbers)).thenReturn(expectedSum);

        // Act & Assert
        mockMvc.perform(post("/api/data/sum")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(numbers)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(expectedSum)));

        Mockito.verify(dataAnalysisService).calculateSum(numbers);
    }

    @Test
    public void calculateSum_WithEmptyList_ReturnsServiceResult() throws Exception {
        // Arrange
        List<Integer> emptyList = Collections.emptyList();
        when(dataAnalysisService.calculateSum(emptyList)).thenReturn(0);

        // Act & Assert
        mockMvc.perform(post("/api/data/sum")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emptyList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(0)));

        Mockito.verify(dataAnalysisService).calculateSum(emptyList);
    }

    @Test
    public void testAllEndpoints_WithExceptionHandling() throws Exception {
        // Arrange
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        
        // For valid inputs
        when(dataAnalysisService.calculateAverage(numbers)).thenReturn(2.0);
        when(dataAnalysisService.findMax(numbers)).thenReturn(3);
        when(dataAnalysisService.findMin(numbers)).thenReturn(1);
        when(dataAnalysisService.calculateSum(numbers)).thenReturn(6);
        
        // Simulate exception in service for calculateAverage
        when(dataAnalysisService.calculateAverage(anyList())).thenThrow(new RuntimeException("Service error"));
        
        // Act & Assert - Spring's exception handling is expected to map the error to a 500 error response
        mockMvc.perform(post("/api/data/average")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(numbers)))
                .andExpect(status().isInternalServerError());
    }
}
