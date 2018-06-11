package MindMap;

import java.awt.*;
import javax.swing.*;

public class MindMap extends JFrame{
    MenuBar menuBar;
    ToolBar toolBar;
    JSplitPane leftPane,rightPane;
    TextEditorPane textEditorPane;
    MindMapPane mindMapPane;
    AttributePane attributePane;
    NodeTreeModel nodeTreeModel;

    MindMap(){
        setTitle("Mind Map");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 1000, 500);
        nodeTreeModel = new NodeTreeModel();
        RenderLayout();
        setVisible(true);
    }
    void RenderLayout(){

        menuBar = new MenuBar();
        this.setJMenuBar(menuBar);
        toolBar = new ToolBar();

        attributePane = new AttributePane();
        mindMapPane = new MindMapPane(attributePane, nodeTreeModel);
        textEditorPane = new TextEditorPane(mindMapPane, nodeTreeModel);
        attributePane.AddMindMapPane(mindMapPane);

        JSplitPane leftPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JSplitPane rightPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        leftPane.setLeftComponent(new JScrollPane(textEditorPane));
        leftPane.setRightComponent(new JScrollPane(mindMapPane));

        rightPane.setLeftComponent(leftPane);
        rightPane.setRightComponent(attributePane);

        leftPane.setContinuousLayout(true);
        rightPane.setContinuousLayout(true);

        leftPane.setDividerSize(5);
        rightPane.setDividerSize(5);

        add(rightPane,"Center");
        rightPane.setDividerLocation(0.8);

        add(toolBar,BorderLayout.NORTH);
    }
    public static void main(String[] args){
        new MindMap();
    }
}