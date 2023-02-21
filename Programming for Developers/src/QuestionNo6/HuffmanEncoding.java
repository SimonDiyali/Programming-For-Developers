package QuestionNo6;

import java.util.*;

public class HuffmanEncoding {

    static class Node {
        char ch;
        int freq;
        Node left, right;
    }

    static class HuffmanComparator implements Comparator<Node> {
        public int compare(Node x, Node y) {
            return x.freq - y.freq;
        }
    }

    static void printCode(Node root, String s, Map<Character, String> codes) {
        if (root.left == null && root.right == null && Character.isLetter(root.ch)) {
            codes.put(root.ch, s);
            return;
        }
        printCode(root.left, s + "0", codes);
        printCode(root.right, s + "1", codes);
    }

    static String encode(String input, Map<Character, String> codes) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (codes.containsKey(ch)) {
                output.append(codes.get(ch));
            }
        }
        return output.toString();
    }

    static String decode(String encoded, Node root) {
        StringBuilder output = new StringBuilder();
        Node current = root;
        for (int i = 0; i < encoded.length(); i++) {
            char bit = encoded.charAt(i);
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            if (current.left == null && current.right == null) {
                output.append(current.ch);
                current = root;
            }
        }
        return output.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the input string: ");
        String input = scanner.nextLine();

        int[] freq = new int[256];
        for (int i = 0; i < input.length(); i++) {
            freq[input.charAt(i)]++;
        }

        PriorityQueue<Node> queue = new PriorityQueue<Node>(new HuffmanComparator());
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                Node node = new Node();
                node.ch = (char) i;
                node.freq = freq[i];
                node.left = null;
                node.right = null;
                queue.add(node);
            }
        }

        while (queue.size() > 1) {
            Node x = queue.peek();
            queue.poll();
            Node y = queue.peek();
            queue.poll();
            Node z = new Node();
            z.ch = '-';
            z.freq = x.freq + y.freq;
            z.left = x;
            z.right = y;
            queue.add(z);
        }

        Node root = queue.peek();
        Map<Character, String> codes = new HashMap<Character, String>();
        printCode(root, "", codes);

        String encoded = encode(input, codes);
        String decoded = decode(encoded, root);

        System.out.println("Encoded string: " + encoded);
        System.out.println("Decoded string: " + decoded);
    }
}
