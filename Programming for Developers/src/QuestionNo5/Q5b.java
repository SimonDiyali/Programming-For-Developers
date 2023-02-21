package QuestionNo5;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Q5b extends JFrame implements ActionListener {
    private JLabel startLabel, targetLabel, centersLabel;
    private JTextField startField, targetField, centersField;
    private JTextArea resultArea;
    private JButton calculateButton;

    public Q5b() {
        setTitle("Electric Vehicle Battery Replacement");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(500, 500);

        startLabel = new JLabel("Start Charge Capacity: ");
        startLabel.setBounds(50, 50, 150, 20);
        add(startLabel);

        startField = new JTextField();
        startField.setBounds(200, 50, 100, 20);
        add(startField);

        targetLabel = new JLabel("Target Miles: ");
        targetLabel.setBounds(50, 80, 150, 20);
        add(targetLabel);

        targetField = new JTextField();
        targetField.setBounds(200, 80, 100, 20);
        add(targetField);

        centersLabel = new JLabel("Service Centers (e.g., 10,60;20,30;30,30;60,40): ");
        centersLabel.setBounds(50, 110, 300, 20);
        add(centersLabel);

        centersField = new JTextField();
        centersField.setBounds(50, 130, 350, 20);
        add(centersField);

        resultArea = new JTextArea();
        resultArea.setBounds(50, 200, 350, 100);
        resultArea.setEditable(false);
        add(resultArea);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(200, 150, 100, 20);
        calculateButton.addActionListener(this);
        add(calculateButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Q5b();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            int startChargeCapacity = Integer.parseInt(startField.getText());
            int targetMiles = Integer.parseInt(targetField.getText());

            String[] centerStrings = centersField.getText().split(";");
            int[][] serviceCenters = new int[centerStrings.length][2];
            for (int i = 0; i < centerStrings.length; i++) {
                String[] centerInfo = centerStrings[i].split(",");
                serviceCenters[i][0] = Integer.parseInt(centerInfo[0]);
                serviceCenters[i][1] = Integer.parseInt(centerInfo[1]);
            }

            int distanceLeft = startChargeCapacity;
            int batteryReplacements = 0;

            for (int i = 0; i < serviceCenters.length; i++) {
                int distanceToNext = i == serviceCenters.length - 1 ? targetMiles - serviceCenters[i][0] : serviceCenters[i+1][0] - serviceCenters[i][0];
                int capacityAtCenter = serviceCenters[i][1];

                if (distanceLeft < distanceToNext) {
                    distanceLeft = capacityAtCenter;
                    batteryReplacements++;
                } else {
                    distanceLeft -= distanceToNext;
                }

                if (distanceLeft < 0) {
                    distanceLeft = capacityAtCenter - Math.abs(distanceLeft);
                    batteryReplacements++;
                }
            }

            resultArea.setText("Number of Battery Replacements: " + batteryReplacements);
        }
    }


}
