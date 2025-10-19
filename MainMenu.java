/* Author: WinterYeti 
*  Contact: winteryetiy@gmail.com
*  Date: October 18, 2025
   Program: CalculatorApp
   Language: Java
   Description: A program that provides multiple calculator tools in one program.
   File 1 of 5
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Calculator Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Select a Calculator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Buttons
        JButton businessMathBtn = new JButton("Business Math Calculator");
        JButton basicCalcBtn = new JButton("Basic Calculator");
        JButton measureCalcBtn = new JButton("Measurement Calculator");
        JButton quitBtn = new JButton("Quit");

        // Panel for layout
        JPanel centerPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        centerPanel.add(basicCalcBtn);
        centerPanel.add(businessMathBtn);
        centerPanel.add(measureCalcBtn);
        centerPanel.add(quitBtn);

        add(titleLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        // Button actions
        basicCalcBtn.addActionListener(e -> {
            new BasicCalculator(this);
            setVisible(false);
        });

        businessMathBtn.addActionListener(e -> {
            new BusinessMathCalculator(this);
            setVisible(false);
        });

        measureCalcBtn.addActionListener(e -> {
            new MeasureCalc(this);
            setVisible(false);
        });

        quitBtn.addActionListener(e -> confirmExit());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });

        setVisible(true);
    }

    private void confirmExit() {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to quit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
