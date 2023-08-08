package program;

import javax.swing.*;
import java.awt.*;

public class InputWindow extends JFrame {
    private JButton goButton;
    private JButton cancelButton;
    private JTextField fromField;
    private JTextField toField;
    private JLabel fromLabel;
    private JLabel toLabel;

    public InputWindow(){
        fromLabel = new JLabel("Путь исходной папки:");
        toLabel = new JLabel("Путь конечной папки:");

        fromField = new JTextField();
        toField = new JTextField();

        goButton = new JButton("Ввод");
        cancelButton = new JButton("Выход");

        setLayout(new GridLayout(3, 2));
        add(fromLabel);
        add(fromField);
        add(toLabel);
        add(toField);
        add(goButton);
        add(cancelButton);

        setVisible(true);
        setMinimumSize(new Dimension(700, 100));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setResizable(false);
        setTitle("Распеределение файлов");
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getGoButton() {
        return goButton;
    }

    public JTextField getFromField() {
        return fromField;
    }

    public JTextField getToField() {
        return toField;
    }
}
