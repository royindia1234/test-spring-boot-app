package com.test.app.test.spring.boot.app.controller;

import com.test.app.test.spring.boot.app.service.DataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataAnalysisController {

    @Autowired
    private DataAnalysisService dataAnalysisService;

    @PostMapping("/average")
    public double calculateAverage(@RequestBody List<Integer> numbers) {
        return dataAnalysisService.calculateAverage(numbers);
    }

    @PostMapping("/max")
    public int findMax(@RequestBody List<Integer> numbers) {
        return dataAnalysisService.findMax(numbers);
    }

    @PostMapping("/min")
    public int findMin(@RequestBody List<Integer> numbers) {
        return dataAnalysisService.findMin(numbers);
    }

    @PostMapping("/sum")
    public int calculateSum(@RequestBody List<Integer> numbers) {
        return dataAnalysisService.calculateSum(numbers);
    }
}
