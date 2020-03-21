package com.myapplication.calculator

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/*Here, we have annotated with MockitoJUnitRunner::class that means it provides the Runner to run the test.*/
@RunWith(MockitoJUnitRunner::class)
class CalculatorTest {
    lateinit var calculator: Calculator
    @Mock
    lateinit var operators:Operators

    @Before
    fun onSetup(){
//        val c = mock(Calculator(operators)::class.java)
        calculator = Calculator(operators)
    }

    @Test
    fun addOperatorTest(){
        val a = 20
        val b = 30
        calculator.addTwoNumbers(a,b)
        verify(operators).add(a,b)
    }


    @Test
    fun givenValidInput_whenAdd_shouldCallAddOperator() {
        val a = 10
        val b = 20
//        calculator.addTwoNumbers(a, b)
        `when`(operators.add(10,20)).thenReturn(30)
//        print(calculator.addTwoNumbers(a, b))
        Assert.assertEquals(60,calculator.addTwoNumbers(a, b))
        verify(operators).add(a, b)

    }

    @Test
    fun givenValidInput_whenSubtract_shouldCallSubtractOperator() {
        val a = 10
        val b = 20
        calculator.subtractTwoNumbers(a, b)
        verify(operators).subtract(a, b)
        print("hi")
    }
    @Test
    fun givenValidInput_whenMultiply_shouldCallMultiplyOperator() {
        val a = 10
        val b = 20
        calculator.multiplyTwoNumbers(a, b)

        verify(operators).multiply(a, b)

    }
    @Test
    fun givenValidInput_whenDivide_shouldCallDivideOperator() {
        val a = 10
        val b = 20
        calculator.divideTwoNumbers(a, b)
        verify(operators).divide(a, b)

    }
}