package QuestionNo4;

import QuestionNo4.LFUCache.LFUCache;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Q4a extends JFrame {

    private final LFUCache<String, String> cache;

    public Q4a() {
        // Set window properties
        setTitle("LFU Cache");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the cache with a capacity of 10
        cache = new LFUCache<String, String>(10);

        // Create components and add to content pane
        // Create GUI components
        JLabel keyLabel = new JLabel("Key:");
        JTextField keyTextField = new JTextField(10);
        JLabel valueLabel = new JLabel("Value:");
        JTextField valueTextField = new JTextField(10);
        JButton putButton = new JButton("Put");
        JButton getButton = new JButton("Get");
        JButton removeButton = new JButton("Remove");
        JList<String> cacheList = new JList<>();

        // Create the model for the cache list
        // Define the model for the cache list
        DefaultListModel<String> cacheListModel = new DefaultListModel<>();
        cacheList.setModel(cacheListModel);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(keyLabel);
        inputPanel.add(keyTextField);
        inputPanel.add(valueLabel);
        inputPanel.add(valueTextField);
        inputPanel.add(putButton);
        inputPanel.add(getButton);
        inputPanel.add(removeButton);
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(cacheList), BorderLayout.CENTER);

        putButton.addActionListener(e -> {
            String key = keyTextField.getText();
            String value = valueTextField.getText();
            // Add the key-value pair to the cache
            cache.put(key, value);
            // Update the list of entries in the cache
            updateCacheList(cacheListModel, cache);
        });

        getButton.addActionListener(e -> {
            String key = keyTextField.getText();
            // Retrieve the value for the specified key
            String value = cache.get(key);
            // Display the value in the value text field
            valueTextField.setText(value);
        });

        removeButton.addActionListener(e -> {
            String key = keyTextField.getText();
            // Remove the entry with the specified key from the cache
            cache.remove(key);
            // Update the list of entries in the cache
            updateCacheList(cacheListModel, cache);
        });

    }

    private static void updateCacheList(DefaultListModel<String> cacheListModel, LFUCache<String, String> cache) {
        // Clear the existing entries from the cache list
        cacheListModel.clear();
        // Add the new entries to the cache list
        for (Map.Entry<String, String> entry : cache.getEntries()) {
            cacheListModel.addElement(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Q4a cacheUI = new Q4a();
        cacheUI.setVisible(true);
    }
}
