package View.ActionListeners;

import View.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class FoodItemAction implements ActionListener {

    protected JTextField foodNameField;
    protected JTextField foodQuantityField;
    protected JSpinner numUnitsField;
    protected String possibleUnits[] = {
            "WHOLE ITEM", "GALLON", "LITER", "CUP", "QUART", "PINT", "MILLILITER", "FLUID OUNCE"
    }; // TODO: ADD THE WEIGHT MEASUREMENTS TO THIS LIST
    protected JComboBox<String> unitField = new JComboBox<>(possibleUnits);

    protected Object[] message;

    @Override
    public void actionPerformed(ActionEvent e) {
        createEntryBoxFields();
        createAdditionalFields();
        initializeFields();
        message = getMessage();
        handleOutput();
    }

    public void createEntryBoxFields() {
        foodNameField = new JTextField();
        foodQuantityField = new JTextField();
        numUnitsField = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        foodNameField.setSize(150, 20); //Increasing one of the field sizes makes the dialog box bigger
        foodNameField.setPreferredSize(new Dimension(150, 20));
    }

    public void handleOutput() {
        int output = JOptionPane.showConfirmDialog(null, message, getTitle(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, MainView.getLogo());
        if (output == JOptionPane.OK_OPTION) {
            processOutput();
        }
    }


    protected abstract void createAdditionalFields();
    protected abstract void initializeFields();
    protected abstract Object[] getMessage();
    protected abstract String getTitle();
    protected abstract void processOutput();


}
