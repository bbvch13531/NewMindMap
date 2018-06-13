package MindMap;

import javax.swing.*;

import java.awt.*;

public class RightPane extends JPanel{
    RightPane(AttributePane attributePane){
        setLayout(new BorderLayout(0,10));
        AttributePaneLabel label = new AttributePaneLabel();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        add(label,BorderLayout.NORTH); add(attributePane,BorderLayout.CENTER);
        setVisible(true);
    }
}
