package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BMICalculatorTest {
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("Before all unit tests !");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("After all unit tests !");
	}
	
	// check with @ParameterizedTest
	@ParameterizedTest
	@ValueSource(doubles = {89.0, 95.0, 110.})
	void should_ReturnTrue_When_DieTRecommended_withParameters(double coderweight) {
	//given
	double weight = coderweight;
	double height = 1.72;
	//when
	Boolean recommended = BMICalculator.isDietRecommended(weight, height);
		
	// then
	assertTrue(recommended);
	}
	
	// check with @ParameterizedTest with @CsvSource
	@ParameterizedTest(name = "weight={0}, height={1}")
	@CsvSource(value = {"89.0, 1.72", "95.0, 1.75","110. , 1.78"})
	void should_ReturnTrue_When_DieTRecommended_withCsvSource(double coderweight, double coderHeight) {
	//given
	double weight = coderweight;
	double height = coderHeight;
	//when
	Boolean recommended = BMICalculator.isDietRecommended(weight, height);
		
	// then
	assertTrue(recommended);
	}
		
	// check with @ParameterizedTest with 
	@ParameterizedTest(name = "weight={0}, height={1}")
	@CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
	void should_ReturnTrue_When_DieTRecommended_withCsvFileSource(double coderweight, double coderHeight) {
	//given
	double weight = coderweight;
	double height = coderHeight;
	//when
	Boolean recommended = BMICalculator.isDietRecommended(weight, height);
		
	// then
	assertTrue(recommended);
	}	
	
    // check if the value returned is true
	@Test
	void should_ReturnTrue_When_DieTRecommended() {
	//assertTrue(BMICalculator.isDietRecommended(89.0, 1.72));
	//given
	double weight = 89.0;
	double height = 1.72;
	//when
	Boolean recommended = BMICalculator.isDietRecommended(weight, height);
	
	// then
	assertTrue(recommended);
	}
	
	
	// check the value returned is false
	@Test
	void should_ReturnFalse_When_DieTNotRecommended() {
		//assertTrue(BMICalculator.isDietRecommended(89.0, 1.72));
		//given
		double weight = 50.0;
		double height = 1.92;
		//when
		Boolean recommended = BMICalculator.isDietRecommended(weight, height);
		
		// then
		assertFalse(recommended);
		}
	
	
	// check if the exception will be triggered if the value of height is equal to zero
	@Test
	void should_ThrowArithmeticException_When_HeightZero() {
	    // given
	    double weight = 50.0;  // Weight in kg
	    double height = 0.0;   // Height in meters (0 is invalid)

	    // when
	    Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

	    // then
	    // Expecting an ArithmeticException to be thrown due to division by zero
	    assertThrows(ArithmeticException.class, executable);
	}
	
	
	// check case if the list is not empty find the worst coders 
	@Test
	void should_ReturnCoderWithWorstBMI_When_CoderListIsNotEmpty() {
	    // given
	    List<Coder> coders = new ArrayList<>();
	    coders.add(new Coder(1.80,60.0));
	    coders.add(new Coder(1.82,98.0));
	    coders.add(new Coder(1.82,64.7));
	    // when
        Coder coderWithWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
	    // then
	    assertAll(
	    	() -> assertEquals(1.82, coderWithWorstBMI.getHeight()),
	    	() -> assertEquals(98.0, coderWithWorstBMI.getWeight())
	    );
	   
	    
	}
	
	// check the case if the list is empty the value returned is Null
	@Test
	void should_ReturnCoderWithWorstBMI_When_CoderListEmpty() {
	    // given
	    List<Coder> coders = new ArrayList<>();
	    // when
        Coder coderWithWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
	    // then
	    assertNull(coderWithWorstBMI);
	   
	}
	
	// check the equality of an array
	@Test
	void should_ReturnCorrectBMIscoreArray_When_CoderListNotEmpty() {
		// given
	    List<Coder> coders = new ArrayList<>();
	    coders.add(new Coder(1.80,60.0));
	    coders.add(new Coder(1.82,98.0));
	    coders.add(new Coder(1.82,64.7));
	    double[] expected = {18.52, 29.59,19.53};
	    // when
        double[] bmiScores = BMICalculator.getBMIScores(coders);
	    // then
	    assertArrayEquals(expected, bmiScores);
	    
	   
	}
	
}
