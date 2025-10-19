/* Author: WinterYeti 
*  Contact: winteryetiy@gmail.com
*  Date: October 18, 2025
   Program: CalculatorApp
   Language: Java
   Description: A program that provides multiple calculator tools in one program.
   File 3 of 5
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BusinessMathCalculator extends JFrame {

    private MainMenu mainMenu; // Reference to go back to main menu
    private JTextArea resultArea;

    public BusinessMathCalculator(MainMenu menu) {
        this.mainMenu = menu;

        setTitle("Business Math Calculator");
        setSize(550, 550);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Business Math Calculator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(15));

        // Tabs for each calculator section
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Markup / Margin", createMarkupPanel());
        tabs.add("Discount", createDiscountPanel());
        tabs.add("Simple Interest", createInterestPanel());
        tabs.add("Break-even Point", createBreakEvenPanel());
        tabs.add("Loan Calculator", createLoanPanel());  // <-- NEW TAB
        mainPanel.add(tabs);

        // Result display
        resultArea = new JTextArea(6, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setBorder(BorderFactory.createTitledBorder("Results"));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(new JScrollPane(resultArea));

        // Back button
        JButton backBtn = new JButton("Back to Menu");
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.addActionListener(e -> {
            dispose();
            mainMenu.setVisible(true);
        });

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(backBtn);

        // Window close behavior
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                backBtn.doClick();
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    // --- MARKUP / MARGIN ---
    private JPanel createMarkupPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField costField = new JTextField();
        JTextField priceField = new JTextField();
        JButton calcBtn = new JButton("Calculate");

        calcBtn.addActionListener(e -> {
            try {
                double cost = Double.parseDouble(costField.getText());
                double price = Double.parseDouble(priceField.getText());
                double markup = ((price - cost) / cost) * 100;
                double margin = ((price - cost) / price) * 100;
                resultArea.setText(String.format(
                    "Markup: %.2f%%\nMargin: %.2f%%", markup, margin));
            } catch (Exception ex) {
                showError("Please enter valid numbers for cost and price.");
            }
        });

        panel.add(new JLabel("Cost:"));
        panel.add(costField);
        panel.add(new JLabel("Selling Price:"));
        panel.add(priceField);
        panel.add(new JLabel(""));
        panel.add(calcBtn);

        return panel;
    }

    // --- DISCOUNT ---
    private JPanel createDiscountPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField priceField = new JTextField();
        JTextField discountField = new JTextField();
        JButton calcBtn = new JButton("Calculate");

        calcBtn.addActionListener(e -> {
            try {
                double price = Double.parseDouble(priceField.getText());
                double discountPercent = Double.parseDouble(discountField.getText());
                double discountAmount = price * (discountPercent / 100);
                double finalPrice = price - discountAmount;
                resultArea.setText(String.format(
                    "Discount Amount: $%.2f\nFinal Price: $%.2f", discountAmount, finalPrice));
            } catch (Exception ex) {
                showError("Please enter valid numbers for price and discount.");
            }
        });

        panel.add(new JLabel("Original Price:"));
        panel.add(priceField);
        panel.add(new JLabel("Discount (%):"));
        panel.add(discountField);
        panel.add(new JLabel(""));
        panel.add(calcBtn);

        return panel;
    }

    // --- SIMPLE INTEREST ---
    private JPanel createInterestPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        JTextField principalField = new JTextField();
        JTextField rateField = new JTextField();
        JTextField timeField = new JTextField();
        JButton calcBtn = new JButton("Calculate");

        calcBtn.addActionListener(e -> {
            try {
                double p = Double.parseDouble(principalField.getText());
                double r = Double.parseDouble(rateField.getText());
                double t = Double.parseDouble(timeField.getText());
                double interest = (p * r * t) / 100;
                resultArea.setText(String.format(
                    "Interest: $%.2f\nTotal Amount: $%.2f", interest, p + interest));
            } catch (Exception ex) {
                showError("Please enter valid numbers for principal, rate, and time.");
            }
        });

        panel.add(new JLabel("Principal ($):"));
        panel.add(principalField);
        panel.add(new JLabel("Rate (% per year):"));
        panel.add(rateField);
        panel.add(new JLabel("Time (years):"));
        panel.add(timeField);
        panel.add(new JLabel(""));
        panel.add(calcBtn);

        return panel;
    }

    // --- BREAK-EVEN ---
    private JPanel createBreakEvenPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        JTextField fixedField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField variableField = new JTextField();
        JButton calcBtn = new JButton("Calculate");

        calcBtn.addActionListener(e -> {
            try {
                double fixed = Double.parseDouble(fixedField.getText());
                double price = Double.parseDouble(priceField.getText());
                double variable = Double.parseDouble(variableField.getText());
                double breakeven = fixed / (price - variable);
                resultArea.setText(String.format("Break-even Point: %.2f units", breakeven));
            } catch (Exception ex) {
                showError("Please enter valid numbers for costs and price.");
            }
        });

        panel.add(new JLabel("Fixed Costs ($):"));
        panel.add(fixedField);
        panel.add(new JLabel("Selling Price per Unit ($):"));
        panel.add(priceField);
        panel.add(new JLabel("Variable Cost per Unit ($):"));
        panel.add(variableField);
        panel.add(new JLabel(""));
        panel.add(calcBtn);

        return panel;
    }

    // --- LOAN CALCULATOR (NEW) ---
    private JPanel createLoanPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField principalField = new JTextField();
        JTextField rateField = new JTextField();
        JTextField yearsField = new JTextField();
        JButton calcBtn = new JButton("Calculate");

        calcBtn.addActionListener(e -> {
            try {
                double principal = Double.parseDouble(principalField.getText());
                double annualRate = Double.parseDouble(rateField.getText());
                double years = Double.parseDouble(yearsField.getText());

                double monthlyRate = annualRate / 100 / 12;
                int months = (int)(years * 12);
                double monthlyPayment = (principal * monthlyRate) /
                        (1 - Math.pow(1 + monthlyRate, -months));

                double totalPayment = monthlyPayment * months;
                double totalInterest = totalPayment - principal;

                resultArea.setText(String.format(
                    "Monthly Payment: $%.2f\nTotal Payment: $%.2f\nTotal Interest: $%.2f",
                    monthlyPayment, totalPayment, totalInterest));
            } catch (Exception ex) {
                showError("Please enter valid numbers for loan inputs.");
            }
        });

        panel.add(new JLabel("Loan Amount ($):"));
        panel.add(principalField);
        panel.add(new JLabel("Annual Interest Rate (%):"));
        panel.add(rateField);
        panel.add(new JLabel("Loan Term (years):"));
        panel.add(yearsField);
        panel.add(new JLabel(""));
        panel.add(calcBtn);

        return panel;
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}
