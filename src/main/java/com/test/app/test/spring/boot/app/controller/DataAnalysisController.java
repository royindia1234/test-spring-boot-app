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

    /**
     * Returns the maximum value from a list of integers provided in the request body.
     *
     * @param numbers the list of integers to analyze
     * @return the maximum integer in the list
     */
    @PostMapping("/max")
    public int findMax(@RequestBody List<Integer> numbers) {
        return dataAnalysisService.findMax(numbers);
    }

    /**
     * Returns the minimum value from a list of integers provided in the request body.
     *
     * @param numbers the list of integers to analyze
     * @return the smallest integer in the list
     */
    @PostMapping("/min")
    public int findMin(@RequestBody List<Integer> numbers) {
        return dataAnalysisService.findMin(numbers);
    }

    /**
     * Calculates and returns the sum of a list of integers provided in the request body.
     *
     * @param numbers the list of integers to sum
     * @return the total sum of the input integers
     */
    @PostMapping("/sum")
    public int calculateSum(@RequestBody List<Integer> numbers) {
        return dataAnalysisService.calculateSum(numbers);
    }
}
