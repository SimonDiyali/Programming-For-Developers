package QuestionNo3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Q3a extends JFrame implements ActionListener {
    private JPanel panel;
    private JTextField inputField;
    private JButton calculateButton;
    private JLabel resultLabel;

    public Q3a() {
        setTitle("Minimum Product Difference");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        inputField = new JTextField(20);
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        resultLabel = new JLabel("Minimum product difference will appear here.");

        panel.add(inputField);
        panel.add(calculateButton);
        panel.add(resultLabel);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            String input = inputField.getText();
            String[] numbers = input.split(",");
            int[] arr = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                arr[i] = Integer.parseInt(numbers[i]);
            }
            int minProductDiff = Integer.MAX_VALUE;
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    int[] a = new int[] {arr[i], arr[j]};
                    int[] b = new int[arr.length - 2];
                    int index = 0;
                    for (int k = 0; k < arr.length; k++) {
                        if (k != i && k != j) {
                            b[index++] = arr[k];
                        }
                    }
                    int productA = 1;
                    for (int k = 0; k < a.length; k++) {
                        productA *= a[k];
                    }
                    int productB = 1;
                    for (int k = 0; k < b.length; k++) {
                        productB *= b[k];
                    }
                    int productDiff = Math.abs(productA - productB);
                    minProductDiff = Math.min(minProductDiff, productDiff);
                }
            }
            resultLabel.setText("Minimum product difference: " + minProductDiff);
        }
    }

    public static void main(String[] args) {
        new Q3a();
    }
}
