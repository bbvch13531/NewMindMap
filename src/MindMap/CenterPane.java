package MindMap;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class CenterPane extends JPanel{
    CenterPane(MindMapPane panel){
        setLayout(new BorderLayout());
//		JLabel label = new JLabel("Mind Map Pane",JLabel.CENTER);
        MindMapLabel label = new MindMapLabel();
        Border border = BorderFactory.createLineBorder(Color.black, 3);
        this.setBorder(new EmptyBorder(new Insets(0,0,0,0)));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        label.setBorder(border);
//		label.setOpaque(true);
//		label.setBackground(Color.yellow);
//		label.setFont(new Font("MindMap",Font.BOLD,20));

        add(label,BorderLayout.NORTH);
        add(new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.CENTER);

        setVisible(true);
    }

}
