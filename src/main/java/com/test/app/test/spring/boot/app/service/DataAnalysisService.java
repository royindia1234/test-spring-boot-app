package com.test.app.test.spring.boot.app.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataAnalysisService {

    /**
     * Calculates the average value of a list of integers.
     *
     * @param numbers the list of integers to average
     * @return the average as a double, or 0.0 if the list is empty
     */
    public double calculateAverage(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    /****
     * Returns the maximum integer from the provided list.
     *
     * @param numbers the list of integers to search
     * @return the largest integer in the list
     * @throws IllegalArgumentException if the list is empty
     */
    public int findMax(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow(() -> new IllegalArgumentException("List is empty"));
    }

    /****
     * Returns the minimum integer in the provided list.
     *
     * @param numbers the list of integers to search
     * @return the smallest integer in the list
     * @throws IllegalArgumentException if the list is empty
     */
    public int findMin(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .min()
                .orElseThrow(() -> new IllegalArgumentException("List is empty"));
    }

    /**
     * Calculates the sum of all integers in the provided list.
     *
     * @param numbers the list of integers to sum
     * @return the total sum of the integers, or 0 if the list is empty
     */
    public int calculateSum(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
