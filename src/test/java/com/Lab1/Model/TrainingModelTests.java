package com.Lab1.Model;

import com.Lab1.Exceptions.MyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Vector;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class responsible of doing tests on my model class
 * @author Michał Góral
 * @version 1.0
 */
public class TrainingModelTests {

    /**
     * A model object from MVC model
     */
    private TrainingModel model;

    /**
     * This method will be processed before each test and it creates an object of TrainingModel class
     */
    @BeforeEach
    public void setUp() {

        model = new TrainingModel();

    }

    /** This test will check if method calculateBmi calculates BMI correctly. If there will be a division by 0 this method
     * should throw ArithmeticException exception. It takes various heights and one weight
     * @param height heights to check
     */
    @ParameterizedTest
    @ValueSource(doubles = { 100.0 , 1.0, 3.0, 100.0, 300.0, 1000.0, 0.0, -1.0, -764834.0}) // add ""
    public void calculateBmiWithHeightTest(Double height) {

        Double bmi;

        try{
            bmi = 100.0/((height.doubleValue()/100)*(height.doubleValue()/100));
            assertEquals(bmi,model.calculateBmi(height,100.0), 0.01, "Variable values differ by more than 0.01!");
        }
        catch (ArithmeticException ex){

        }
    }

    /**
     * This test will check if method calculateBmi calculates BMI correctly. If there will be a division by 0 this method
     * should throw ArithmeticException exception. It takes various weights and one height
     * @param weight weights to check
     */
    @ParameterizedTest
    @ValueSource(doubles = { 100.0 , 1.0, 3.0, 100.0, 300.0, 1000.0, 0.0, -1.0, -764834.0}) // add ""
    public void calculateBmiWithWeightTest(Double weight) {

        Double bmi;

        try{
            bmi = weight/4.0;
            assertEquals(bmi,model.calculateBmi(200.0,weight), 0.01, "Variable values differ by more than 0.01!");
        }
        catch (ArithmeticException ex){

        }
    }

    /**
     * This test will check if method calculateBmi calculates BMI correctly. If there will be a division by 0 this method
     * should throw ArithmeticException exception. It takes various weights and heights
     * @param height heights to check
     * @param weight weights to check
     */
    @ParameterizedTest
    @CsvSource({"1.0,2.0","2.0,1.0","200.0,120.0","0.0,520.0","400.0,0.0","0.0,0.0","-200.0,120.0","200.0,-120.0","-200.0,-120.0"})
    public void calculateBmiWithHeightAndWeightTest(Double height, Double weight) {

        Double bmi;

        try{
            bmi = weight / ((height /100) * (height /100));
            assertEquals(bmi,model.calculateBmi(height,weight), 0.01, "Variable values differ by more than 0.01!");
        }
        catch (ArithmeticException ex){

        }
    }

    /**
     * This test will check if method checkHeightAndWeight works correctly. Only numbers from 1.0 to 320.0 should be processed.
     * Otherwise this method should throw a MyException exception
     * @param height heights to check
     */
    @ParameterizedTest
    @ValueSource(doubles = { -100.0, 1.0, 3.0, 100.0, 320.0, 1000.0, 0.0}) // add ""
    public void checkHeightTest(Double height) {
        try{
            model.checkHeightAndWeight(height, 80.0);
        }
        catch(MyException ex){

        }
    }

    /**
     * This test will check if method checkHeightAndWeight works correctly. Only numbers from 1.0 to 320.0 should be processed.
     * Otherwise this method should throw a MyException exception
     * @param weight weights to check
     */
    @ParameterizedTest
    @ValueSource(doubles = { -100.0, 1.0, 3.0, 100.0, 320.0, 1000.0, 0.0}) // add ""
    public void checkWeightTest(Double weight) {
        try{
            model.checkHeightAndWeight(80.0, weight);
        }
        catch(MyException ex){

        }
    }

    /**
     * This test will check if method checkHeightAndWeight works correctly. Only numbers from 1.0 to 320.0 should be processed.
     * Otherwise this method should throw a MyException exception
     * @param weight weights to check
     * @param height heights to check
     */
    @ParameterizedTest
    @CsvSource({"1.0,2.0","2.0,1.0","200.0,120.0","0.0,520.0","400.0,0.0","0.0,0.0","-200.0,120.0","200.0,-120.0","-200.0,-120.0"})
    public void checkHeightAndWeightTest(Double height, Double weight) {
        try{
            model.checkHeightAndWeight(height, weight);
        }
        catch(MyException ex){

        }
    }

    /**
     * This test will check if method checkIntegersIfNegative checks if number is negative correctly.
     * If a number is negative it should throw a MyException exception
     * @param arg numbers to check
     */
    @ParameterizedTest
    @ValueSource(ints = { -100 , -1, 0, -234, -67, 23, -2135667, 30, 0}) // add ""
    public void checkIntegersIfNegative(Integer arg) {

        try{
            model.checkIntegersIfNegative(arg);
        }
        catch(MyException ex){

        }
    }

    /**
     * This test will check if method checkDoublesIfNegative checks if number is negative correctly.
     * If a number is negative it should throw a MyException exception
     * @param arg numbers to check
     */
    @ParameterizedTest
    @ValueSource(doubles = { -100.0 , -1.0, 0.0, -2134.0, -123780.0, 1.1, 0.0}) // add ""
    public void checkDoublesIfNegative(Double arg) {

        try{
            model.checkDoublesIfNegative(arg);
        }
        catch(MyException ex){
        }
    }

