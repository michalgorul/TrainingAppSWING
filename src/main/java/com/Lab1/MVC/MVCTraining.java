/**
 * Contains a MVCTraining class with main method
 * @author Michał Góral
 * @version 1.0
 */
package com.Lab1.MVC;

import com.Lab1.Controler.TrainingController;
import com.Lab1.Model.TrainingModel;
import com.Lab1.View.TrainingView;

/**
 * The connecting MVC model class containing main method
 * @author Michał Góral
 * @version 1.0
 */
public class MVCTraining {
    /**
     * The main method
     * @param args i dont use calling parameter
     */
    public static void main(String[] args) {

        TrainingView theView = new TrainingView();
        TrainingModel theModel = new TrainingModel();
        TrainingController theController = new TrainingController(theView, theModel);

        theView.setVisible(true);
    }
}
