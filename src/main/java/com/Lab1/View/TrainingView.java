package com.Lab1.View;

import com.Lab1.Model.TrainingModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * This is the View class
 * Its only job is to display what the user sees and reed user's input
 * It performs no calculations, but instead passes
 * information entered by the user to whomever needs it
 * @author Michał Góral
 * @version 1.0
 */
public class TrainingView extends JFrame{

    /**
     * A toplevel window which can be minimized to an icon.
     */
    JPanel trainPanel;

    /**
     * A component which allows for the editing of a single line of text for name input
     */
    private JTextField getNameField;

    /**
     * An implementation of a "push" button when user wants to save his name
     */
    private JButton sendNameButton;

    /**
     * A component that displays a short string of user's name
     */
    private JLabel showNameLabel;

    /**
     * A single line input field that lets the user select a value of sets from an ordered set
     */
    private JSpinner addSets;

    /**
     * A single line input field that lets the user select a value of reps from an ordered set
     */
    private JSpinner addReps;

    /**
     * A single line input field that lets the user select a value of kilometers from an ordered set
     */
    private JSpinner addKilometers;

    /**
     * An implementation of a "push" button when user wants to save exercise
     */
    private JButton saveExerciseButton;

    /**
     * A combination of a text field and a drop-down list of available categories
     */
    private JComboBox addCategory;

    /**
     * A single line input field that lets the user select a value of kilograms from an ordered set
     */
    private JSpinner addKilograms;

    /**
     * An implementation of a "push" button when user wants to show stats
     */
    private JButton showStatisticsButton;

    /**
     * An implementation of a "push" button when user wants to calculate his BMI
     */
    private JButton calculateBmiButton;

    /**
     * A single line input field that lets the user select a value of user's height from an ordered set
     */
    private JSpinner heightValue;

    /**
     * A single line input field that lets the user select a value of user's weight from an ordered set
     */
    private JSpinner weightValue;

    /**
     * A component that displays a short string with running stats
     */
    private JLabel runningStats;

    /**
     * A component that displays a short string with chest exercises stats
     */
    private JLabel chestStats;

    /**
     * A component that displays a short string with legs exercises stats
     */
    private JLabel legsStats;

    /**
     * A component that displays a short string with back exercises stats
     */
    private JLabel backStats;

    /**
     * A component that displays a short string with arms exercises stats
     */
    private JLabel armsStats;

    /**
     * A component that displays a short string with user's BMI value
     */
    private JLabel bmiValue;

    /**
     * Array of category names
     */
    private Vector<String> categoriesArray;

    private final TrainingModel model = new TrainingModel();

    /**
     * This method sets up the view
     */
    public TrainingView(){
        // Sets up the view and adds the components
        super("Training App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(trainPanel);
        this.setSize(700, 600);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width/2)-(this.getSize().width/2),
                (Toolkit.getDefaultToolkit().getScreenSize().height/2)-(this.getSize().height/2));
        configureComboBox(model.readCategoriesFromFile("C:\\Users\\micha\\IdeaProjects\\TrainingAppSWING\\categories.txt"));
        this.addKilograms.setModel(new SpinnerNumberModel(0.0,-10.0,1000.0,0.1));
        this.addKilometers.setModel(new SpinnerNumberModel(0.0,-10.0,1000.0,0.1));
        this.weightValue.setModel(new SpinnerNumberModel(0.0,-10.0,1000.0,0.1));
        this.heightValue.setModel(new SpinnerNumberModel(0.0,-10.0,1000.0,0.1));

