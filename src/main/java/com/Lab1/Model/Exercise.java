package com.Lab1.Model;

/**
 * This is the Exercise class
 * It contains information about a exercise
 * and provides us to set those values
 * @author Michał Góral
 * @version 1.0
 */
public class Exercise {

    private final String exerciseName;

    private final Double weight;
    private final Integer sets;
    private final Integer reps;
    private final Double kilometers;

    public Exercise(String name, Double weight, Integer sets, Integer reps, Double kilometers){

        this.exerciseName = name;
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
        this.kilometers = kilometers;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getSets() {
        return sets;
    }

    public Integer getReps() {
        return reps;
    }

    public Double getKilometers() {
        return kilometers;
    }
}
