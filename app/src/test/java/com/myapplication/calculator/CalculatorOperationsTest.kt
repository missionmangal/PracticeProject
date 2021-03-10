package com.myapplication.calculator

import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`

class CalculatorOperationsTest {

    lateinit var calculator: CalculatorOperations

    @Before
    fun setBefore(){
        calculator = CalculatorOperations()
    }

    @Test
    fun add() {
        val result = calculator.add(10,20)
        assertThat(result, `is`(30))
    }

    @Test
    fun subtraction() {
        val result = calculator.add(10,20)
        assertThat(result, `is`(-10))
    }

    @Test
    fun multiply() {
        val result = calculator.add(10,20)
        assertThat(result, `is`(equalTo(200)))
    }

    @Test
    fun divide() {

        val result = calculator.add(10,20)
        assertThat(result, `is`(equalTo(0)))
    }
}