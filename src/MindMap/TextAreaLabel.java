package MindMap;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TextAreaLabel extends JPanel{
    TextAreaLabel(){
        setLayout(new FlowLayout());
        JLabel label = new JLabel("Text Editor Pane");
        this.setBorder(new EmptyBorder(new Insets(0,0,0,0)));
        this.setBackground(Color.decode("0x6862e8"));

        label.setFont(new Font("a", Font.PLAIN, 30));
        label.setForeground(Color.black);
        label.setBackground(Color.decode("0x6862e8"));

        this.setBackground(Color.CYAN);

        add(label,new FlowLayout(FlowLayout.CENTER));
        setVisible(true);
    }

}
