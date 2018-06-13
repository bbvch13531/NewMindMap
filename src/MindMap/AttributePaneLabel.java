package MindMap;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class AttributePaneLabel extends JPanel{
    AttributePaneLabel()
    {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        Border border = BorderFactory.createLineBorder(Color.BLUE, 3);
        this.setBorder(new EmptyBorder(new Insets(0,0,0,0)));
        JLabel label = new JLabel("Attribute Pane");
        label.setFont(new Font("a", Font.PLAIN, 30));
        this.setBorder(border);
        label.setForeground(Color.white);
        this.setOpaque(false);
        add(label,new FlowLayout(FlowLayout.CENTER));
        setVisible(true);
    }
}
