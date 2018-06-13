package MindMap;

import MindMap.EventListener.ModifyBtnListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AttributePane extends JPanel {
    MindMapPane mindMapPane;
    Node selectedNode;
    JPanel colorPatch;
    JColorChooser tcc;
    JLabel x,y,height,width,color,name;
    JTextField xVal,yVal,heightVal,widthVal,colorVal,nameVal;
    JButton modifyBtn;
    ModifyBtnListener modifyBtnListener;

    AttributePane(){
        modifyBtnListener = new ModifyBtnListener(this,mindMapPane,selectedNode);
        setLayout(new GridLayout(7,1,0,15));
        AddName();
        AddX();
        AddY();
        AddHeight();
        AddWidth();
        AddColor();
        AddButton();
        setBorder(new EmptyBorder(new Insets(0,0,0,0)));
        setVisible(true);
    }
    public void AddMindMapPane(MindMapPane mmp){
        this.mindMapPane = mmp;
        modifyBtnListener.UpdateBtnListener(this,mindMapPane,selectedNode);
        modifyBtn.addActionListener(modifyBtnListener);
    }

    public ModifyBtnListener getModifyBtnListener(){ return this.modifyBtnListener;}

    public void setSelectedNode(Node ssn,JLabel label){
        this.selectedNode = ssn;
        modifyBtnListener.UpdateBtnListener(this,mindMapPane,selectedNode);
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
        selectedNode.setColor(colorVal.getText());
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
        name.setFont(new Font("a", Font.PLAIN, 30));
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
        x.setFont(new Font("a", Font.PLAIN, 30));

        add(x);
        add(xVal);
    }
    void AddY(){
        y = new JLabel("Y");
        yVal = new JTextField(7);
        y.setFont(new Font("a", Font.PLAIN, 30));

        add(y);
        add(yVal);
    }
    void AddHeight(){
        height = new JLabel("Height");
        heightVal = new JTextField(7);
        height.setFont(new Font("a", Font.PLAIN, 30));

        add(height);
        add(heightVal);
    }
    void AddWidth(){
        width = new JLabel("Width");
        widthVal = new JTextField(7);
        width.setFont(new Font("a", Font.PLAIN, 30));

        add(width);
        add(widthVal);
    }
    void AddColor(){
        color = new JLabel("Color");
        colorVal = new JTextField(8);
        color.setFont(new Font("a", Font.PLAIN, 30));
        color.setToolTipText("Click to Pick Color");
        color.addMouseListener(new MyMouseListener());

        add(color);
        add(colorVal);
    }
    void AddButton(){
        JLabel dummyLabel = new JLabel("");
        modifyBtn = new JButton("변경");
        add(dummyLabel);
        add(modifyBtn,BorderLayout.SOUTH);
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
    class MyMouseListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            int red, green, blue;
            String setColor, convertColor,stringRed,StringGreen,StringBlue;
            colorPatch = new JPanel();
            colorPatch.setPreferredSize( new Dimension(50,20) );
            add(colorPatch);
            Color nodeColor = JColorChooser.showDialog(getComponentPopupMenu(), "Set Color", Color.black);
            red = nodeColor.getRed();
            green = nodeColor.getGreen();
            blue = nodeColor.getBlue();
            stringRed = getRGBHex(red);
            StringGreen = getRGBHex(green);
            StringBlue = getRGBHex(blue);
            setColor = stringRed+StringGreen+StringBlue;
            System.out.println(setColor);
            convertColor = getColorHex(setColor);
            colorVal.setText(convertColor);
            System.out.println(convertColor);
        }
        String getRGBHex(int rgb){
            String color;
            color = Integer.toHexString(rgb);
            if(color.length() == 1){
                color = "0"+color;
            }
            return color;
        }
        String getColorHex(String color){

            if(color.length() == 4){
                color = "0x00"+color;
            }
            else if(color.length() == 5){
                color = "0x0" +color;
            }
            else if(color.length() == 6){
                color = "0x"+color;
            }
            return color;
        }
    }
}
