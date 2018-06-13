package MindMap;

import MindMap.EventListener.NodeSelectListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.math.*;

public class MindMapPane extends JPanel {
    AttributePane attributePane;
    public NodeTreeModel nodeTreeModel;
    public ArrayList <JLabel> labelArray;
    public ArrayList <Node> nodeArray;
    public Node root;

    MindMapPane(AttributePane abp, NodeTreeModel ntm){
        setLayout(null);
        this.attributePane = abp;
        this.nodeTreeModel = ntm;
        labelArray = new ArrayList<>();
        root = nodeTreeModel.getRoot();
    }

    public void updateNodeTreeModel(NodeTreeModel ntm){
        this.nodeTreeModel = ntm;
        root = nodeTreeModel.getRoot();
        nodeArray = root.getNodeArray(root);
    }

    // test Method
    public void draw(){
        NodeSelectListener nodeSelectListener = new NodeSelectListener(attributePane,this);
        this.addMouseListener(nodeSelectListener);
        this.addMouseMotionListener(nodeSelectListener);
        int size = nodeTreeModel.getSize();


        Node root = nodeTreeModel.getRoot();
        if(nodeTreeModel.getSize()==0 || nodeTreeModel.getSize()==1){
            System.out.println("Test log : Potential Error could occur");
        }
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
//        root.treeToArray(root);
        nodeArray = root.getNodeArray(root);
//        nodeArray.remove(0)


        for(int i=0;i<nodeArray.size();i++){
            if(i!=0) {
                drawLabel(i);
            }

            Node curLabelNode = nodeArray.get(i);
            if(i==0){
                curLabelNode.setX(500);
                curLabelNode.setY(350);
            }
//            curLabelNode.setX(50+i*30);
//            curLabelNode.setY(100+i*40);
//            System.out.println();
            labelArray.add(new JLabel(curLabelNode.getText(),JLabel.CENTER));
            JLabel curLabel = labelArray.get(i);

            /*
            curLabel.setBackground(Color.decode(curLabelNode.getColor()));
            */


            curLabel.setOpaque(true);
            curLabel.setLocation(curLabelNode.getX(),curLabelNode.getY());
            curLabel.setBackground(Color.decode(curLabelNode.getColor()));
            curLabel.setSize(curLabelNode.getWidth(),curLabelNode.getHeight());
            curLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            System.out.printf("%d %d\n",curLabelNode.getX(),curLabelNode.getY());
            add(curLabel);
            // Add EventListener to JLabel Node
            curLabel.addMouseListener(nodeSelectListener);
            curLabel.addMouseMotionListener(nodeSelectListener);
//            nodeSelectListener.UpdateNode(curLabelNode,curLabel);
            System.out.printf("getParent %d \n",getParent(i));

//            Node parent = nodeArray.get(getParent(i));

        }
        setVisible(true);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(Color.blue);

//        Node root = nodeTreeModel.getRoot();
//        ArrayList <Node> nodeArray = root.getNodeArray(root);
        if(nodeTreeModel.getSize()!=0) {
            for (int i = 1; i < nodeArray.size(); i++) {
                Node cur = nodeArray.get(i);
                Node parent = nodeArray.get(getParent(i));
                drawLine(g2, cur, parent);
            }
        }
    }

    public void updateNode(Node selectedNode){
//
        for(int i=0;i<labelArray.size();i++){
            if(selectedNode.getText().equals(labelArray.get(i).getText())){
                NodeSelectListener nodeSelectListener = new NodeSelectListener(attributePane,this);
                remove(labelArray.get(i));
                JLabel updateLabel = new JLabel(selectedNode.getText(),JLabel.CENTER);
                updateLabel.setLocation(selectedNode.getX(),selectedNode.getY());
                updateLabel.setSize(selectedNode.getWidth(),selectedNode.getHeight());
                updateLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                updateLabel.setOpaque(true);
                updateLabel.setBackground(Color.decode(selectedNode.getColor()));

                updateLabel.addMouseMotionListener(nodeSelectListener);
                updateLabel.addMouseListener(nodeSelectListener);

                labelArray.set(i,updateLabel);
                System.out.printf("%s %d %d %d\n",updateLabel.getText(),i,updateLabel.getX(),updateLabel.getY());
                add(updateLabel);

            }
        }
        this.repaint();
        for(int i=0;i<labelArray.size();i++) {
            System.out.printf("%s %d %d\n", labelArray.get(i).getText(),labelArray.get(i).getX(), labelArray.get(i).getY());

        }
    }
    int getParent(int index){
        //Root's parent index is -1
        Node root = nodeTreeModel.getRoot();
        ArrayList<Node> nodeArray = root.getNodeArray(root);
        int curDepth, parentDepth;

        curDepth = nodeArray.get(index).getDepth();

        for(int i=index-1; i>=0; i--){
            parentDepth = nodeArray.get(i).getDepth();
            if(parentDepth == curDepth -1){
                return i;
            }
        }
        return -1;
    }
    void drawLabel(int index){
        // Could call except root
//        Node root = nodeTreeModel.getRoot();
//        ArrayList<Node> nodeArray = root.getNodeArray(root);

        Node child = nodeArray.get(index);

        // 노드 어레이를 순회하며 child의 부모를 찾는다.
        Node parent = nodeArray.get(getParent(index));

        int childIndex = nodeTreeModel.getIndexOfChild(parent,child);

        double theta = (childIndex * 2 * Math.PI) / parent.getChild().size();

        System.out.printf("index = %d, childNode = %s, parentNode = %s, childIndex = %d, theta = %f, sin = %f\n",
                index,child.getText(),parent.getText(),childIndex,theta,Math.sin(theta));
        // child가 부모의 i번째 자식 구한다.
        // i만큼 cos,sin 각으로 부모의 getx, gety만큼 떨어지게 setLocation한다.
        // 떨어진 거리는 node의 depth가 깊을수록 더 감소한다. -> 깊을수록 부모와 자식의 거리가 가까워진다.
        int px,py,cx,cy;
        double ratio = 200 / (double)child.getDepth();
        px=parent.getX();
        py=parent.getY();
        cx = px + (int) (ratio * Math.cos((double)theta));
        cy = py - (int) (ratio * Math.sin((double)theta));
        System.out.printf("cx = %d cy = %d\n",cx, cy);
        child.setX(cx);
        child.setY(cy);

    }
    void drawLine(Graphics2D g2,Node child, Node parent){

        g2.drawLine(child.getX()+child.getWidth()/2,child.getY() + child.getHeight()/ 2, parent.getX() + parent.getWidth()/2,parent.getY() + parent.getHeight()/2);
    }
}