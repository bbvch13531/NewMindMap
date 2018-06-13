package MindMap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import MindMap.EventListener.ApplyBtnListener;

public class TextEditorPane extends JPanel {
    MindMapPane mindMapPane;
    JTextArea textArea;
    NodeTreeModel nodeTreeModel;
    ApplyButton applyBtn;
    ApplyBtnListener applyBtnListener;
    TextEditorPane(MindMapPane mmp,NodeTreeModel ntm){
        setLayout(new BorderLayout());
        TextAreaLabel label = new TextAreaLabel();
        ApplyButton applyBtn = new ApplyButton();

        textArea = new JTextArea(7,20);
        textArea.setBackground(Color.cyan);
        textArea.setFont(new Font("s",Font.BOLD,15));
        textArea.setBorder(new EmptyBorder(new Insets(0,0,0,0)));
        textArea.setTabSize(3);

        this.mindMapPane = mmp;
        this.nodeTreeModel = ntm;

        this.setBackground(Color.CYAN);
        this.setBorder(new EmptyBorder(new Insets(0,0,0,0)));
        applyBtnListener = new ApplyBtnListener(this, mindMapPane, nodeTreeModel);
        applyBtn.btn.addActionListener(applyBtnListener);

        Border border = BorderFactory.createLineBorder(Color.black, 5);
//        this.setBorder(border);
        label.setBorder(border);
        label.setOpaque(true);
        label.setBackground(Color.YELLOW);

        add(label,BorderLayout.NORTH);
        add(new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.CENTER);
        add(applyBtn,BorderLayout.SOUTH);

        setVisible(true);
    }
    public ApplyBtnListener getApplyBtnListener(){return this.applyBtnListener;}
    public JTextArea getTextArea(){
        return textArea;
    }
}
