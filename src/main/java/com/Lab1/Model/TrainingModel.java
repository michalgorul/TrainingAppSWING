package com.Lab1.Model;

import com.Lab1.Exceptions.MyException;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The Model class performs all the calculations and data needed
 * and that is it. It doesn't know the View exists
 * @author Michał Góral
 * @version 1.1
 */
public class TrainingModel {

    /**
     * Holds the string of the user's name entered in the view
     */
    private String userName;

    /**
     * Vector of exercises
     */
    private final Vector<Exercise> exercises = new Vector<>();

    /**
     * This method will give us vector of all exercises
     * @return vector of all exercises
     */
    public Vector<Exercise> getExercises() {
        return exercises;
    }

    /**
     * This method will add new exercise to exercises vector
     * @param name name of category
     * @param weight weight to add in exercise
     * @param sets sets to add in exercise
     * @param reps reps to add in exercise
     * @param kilometers kilometers to add in exercise
     */
    public void addExercise(String name, Double weight, Integer sets, Integer reps, Double kilometers){

        try{
            checkDoublesIfNegative(weight,kilometers);
            checkIntegersIfNegative(sets,reps);
            exercises.add(new Exercise(name,weight,sets,reps,kilometers));

        }
        catch (MyException ex){

        }
    }

    /**
     * This method will read categories from file
     * @param name name of file we want to read from
     * @return vector of category names
     */
    public Vector<String> readCategoriesFromFile(String name) {

        Vector<String> categories = new Vector<>();
        try {
            File myObj = new File(name);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                categories.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return categories;
    }

    /**
     * This method will set passed name
     * @param userName name to set
     */
    public void setUserName(String userName){
        this.userName = userName;
    }

    /**
     * This method will give user's name who wants it
     * @return user's name
     */
    public String getUserName(){

        return this.userName;
    }

    /**
     * This method will give us sum of weights from specific category
     * @param arrayName category from which we want to get sum of weights
     * @return sum of weights in specific category
     */
    public Double getSumWeights(String arrayName){

        Double sum = 0.0;

        for(Exercise ex : exercises){

            if(ex.getExerciseName().equals(arrayName)){

                sum += ex.getWeight();
            }

        }
        return sum;
    }

    /**
     * This method will give us sum of sets from specific category
     * I'm using streams here
     * @param arrayName category from which we want to get sum of sets
     * @return sum of sets in specific category
     */
    public Integer getSumSets(String arrayName){

        Stream<Integer> filteredStream = exercises.stream()
                .filter(e -> e.getExerciseName().equals(arrayName))
                .map(Exercise::getSets);

        IntStream intStream = filteredStream.mapToInt(x -> x);

        return intStream.sum();
    }

    /**
     * This method will give us sum of reps from specific category
     * I'm using streams here
     * @param arrayName category from which we want to get sum of reps
     * @return sum of reps in specific category
     */
    public Integer getSumReps(String arrayName){

        Stream<Integer> filteredStream = exercises.stream()
                .filter(e -> e.getExerciseName().equals(arrayName))
                .map(Exercise::getReps);

        IntStream intStream = filteredStream.mapToInt(x -> x);

        return intStream.sum();

    }

    /**
     * This method will give us sum of kilometers from "Running" category
     * @return sum of kilometers in "Running" category
     */
    public Double getSumKilometers() {

        Double sum = 0.0;

        for(Exercise ex : exercises){

            if(ex.getExerciseName().equals("Running")){

                sum += ex.getKilometers();
            }

        }
        return sum;
    }

    /**
     * This method will calculate BMI of user
     * @param height user's height
     * @param weight user's weight
     * @return user's BMI
     */
    public Double calculateBmi(Double height, Double weight){
        double bmi;

       if (height <= 0 || weight <= 0) {
           throw new ArithmeticException("incorrect values");
       }
       else {
           bmi = weight.doubleValue()/((height.doubleValue()/100)*(height.doubleValue()/100));
       }

        return BigDecimal.valueOf(bmi).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * This method will check if user's weight and height are in reasonable bounds
     * @param height user's height
     * @param weight user's weight
     * @throws MyException thrown if height or weight arent proper
     */
    public void checkHeightAndWeight(Double height, Double weight) throws MyException {
        if(height < 1.0 || height > 320.0 || weight < 1.0 || weight > 320.0){

            throw new MyException(height, weight);
        }
    }

    /**
     * This method will check if integers are negative and throw an exception if so
     * @param args values to check
     * @throws MyException thrown if value is negative
     */
    public void checkIntegersIfNegative(Integer... args) throws MyException {

        for (Integer arg : args) {

            if (arg < 0) {
                throw new MyException(arg);
            }
        }

    }

    /**
     * This method will check if integers are negative and throw an exception if so
     * @param args values to check
     * @throws MyException thrown if value is negative
     */
    public void checkDoublesIfNegative(Double... args) throws MyException {

        for (Double arg : args) {

            if (arg < 0) {
                throw new MyException(arg);
            }
        }
    }

}

