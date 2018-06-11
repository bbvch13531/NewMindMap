package MindMap;

import javax.swing.*;
import MindMap.EventListener.ApplyBtnListener;

public class TextEditorPane extends JPanel {
    MindMapPane mindMapPane;
    JTextArea textArea;
    NodeTreeModel nodeTreeModel;
    TextEditorPane(MindMapPane mmp,NodeTreeModel ntm){
        textArea = new JTextArea(20,20);
        JButton applyBtn = new JButton("적용");

        this.mindMapPane = mmp;
        this.nodeTreeModel = ntm;

        applyBtn.addActionListener(new ApplyBtnListener(this, mindMapPane, nodeTreeModel));
        add(textArea);
        add(applyBtn);
        setVisible(true);
    }
    public JTextArea getTextArea(){
        return textArea;
    }
}
