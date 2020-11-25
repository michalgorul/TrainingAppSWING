/**
 * Contains a Controller class for MVC model
 * @author Michał Góral
 * @version 1.0
 */
package com.Lab1.Controler;

import com.Lab1.Exceptions.MyException;
import com.Lab1.Model.TrainingModel;
import com.Lab1.View.TrainingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Controller class coordinates interactions between the View and Model
 * @author Michał Góral
 * @version 1.0
 */
public class TrainingController {

    /**
     * The view object from MVC model
     */
    private final TrainingView theView;

    /**
     * The model object from MVC model
     */
    private final TrainingModel theModel;

    /**
     *  This method handles actions after clicking one of buttons
     * @param theView view object from MVC model
     * @param theModel model object from MVC model
     */
    public TrainingController(TrainingView theView, TrainingModel theModel){

        this.theView = theView;
        this.theModel = theModel;

        // Tell the View that when ever the send button is clicked to execute
        // the actionPerformed method in the NameListener inner class
        this.theView.addNameListener(new NameListener());

        // Tell the View that when ever the saveExercise button is clicked to execute
        // the actionPerformed method in the SaveListener inner class
        this.theView.addSaveExerciseListener(new SaveListener());

        // Tell the View that when ever the showStats button is clicked to execute
        // the actionPerformed method in the StatsListener inner class
        this.theView.showStatisticsListener(new StatsListener());

        // Tell the View that when ever the calculateBmi button is clicked to execute
        // the actionPerformed method in the BmiListener inner class
        this.theView.calculateBmiListener(new BmiListener());
    }

    /**
     * This class implements action handling for saving user's name
     * @author Michał Góral
     * @version 1.0
     */
    class NameListener implements ActionListener{

        //@Override
        /**
         * Invoked when an action occurs.
         * @param e the event to be processed
         */
        public void actionPerformed(ActionEvent e) {

            String name = "";

            // Surround interactions with the view with
            // a try block in case name wasn't properly entered
            try{
                name = theView.getNameFromField();
                theModel.setUserName(name);
                theView.setShowNameField(theModel.getUserName());
                theView.displayNameMessage("You are " + name + "!!!");
            }
            catch(NullPointerException ex){
                theView.displayErrorMessage("You need to enter a name");
            }
        }
    }

    /**
     * This class implements action handling for saving user's exercises
     * @author Michał Góral
     * @version 1.0
     */
    class SaveListener implements ActionListener{

        //@Override
        /**
         * Invoked when an action occurs.
         * @param e the event to be processed
         */
        public void actionPerformed(ActionEvent e) {

            // Surround interactions with the view with
            // a try block in case name wasn't properly entered
            try {
                //theModel.checkValues(theView.getSavedWeight(), theView.getSavedSets(), theView.getSavedReps(), theView.getSavedKilometers());
                theModel.checkIntegersIfNegative(theView.getSavedSets(), theView.getSavedReps());
                theModel.checkDoublesIfNegative(theView.getSavedWeight(), theView.getSavedKilometers());
                theModel.addExercise(theView.getSavedCategory(), theView.getSavedWeight(), theView.getSavedSets(), theView.getSavedReps(), theView.getSavedKilometers());
            } catch (MyException ex) {
                theView.displayErrorMessage(ex.toStringValues());
            }
        }

    }

    /**
     * This class implements action handling for showing user's exercises statistics
     * @author Michał Góral
     * @version 1.0
     */
    class StatsListener implements ActionListener{

        private final String chest = "Chest exercise";
        private final String legs = "Legs exercise";
        private final String back = "Back exercise";
        private final String arms = "Arms exercise";

        //@Override
        /**
         * Invoked when an action occurs.
         * @param e the event to be processed
         */
        public void actionPerformed(ActionEvent e) {

            // Surround interactions with the view with
            // a try block in case name wasn't properly entered
            try{
                setStats();
            }
            catch(ArrayIndexOutOfBoundsException ex){
                theView.displayErrorMessage("Something went wrong");
            }
        }

        private void setStats(){

            theView.setRunningStats(theModel.getSumKilometers());
            theView.setChestStats(theModel.getSumWeights(this.chest),theModel.getSumSets(this.chest),theModel.getSumReps(this.chest));
            theView.setLegsStats(theModel.getSumWeights(this.legs),theModel.getSumSets(this.legs),theModel.getSumReps(this.legs));
            theView.setBackStats(theModel.getSumWeights(this.back),theModel.getSumSets(this.back),theModel.getSumReps(this.back));
            theView.setArmsStats(theModel.getSumWeights(this.arms),theModel.getSumSets(this.arms),theModel.getSumReps(this.arms));
        }
    }

    /**
     * This class implements action handling for calculating user's BMI
     * @author Michał Góral
     * @version 1.0
     */
    class BmiListener implements ActionListener{

        //@Override
        /**
         * Invoked when an action occurs.
         * @param e the event to be processed
         */
        public void actionPerformed(ActionEvent e) {

            // Surround interactions with the view with
            // a try block in case name wasn't properly entered
            try{
                theModel.checkHeightAndWeight(theView.getHeightBmi(), theView.getWeightBmi());
                theView.setBmiField(theModel.calculateBmi(theView.getHeightBmi(), theView.getWeightBmi()));
            }
            catch(MyException ex){
                theView.displayErrorMessage(ex.toStringBmi());
            }
        }
    }
}
