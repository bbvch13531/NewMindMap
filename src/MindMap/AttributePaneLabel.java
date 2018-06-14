package MindMap;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class AttributePaneLabel extends JPanel{
    AttributePaneLabel()
    {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        Border border = BorderFactory.createLineBorder(Color.black, 3);
        this.setBorder(new EmptyBorder(new Insets(0,0,0,0)));
        this.setBackground(Color.decode("0x62A3E8"));

        JLabel label = new JLabel("Attribute Pane");
        label.setFont(new Font("a", Font.PLAIN, 30));
        label.setForeground(Color.black);
        label.setBackground(Color.decode("0x62A3E8"));

        this.setBorder(border);
        add(label,new FlowLayout(FlowLayout.CENTER));
        setVisible(true);
    }
}
