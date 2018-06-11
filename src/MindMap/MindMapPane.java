package MindMap;

import MindMap.EventListener.NodeSelectListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MindMapPane extends JPanel {
    AttributePane attributePane;
    public NodeTreeModel nodeTreeModel;
    public ArrayList <JLabel> labelArray;

    MindMapPane(AttributePane abp, NodeTreeModel ntm){
        setLayout(null);
        this.attributePane = abp;
        this.nodeTreeModel = ntm;
        labelArray = new ArrayList<>();
    }

    public void updateNodeTreeModel(NodeTreeModel ntm){
        this.nodeTreeModel = ntm;
    }

    // test Method
    public void draw(){
        NodeSelectListener nodeSelectListener = new NodeSelectListener(attributePane,this);
        this.addMouseListener(nodeSelectListener);
        this.addMouseMotionListener(nodeSelectListener);
        int size = nodeTreeModel.getSize();
//        JLabel []labelArray = new JLabel[size];

        ArrayList <Node> nodeArray;
        if(labelArray.size()!=0){
            System.out.println(labelArray.size());
            for(int i=0;i<labelArray.size();i++){
                this.remove(labelArray.get(i));
            }
            labelArray.clear();
        }
        this.repaint();
        // nodeTreeModel을 트리 순회하면서 JLabel 생성..
        Node root =nodeTreeModel.getRoot();
        root.treeToArray(root);

        nodeArray = root.getNodeArray();
        for(int i=0;i<nodeArray.size();i++){
            Node curLabelNode = nodeArray.get(i);
            curLabelNode.setX(50+i*30);
            curLabelNode.setY(100+i*40);
//            System.out.println();
            labelArray.add(new JLabel(curLabelNode.getText()));

            labelArray.get(i).setLocation(curLabelNode.getX(),curLabelNode.getY());
            labelArray.get(i).setSize(curLabelNode.getWidth(),curLabelNode.getHeight());
            labelArray.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            System.out.printf("%d %d\n",curLabelNode.getX(),curLabelNode.getY());
            add(labelArray.get(i));
            // Add EventListener to JLabel Node
            nodeSelectListener.UpdateNode(curLabelNode);
            labelArray.get(i).addMouseListener(nodeSelectListener);
            labelArray.get(i).addMouseMotionListener(nodeSelectListener);
        }
        setVisible(true);
    }
    public void updateNode(Node selectedNode){
//        remove(selectedNode);
        for(int i=0;i<labelArray.size();i++){
            if(selectedNode.getText().equals(labelArray.get(i).getText())){
                remove(labelArray.get(i));
                JLabel updateLabel = new JLabel(selectedNode.getText());
                updateLabel.setLocation(selectedNode.getX(),selectedNode.getY());
                updateLabel.setSize(selectedNode.getWidth(),selectedNode.getHeight());
                updateLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                labelArray.set(i,updateLabel);
                add(updateLabel);

            }
        }
        this.repaint();
        for(int i=0;i<labelArray.size();i++) {
            System.out.printf("%s %d %d\n", labelArray.get(i).getText(),labelArray.get(i).getX(), labelArray.get(i).getY());

        }
    }
}