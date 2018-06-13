package MindMap;

import MindMap.EventListener.MenuToolListener;

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
    CenterPane centerPane;
    RightPane right;

    public MindMap(){
        setTitle("Mind Map");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 1800, 800);
        nodeTreeModel = new NodeTreeModel();
        RenderLayout();
        setVisible(true);
    }
    void RenderLayout(){

        attributePane = new AttributePane();
        mindMapPane = new MindMapPane(attributePane, nodeTreeModel);
        centerPane = new CenterPane(mindMapPane);
        textEditorPane = new TextEditorPane(mindMapPane, nodeTreeModel);
        right = new RightPane(attributePane);
        attributePane.AddMindMapPane(mindMapPane);

        MenuToolListener menuToolListener = new MenuToolListener(this, mindMapPane, textEditorPane, attributePane, nodeTreeModel);
        menuBar = new MenuBar(menuToolListener);
        this.setJMenuBar(menuBar);
        toolBar = new ToolBar(menuToolListener);

        JSplitPane leftPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JSplitPane rightPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        add(rightPane,"Center");
        setVisible(true);

        leftPane.setLeftComponent(textEditorPane);
        leftPane.setRightComponent(new JScrollPane(centerPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        rightPane.setLeftComponent(leftPane);
        rightPane.setRightComponent(right);

        leftPane.setContinuousLayout(true);
        rightPane.setContinuousLayout(true);

        leftPane.setDividerSize(0);
        rightPane.setDividerSize(5);

        rightPane.setDividerLocation(0.8);

        add(toolBar,BorderLayout.NORTH);
    }
    public static void main(String[] args){
        new MindMap();
    }
}