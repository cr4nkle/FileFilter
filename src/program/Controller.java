package program;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.NoSuchFileException;

public class Controller {
    private FileSorter fileSorter;

    public void execute(InputWindow window){
        window.getGoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String fromDir = window.getFromField().getText();
                String toDir = window.getToField().getText();
                File file = null;

                if (!fromDir.isEmpty() && !toDir.isEmpty()){
                    fileSorter = new FileSorter(toDir);
                    file = new File(fromDir);

                    try{
                        fileSorter.getFiles(file);
                        JOptionPane.showMessageDialog(window, "Распределение файлов окончено!");
                    }catch (NoSuchFileException e){
                        JOptionPane.showMessageDialog(window, "Вы ввели неверный путь!!");
                    }
                }else {
                    JOptionPane.showMessageDialog(window, "Вы не ввели путь!!");
                }
            }
        });

        window.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.dispose();
            }
        });
    }
}
