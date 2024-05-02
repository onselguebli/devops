package com.example.demo1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo1.Calcule;

import org.junit.jupiter.api.Test;

class MyFirstJUnitJupiterTests {

    private final Calcule calculator = new Calcule();

    @Test
    void add() {
        assertEquals(2, calculator.add(1, 1));
    }

}