/* Author: WinterYeti 
*  Contact: winteryetiy@gmail.com
*  Date: October 18, 2025
   Program: CalculatorApp
   Language: Java
   Description: A program that provides multiple calculator tools in one program.
   File 4 of 5
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MeasureCalc extends JFrame implements ActionListener {

    private JFrame parent;
    private JComboBox<String> conversionType;
    private JTextField inputField;
    private JLabel resultLabel;
    private JButton convertButton, clearButton, backButton;

    public MeasureCalc(JFrame parent) {
        this.parent = parent;
        setTitle("Measurement Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 5, 5));

        add(new JLabel("Select Conversion Type:"));
        String[] conversions = {
            "Inches to Centimeters",
            "Centimeters to Inches",
            "Feet to Meters",
            "Meters to Feet",
            "Gallons to Liters",
            "Liters to Gallons",
            "Pounds to Kilograms",
            "Kilograms to Pounds"
        };
        conversionType = new JComboBox<>(conversions);
        add(conversionType);

        add(new JLabel("Enter Value:"));
        inputField = new JTextField();
        add(inputField);

        convertButton = new JButton("Convert");
        clearButton = new JButton("Clear");
        backButton = new JButton("Back to Menu");

        add(convertButton);
        add(clearButton);
        add(backButton);

        resultLabel = new JLabel("Result: ", SwingConstants.CENTER);
        add(resultLabel);

        convertButton.addActionListener(this);
        clearButton.addActionListener(this);
        backButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            try {
                double value = Double.parseDouble(inputField.getText());
                double result = 0.0;
                String selected = (String) conversionType.getSelectedItem();

                switch (selected) {
                    case "Inches to Centimeters": result = value * 2.54; break;
                    case "Centimeters to Inches": result = value / 2.54; break;
                    case "Feet to Meters": result = value * 0.3048; break;
                    case "Meters to Feet": result = value / 0.3048; break;
                    case "Gallons to Liters": result = value * 3.78541; break;
                    case "Liters to Gallons": result = value / 3.78541; break;
                    case "Pounds to Kilograms": result = value * 0.453592; break;
                    case "Kilograms to Pounds": result = value / 0.453592; break;
                }

                resultLabel.setText(String.format("Result: %.4f", result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            }

        } else if (e.getSource() == clearButton) {
            inputField.setText("");
            resultLabel.setText("Result: ");

        } else if (e.getSource() == backButton) {
            dispose();
            parent.setVisible(true);  // ✅ return to main menu
        }
    }
}
