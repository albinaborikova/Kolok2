import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Window extends JFrame {

    private final JTextArea inputArea;
    private final JTextArea outputArea;
    private final ArrayList<Toy> toys;
    private Strategy method;

    Window(String name){
        super(name);
        toys = new ArrayList<>();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        inputArea = new JTextArea();
        add(inputArea);
        JButton add = new JButton("Add");
        add(add);
        JButton open = new JButton("Open");
        add(open);
        JButton streamSort = new JButton("Sort by stream");
        add(streamSort);
        JButton algorithmSort = new JButton("Sort by algorithm");
        add(algorithmSort);
        outputArea = new JTextArea();
        add(outputArea);

        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Scanner scanner = new Scanner(JOptionPane.showInputDialog(null, "Enter name, price, ageFrom and ageTo using space."));
                try {
                    toys.add(new Toy(scanner.next(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
                }catch (IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                showInput();
            }
        });

        open.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser(".");
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        Scanner scanner = new Scanner(fileChooser.getSelectedFile());
                        toys.clear();
                        while (scanner.hasNext()){
                            toys.add(new Toy(scanner.next(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
                        }
                        showInput();
                    } catch (IllegalArgumentException | FileNotFoundException | InputMismatchException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        streamSort.addActionListener(e -> {
            sortUsingStrategy(new StreamStrategy());
        });

        algorithmSort.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sortUsingStrategy(new AlgorithmStrategy());
            }
        });
    }

    private void sortUsingStrategy(Strategy s) {
        Scanner scanner = new Scanner(JOptionPane.showInputDialog(null, "Enter ageFrom and ageTo using space."));
        int lowerBound = scanner.nextInt();
        int upperBound = scanner.nextInt();
        try {
            if (!Toy.rightBound(lowerBound, upperBound))
                throw new IllegalArgumentException("Upper bound is less than lower bound");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        setStrategy(s);
        showOutput(method.getSortedArray(toys, lowerBound, upperBound));
    }

    private void setStrategy(Strategy strategy){
        method = strategy;
    }

    public static void main(String[] args){
        Window chooser = new Window("Main");
        chooser.pack();
        chooser.setSize(1100, 400);
        chooser.setLocation(400, 50);
        chooser.setVisible(true);
    }

    private void showOutput(ArrayList<Toy> output){
        StringBuilder stringBuilder = new StringBuilder();
        for (Toy toy : output){
            stringBuilder.append(toy).append("\n");
        }
        outputArea.setText(stringBuilder.toString());
    }

    private void showInput(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Toy toy: toys){
            stringBuilder.append(toy.toString()).append("\n");
        }
        inputArea.setText(stringBuilder.toString());
    }

}
