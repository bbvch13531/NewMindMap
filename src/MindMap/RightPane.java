package MindMap;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class RightPane extends JPanel{
    RightPane(AttributePane attributePane){
        setLayout(new BorderLayout(0,10));
        Border border = BorderFactory.createLineBorder(Color.BLUE, 3);
        AttributePaneLabel label = new AttributePaneLabel();
//			this.setBackground(Color.CYAN);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
//			label.setBorder(border);
        add(label,BorderLayout.NORTH); add(attributePane,BorderLayout.CENTER);
        setVisible(true);
    }
}
