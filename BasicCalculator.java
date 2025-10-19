/* Author: WinterYeti 
*  Contact: winteryetiy@gmail.com
*  Date: October 18, 2025
   Program: CalculatorApp
   Language: Java
   Description: A program that provides multiple calculator tools in one program.
   File 5 of 5
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BasicCalculator extends JFrame implements ActionListener {

    private MainMenu mainMenu; // Reference to main menu
    private JTextField displayField;
    private StringBuilder input;

    public BasicCalculator(MainMenu menu) {
        this.mainMenu = menu;
        input = new StringBuilder();

        setTitle("Basic Calculator");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        // Display field
        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(displayField, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5)); // 5 rows now
        String[] buttons = {
            "7","8","9","/",
            "4","5","6","*",
            "1","2","3","-",
            "0",".","=","+",
            "C"  // Clear button
        };
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(this);
            buttonPanel.add(btn);
        }
        add(buttonPanel, BorderLayout.CENTER);

        // Bottom panel for Back button
        JPanel bottomPanel = new JPanel();
        JButton backBtn = new JButton("Back to Menu");
        backBtn.addActionListener(e -> {
            dispose();
            mainMenu.setVisible(true);
        });
        bottomPanel.add(backBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // Window close behavior
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                backBtn.doClick();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "=":
                try {
                    double result = evaluate(input.toString());
                    displayField.setText(String.valueOf(result));
                    input.setLength(0);
                } catch (Exception ex) {
                    displayField.setText("Error");
                    input.setLength(0);
                }
                break;
            case "C":  // Clear button
                input.setLength(0);
                displayField.setText("");
                break;
            case "+": case "-": case "*": case "/": case ".":
                input.append(command);
                displayField.setText(input.toString());
                break;
            default: // numbers
                input.append(command);
                displayField.setText(input.toString());
        }
    }

    // Evaluate a simple expression containing +, -, *, /
    private double evaluate(String expr) throws Exception {
        if (expr.isEmpty()) return 0;

        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder number = new StringBuilder();

        for (char c : expr.toCharArray()) {
            if ("+-*/".indexOf(c) >= 0) {
                if (number.length() == 0) {
                    throw new Exception("Invalid syntax");
                }
                tokens.add(number.toString());
                tokens.add(String.valueOf(c));
                number.setLength(0);
            } else {
                number.append(c);
            }
        }
        tokens.add(number.toString());

        // First pass: * and /
        for (int i = 0; i < tokens.size(); i++) {
            String t = tokens.get(i);
            if (t.equals("*") || t.equals("/")) {
                double a = Double.parseDouble(tokens.get(i - 1));
                double b = Double.parseDouble(tokens.get(i + 1));
                double res = t.equals("*") ? a * b : a / b;
                tokens.set(i - 1, Double.toString(res));
                tokens.remove(i); // operator
                tokens.remove(i); // second operand
                i--; // stay at current index
            }
        }

        // Second pass: + and -
        double result = Double.parseDouble(tokens.get(0));
        for (int i = 1; i < tokens.size(); i += 2) {
            String op = tokens.get(i);
            double num = Double.parseDouble(tokens.get(i + 1));
            if (op.equals("+")) result += num;
            else result -= num;
        }

        return result;
    }
}
