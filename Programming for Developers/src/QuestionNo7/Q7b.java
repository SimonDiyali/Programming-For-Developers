package QuestionNo7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Q7b extends JFrame {

    private final JTextField urlField;
    private final JTextArea resultArea;
    private final JButton startButton;
    private final WebCrawler webCrawler;

    public Q7b() {
        super("Web Crawler");

        urlField = new JTextField("https://www.example.com", 20);
        startButton = new JButton("Start");
        resultArea = new JTextArea(10, 40);

        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("URL:"));
        inputPanel.add(urlField);
        inputPanel.add(startButton);
        contentPanel.add(inputPanel, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);
        setContentPane(contentPanel);

        webCrawler = new WebCrawler(4, resultArea);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startUrl = urlField.getText();
                webCrawler.crawl(startUrl);
                startButton.setEnabled(false);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Q7b();
            }
        });
    }
}

class WebCrawler {

    private final int numThreads;
    private final BlockingQueue<String> linkQueue = new LinkedBlockingQueue<>();
    private final Set<String> visitedLinks = new HashSet<>();
    private final JTextArea resultArea;

    public WebCrawler(int numThreads, JTextArea resultArea) {
        this.numThreads = numThreads;
        this.resultArea = resultArea;
    }

    public void crawl(String startUrl) {
        linkQueue.add(startUrl);

        for (int i = 0; i < numThreads; i++) {
            new Thread(new CrawlerWorker()).start();
        }
    }

    private class CrawlerWorker implements Runnable {

        @Override
        public void run() {
            while (true) {
                String link = null;
                try {
                    link = linkQueue.take();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                if (!visitedLinks.contains(link)) {
                    visitedLinks.add(link);
                    String pageContent = retrievePageContent(link);
                    Set<String> links = extractLinks(pageContent);

                    String finalLink = link;
                    SwingUtilities.invokeLater(() -> {
                        resultArea.append(finalLink + " - " + links.size() + " links found\n");
                    });

                    for (String newLink : links) {
                        linkQueue.offer(newLink);
                    }
                }
            }
        }

        private String retrievePageContent(String link) {
            // Implement code to retrieve page content from the URL
            return "";
        }

        private Set<String> extractLinks(String pageContent) {
            // Implement code to extract links from the page content
            return new HashSet<>();
        }
    }
}