    /**
     * This test will check if method readCategoriesFromFile is reading correctly
     * In the existing file there is only one string and its value is "test"
     * @param str string to compare with the text in the file
     */
    @ParameterizedTest
    @ValueSource(strings = { "test" }) // add ""
    public void readCategoriesFromExistingFileCorrectString(String str){

        Vector<String> test = model.readCategoriesFromFile("C:\\Users\\micha\\IdeaProjects\\TrainingApp\\abc.txt");
        assertEquals(str, test.get(0),"the strings differ");

    }

    /**
     * This test will check if method readCategoriesFromFile is reading correctly
     * In the existing file there is only one string and its value is "test"
     * @param str string to compare with the text in the file
     */
    @ParameterizedTest
    @ValueSource(strings = { "test1", "test%%%" }) // add ""
    public void readCategoriesFromExistingFileIncorrectString(String str){

        Vector<String> test = model.readCategoriesFromFile("C:\\Users\\micha\\IdeaProjects\\TrainingApp\\abc.txt");
        assertNotEquals(str, test.get(0),"the strings differ");

    }
    /**
     * This test will check if method readCategoriesFromFile handles reading from not existing file
     * It should throw an FileNotFoundException every time
     * @param str names of files to check
     */
    @ParameterizedTest
    @ValueSource(strings = { "test", "test1", "test%%%" }) // add ""
    public void readCategoriesFromNotExistingFile(String str){

        Vector<String> test = model.readCategoriesFromFile(str);
    }

    /**
     * This test will check, if method addExercise checks if double values are negative, correctly.
     * If a number is negative it should throw a MyException exception
     * @param val values to check
     */
    @ParameterizedTest
    @ValueSource(doubles = { -100.0 , -1.0, 0.0, -2134.0, -123780.0, 1.1, 0.0}) // add ""
    public void addExerciseTestNegativeDoubles(Double val) {

        model.addExercise("test", val, 10, 10, val);

    }
    /**
     * This test will check, if method addExercise checks if integer values are negative, correctly.
     * If a number is negative it should throw a MyException exception
     * @param val values to check
     */
    @ParameterizedTest
    @ValueSource(ints = { -100 , -1, 0, -234, -67, 23, -2135667, 0}) // add ""
    public void addExerciseTestNegativeIntegers(Integer val) {

        model.addExercise("test",10.0, val, val, 10.0);
    }

    /**
     * This test will check, if method addExercise checks if double values are positive, correctly.
     * If a number is negative it should not throw any  exception
     * @param val values to check
     */
    @ParameterizedTest
    @ValueSource(doubles = { 100.0 , 2134.0, 123780.0, 1.1}) // add ""
    public void addExerciseTestPositiveDoubles(Double val) {

        try{
            model.addExercise("test", val, 10, 10, val);
        }
        catch (Exception ex){

        }
    }

    /**
     * This test will check, if method addExercise checks if integer values are positive, correctly.
     * If a number is negative it should not throw any exception
     * @param val values to check
     */
    @ParameterizedTest
    @ValueSource(ints = { 100 , 234, 67, 23, 2135667, 30, 3}) // add ""
    public void addExerciseTestPositiveIntegers(Integer val) {

        try{
            model.addExercise("test",10.0, val, val, 10.0);
        }
        catch (Exception ex){

        }
    }
    
    /**
     * This test will check, if method getSumWeights sums up weights correctly
     * @param val values to check
     */
    @ParameterizedTest
    @ValueSource(doubles = { 100.0 , 2134.0, 123780.0, 1.1}) // add ""
    public void getSumWeights(Double val) {

        model.addExercise("name", val, 0,0,0.0);
        model.addExercise("name", val, 0,0,0.0);
        model.addExercise("name", val, 0,0,0.0);

        if(val > 0.0){
            assertEquals(val*3, model.getSumWeights("name"), "summing went wrong");
        }
        else{
            assertEquals(0.0, model.getSumWeights("name"), "summing went wrong");
        }
    }

    /**
     * This test will check, if method getSumSets sums up sets correctly
     * @param val values to check
     */
    @ParameterizedTest
    @ValueSource(ints = { 100 , 234, 67, 23, 2135667, 30, 3, 0, -1234}) // add ""
    public void getSumSets(Integer val) {

        model.addExercise("name", 0.0, val,0,0.0);
        model.addExercise("name", 0.0, val,0,0.0);
        model.addExercise("name", 0.0, val,0,0.0);

        if(val > 0){
            assertEquals(val*3, model.getSumSets("name"), "summing went wrong");
        }
        else{
            assertEquals(0, model.getSumSets("name"), "summing went wrong");
        }
    }

    /**
     * This test will check, if method getSumSets sums up reps correctly
     * @param val values to check
     */
    @ParameterizedTest
    @ValueSource(ints = { 100 , 234, 67, 23, 2135667, 30, 3, 0, -123}) // add ""
    public void getSumReps(Integer val) {

        model.addExercise("name", 0.0, 0,val,0.0);
        model.addExercise("name", 0.0, 0,val,0.0);
        model.addExercise("name", 0.0, 0,val,0.0);

        if(val > 0){
            assertEquals(val*3, model.getSumReps("name"), "summing went wrong");
        }
         else{
            assertEquals(0, model.getSumReps("name"), "summing went wrong");
        }
    }

    /**
     * This test will check, if method getSumWeights sums up kilometers correctly
     * @param val values to check
     */
    @ParameterizedTest
    @ValueSource(doubles = { 100.0 , 2134.0, 123780.0, 1.1, -1.3, 0.0}) // add ""
    public void getSumKilometers(Double val) {

        model.addExercise("Running", 0.0, 0,0, val);
        model.addExercise("Running", 0.0, 0,0, val);
        model.addExercise("Running", 0.0, 0,0, val);

        if(val > 0.0){
            assertEquals(val*3, model.getSumKilometers(), "summing went wrong");
        }
        else{
            assertEquals(0.0, model.getSumKilometers(), "summing went wrong");
        }
    }
}