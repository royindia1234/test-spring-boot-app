package com.test.app.test.spring.boot.app.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataAnalysisService {

    public double calculateAverage(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    public int findMax(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow(() -> new IllegalArgumentException("List is empty"));
    }
}