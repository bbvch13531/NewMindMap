package MindMap;

import javax.swing.*;
import java.util.ArrayList;

public class Node {
    private String text,color;
    private int x,y,width,height,depth;
    public ArrayList<Node> child,nodeArray;
    public Node(){
        child = new ArrayList<>();
        nodeArray = new ArrayList<>();
        this.depth = 0;
    }

    Node(String text){
        this.text = text;
        child = new ArrayList<>();
        nodeArray = new ArrayList<>();
    }
    public String getText() { return text; }

    public String getColor() { return color; }

    public int getX() { return x; }

    public int getY() { return y; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public ArrayList<Node> getChild(){ return this.child; }

    public int getDepth(){ return this.depth;}

    public void setText(String text) { this.text = text; }

    public void setColor(String color) { this.color = color; }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public void setHeight(int height) { this.height = height; }

    public void setWidth(int width) { this.width = width; }

    public void setDepth(int depth){ this.depth = depth;}

    public void addChild(Node child) {
        child.setDepth(this.getDepth()+1);
        this.child.add(child);
    }

    public ArrayList<Node> getNodeArray(Node node){
//        return this.nodeArray;
//        ArrayList<Node> returnArray = new ArrayList<>();
        if(nodeArray.size()!=0) return nodeArray;
        else {
            treeToArray(node, nodeArray);
            return nodeArray;
        }
    }

    @Override
    public String toString(){
        return "text = "+getText()+", color = "+getColor()+", x = "+getX()+", y = "+getY();
    }

    public void treeToArray(Node node, ArrayList<Node> returnArray){
        ArrayList<Node> child = node.getChild();
        if(node == null) return;

        returnArray.add(node);
//        System.out
        for(int i=0;i<child.size();i++){
            treeToArray(child.get(i),returnArray);
        }
    }
}
