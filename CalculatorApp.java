/* Author: WinterYeti 
*  Contact: winteryetiy@gmail.com
*  Date: October 18, 2025
   Program: CalculatorApp
   Language: Java
   Description: A program that provides multiple calculator tools in one program.
   File 2 of 5
*/

public class CalculatorApp {
    public static void main(String[] args) {
        // Run GUI on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainMenu();
        });
    }
}
