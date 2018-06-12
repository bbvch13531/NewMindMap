package MindMap;

import javax.swing.*;
import java.util.ArrayList;

public class Node {
    private String text,color;
    private int x,y,width,height;
    private ArrayList<Node> child,nodeArray;
    public Node(){
        child = new ArrayList<>();
        nodeArray = new ArrayList<>();
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

    public void setText(String text) { this.text = text; }

    public void setColor(String color) { this.color = color; }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public void setHeight(int height) { this.height = height; }

    public void setWidth(int width) { this.width = width; }

    public void addChild(Node child) { this.child.add(child); }

    public ArrayList<Node> getNodeArray(){ return this.nodeArray;}

    @Override
    public String toString(){
        return "text = "+getText()+", color = "+getColor()+", x = "+getX()+", y = "+getY();
    }

    public void treeToArray(Node node){
        ArrayList<Node> child = node.getChild();
        if(node == null) return;

        nodeArray.add(node);
//        System.out
        for(int i=0;i<child.size();i++){
            treeToArray(child.get(i));
        }
    }
}
