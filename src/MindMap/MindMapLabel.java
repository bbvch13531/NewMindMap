package MindMap;

import javax.swing.*;
import java.awt.*;

public class MindMapLabel extends JPanel{
    MindMapLabel(){
        setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("Mind Map Pane");
        label.setFont(new Font("a", Font.PLAIN, 30));
        label.setForeground(Color.black);
        this.setBackground(Color.decode("0x7997FF"));

        add(label,new FlowLayout(FlowLayout.CENTER));
        setVisible(true);
    }
}