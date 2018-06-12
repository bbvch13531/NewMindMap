package MindMap;

import MindMap.EventListener.ModifyBtnListener;

import javax.swing.*;
import java.awt.*;

public class AttributePane extends JPanel {
    MindMapPane mindMapPane;
    Node selectedNode;
    JLabel x,y,height,width,color,name;
    JTextField xVal,yVal,heightVal,widthVal,colorVal,nameVal;
    JButton modifyBtn;
    ModifyBtnListener modifyBtnListener;
    AttributePane(){

        modifyBtn = new JButton("변경");
        modifyBtnListener = new ModifyBtnListener(this,mindMapPane,selectedNode);
        setLayout(new GridLayout(6,1,0,15));
        AddName();
        AddX();
        AddY();
        AddHeight();
        AddWidth();
        AddColor();
        add(modifyBtn);
        setVisible(true);
    }
    public void AddMindMapPane(MindMapPane mmp){
        this.mindMapPane = mmp;
        modifyBtnListener.UpdateBtnListener(this,mindMapPane,selectedNode);
        modifyBtn.addActionListener(modifyBtnListener);
    }
    public void setSelectedNode(Node ssn,JLabel label){
        this.selectedNode = ssn;
        modifyBtnListener.UpdateBtnListener(this,mindMapPane,selectedNode);
//        JLabel target = (JLabel) obj;
//        System.out.printf("%s",node.getText());
        nameVal.setText(String.valueOf(ssn.getText()));

        xVal.setText(String.valueOf(ssn.getX()));
        yVal.setText(String.valueOf(ssn.getY()));
        heightVal.setText(String.valueOf(ssn.getHeight()));
        widthVal.setText(String.valueOf(ssn.getWidth()));
        colorVal.setText(String.valueOf(ssn.getColor()));

        ssn.setX(label.getX());
        ssn.setY(label.getY());
        ssn.setHeight(label.getHeight());
        ssn.setWidth(label.getWidth());
    }
    public void clearTextFields(){
        nameVal.setText(null);
        xVal.setText(null);
        yVal.setText(null);
        heightVal.setText(null);
        widthVal.setText(null);
        colorVal.setText(null);
    }
    void AddName(){
        name = new JLabel("Text");
        nameVal = new JTextField();
        nameVal.setBackground(Color.LIGHT_GRAY);
        nameVal.setForeground(Color.BLUE);
        nameVal.setEditable(false);
        add(name);
        add(nameVal);
    }
    void AddX(){
        x = new JLabel("X");
        xVal = new JTextField(7);

        add(x);
        add(xVal);
    }
    void AddY(){
        y = new JLabel("Y");
        yVal = new JTextField(7);

        add(y);
        add(yVal);
    }
    void AddHeight(){
        height = new JLabel("Height");
        heightVal = new JTextField(7);

        add(height);
        add(heightVal);
    }
    void AddWidth(){
        width = new JLabel("Width");
        widthVal = new JTextField(7);

        add(width);
        add(widthVal);
    }
    void AddColor(){
        color = new JLabel("Color");
        colorVal = new JTextField(8);

        add(color);
        add(colorVal);
    }
    public String[] getData(){
        String[] dataArr = new String[6];
        dataArr[0] = nameVal.getText();
        dataArr[1] = xVal.getText();
        dataArr[2] = yVal.getText();
        dataArr[3] = heightVal.getText();
        dataArr[4] = widthVal.getText();
        dataArr[5] = colorVal.getText();

        return dataArr;
    }
}
