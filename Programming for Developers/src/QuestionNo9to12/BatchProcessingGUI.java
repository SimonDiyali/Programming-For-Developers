package QuestionNo9to12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BatchProcessingGUI extends JFrame {

    private JList<String> taskList;
    private DefaultListModel<String> taskListModel;
    private JButton newJobButton, removeButton, resumeButton;
    private int selectedTaskIndex = -1;

    public BatchProcessingGUI() {
        // Set the title of the window
        setTitle("Batch Processing GUI");

        // Create a panel for the main screen
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a list to display the tasks
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                // Enable/disable the "Remove" and "Resume" buttons
                selectedTaskIndex = taskList.getSelectedIndex();
                removeButton.setEnabled(selectedTaskIndex >= 0);
                resumeButton.setEnabled(selectedTaskIndex >= 0);
            }
        });

        // Add the list to the main panel
        mainPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

        // Create a "New Job" button
        newJobButton = new JButton("New Job");
        newJobButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show the "New Job" dialog and add the new job to the task list
                String taskName = JOptionPane.showInputDialog("Enter the task name:");
                if (taskName != null && !taskName.trim().isEmpty()) {
                    taskListModel.addElement(taskName);
                }
            }
        });

        // Create a "Remove" button
        removeButton = new JButton("Remove");
        removeButton.setEnabled(false);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Remove the selected task from the task list
                if (selectedTaskIndex >= 0) {
                    taskListModel.remove(selectedTaskIndex);
                }
            }
        });

        // Create a "Resume" button
        resumeButton = new JButton("Resume");
        resumeButton.setEnabled(false);
        resumeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Resume the selected task from the position where it failed
                if (selectedTaskIndex >= 0) {
                    String taskName = taskListModel.getElementAt(selectedTaskIndex);
                    JOptionPane.showMessageDialog(null, "Resuming task: " + taskName, "Task Resumed", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Add the buttons to the button panel
        buttonPanel.add(newJobButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(resumeButton);

        // Add the button panel to the main panel
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the window
        add(mainPanel);

        // Set the size and make the window visible
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create a new instance of the window
        BatchProcessingGUI frame = new BatchProcessingGUI();

        // Exit the application when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