        // End of setting up the components --------
    }

    /**
     * This method will add field to category comboBox from categoriesArray
     * @param categories vector of category names
     */
    private void configureComboBox(Vector<String> categories){
        for(String val : categories)
        {
            addCategory.addItem(val);
        }
    }

    /**
     * This method will give us name of user's input
     * @return name of user
     */
    public String getNameFromField(){

        return getNameField.getText();
    }

    /**
     * This method will set nameField
     * @param name name to set
     */
    public void setShowNameField(String name){

        showNameLabel.setFont(showNameLabel.getFont().deriveFont(28.0f));
        showNameLabel.setText("Hello " + name + " :)");
    }

    /**
     * This method will give us category which urer choose
     * @return name of saved category
     */
    public String getSavedCategory(){

        return addCategory.getSelectedItem().toString();
    }

    /**
     * This method will give us value of weights which user typed
     * @return input weights
     */
    public Double getSavedWeight(){

        return (Double) addKilograms.getValue();
    }

    /**
     * This method will give us value of sets which user typed
     * @return input sets
     */
    public Integer getSavedSets(){

        return (Integer) addSets.getValue();
    }

    /**
     * This method will give us value of reps which user typed
     * @return input reps
     */
    public Integer getSavedReps(){

        return (Integer) addReps.getValue();
    }

    /**
     * This method will give us value of kilometers which user typed
     * @return input kilometers
     */
    public Double getSavedKilometers(){

        return (Double) addKilometers.getValue();
    }

    /**
     * This method will set label in running stats
     * @param km value of kilometers to display
     */
    public void setRunningStats(Double km){
        runningStats.setText("In total you ran " + String.format("%.1f", km) + " kilometers");
    }

    /**
     * This method will set label in chest stats
     * @param weights value of kilograms to display
     * @param sets value of sets to display
     * @param reps value of reps to display
     */
    public void setChestStats(Double weights, Integer sets, Integer reps){
        chestStats.setText("In total you pick up " + String.format("%.1f", weights) + " kilograms on the chest in " +
                sets + " sets and in " + reps + " reps.");
    }

    /**
     * This method will set label in legs stats
     * @param weights value of kilograms to display
     * @param sets value of sets to display
     * @param reps value of reps to display
     */
    public void setLegsStats(Double weights, Integer sets, Integer reps){
        legsStats.setText("In total you pick up " + String.format("%.1f", weights) + " kilograms on the chest in " +
                sets + " sets and in " + reps + " reps.");
    }

    /**
     * This method will set label in back stats
     * @param weights value of kilograms to display
     * @param sets value of sets to display
     * @param reps value of reps to display
     */
    public void setBackStats(Double weights, Integer sets, Integer reps){
        backStats.setText("In total you pick up " + String.format("%.1f", weights) + " kilograms on the chest in " +
                sets + " sets and in " + reps + " reps.");
    }

    /**
     * This method will set label in back stats
     * @param weights value of kilograms to display
     * @param sets value of sets to display
     * @param reps value of reps to display
     */
    public void setArmsStats(Double weights, Integer sets, Integer reps){
        armsStats.setText("In total you pick up " + String.format("%.1f", weights) + " kilograms on the chest in " +
                sets + " sets and in " + reps + " reps.");
    }

    /**
     * This method will get us user's height from input
     * @return user's height
     */
    public Double getHeightBmi(){

        return (Double) heightValue.getValue();
    }

    /**
     * This method will get us user's weight from input
     * @return user's weight
     */
    public Double getWeightBmi(){

        return (Double) weightValue.getValue();
    }

    /**
     * This method will set label in user's BMI
     * @param bmi user's BMI value
     */
    public void setBmiField(Double bmi){

        bmiValue.setText("You have " + bmi + " BMI points");
    }

    /**
     * This method will execute a method in the Controller named actionPerformed
     * if the sendNameButton is clicked
     * @param listenForNameButton object handling nameButton clicked
     */
    public void addNameListener(ActionListener listenForNameButton){

        sendNameButton.addActionListener(listenForNameButton);
    }

    /**
     * This method will execute a method in the Controller named actionPerformed
     * if the saveExerciseButton is clicked
     * @param listenForSaveButton object handling saveButton clicked
     */
    public void addSaveExerciseListener(ActionListener listenForSaveButton){

        saveExerciseButton.addActionListener(listenForSaveButton);
    }

    /**
     * This method will execute a method in the Controller named actionPerformed
     * if the showStatisticsButton is clicked
     * @param listenForShowStatisticsButton object handling showStatsButton clicked
     */
    public void showStatisticsListener(ActionListener listenForShowStatisticsButton){

        showStatisticsButton.addActionListener(listenForShowStatisticsButton);
    }

    /**
     * This method will execute a method in the Controller named actionPerformed
     * if the calculateBmiButton is clicked
     * @param listenForCalculateBmiButton object handling calculateBmiButton clicked
     */
    public void calculateBmiListener(ActionListener listenForCalculateBmiButton){

        calculateBmiButton.addActionListener(listenForCalculateBmiButton);
    }

    /**
     * This method will open a popup that contains the error message passed
     * @param errorMessage message to display
     */
    public void displayErrorMessage(String errorMessage){

        JOptionPane.showMessageDialog(this, errorMessage);
    }

    /**
     * This method will open a popup that contains the message with name passed
     * @param message message with name to display
     */
    public void displayNameMessage(String message){

        JOptionPane.showMessageDialog(this, message);
    }

}