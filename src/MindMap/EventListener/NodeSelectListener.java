package MindMap.EventListener;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

import MindMap.*;

public class NodeSelectListener extends MouseAdapter {
    AttributePane attributePane;
    MindMapPane mindMapPane;
    ArrayList <JLabel> labelArray;
    ArrayList <Node> nodeArray;
    Node selectedNode,root;
    JLabel selectedLabel;

    int x,y;
    int xPos,yPos,index;

    boolean dragging = false;
    boolean selected = false;
    boolean entered = false;

    public NodeSelectListener(AttributePane abp,MindMapPane mmp){
        this.attributePane = abp;
        this.mindMapPane = mmp;
        labelArray = mindMapPane.labelArray;
        this.selectedNode = null;
        this.root = mmp.nodeTreeModel.getRoot();
        nodeArray = root.getNodeArray(root);
    }

    public void UpdateNode(Node node,JLabel label){
        node.setX(label.getX());
        node.setY(label.getY());
        node.setHeight(label.getHeight());
        node.setWidth(label.getWidth());

        attributePane.setSelectedNode(node,label);
    }

    public void mousePressed(MouseEvent e){
        String prevColor ="", temporaryColor="";
        String stringRed, stringGreen, stringBlue;
        int red , green, blue;

        x = e.getX();
        y = e.getY();
        index = 0;
        index = getIndex(e);
        if(index!=-1) {
            if (labelArray.get(index) == e.getSource())
                selected = true;
            else {
                selected = false;
            }
        }
        if(entered) {
            if (mindMapPane.getCursor() != Cursor.getDefaultCursor()) { // draw.getCursor()
                // If cursor is set for Test, allow dragging.
                dragging = true;
            }
            selectedNode = nodeArray.get(index);
            prevColor = selectedNode.getColor();
            red = 255 - selectedLabel.getBackground().getRed();
            green = 255 - selectedLabel.getBackground().getGreen();
            blue = 255 - selectedLabel.getBackground().getBlue();
            stringRed = getRGBHex(red);
            stringGreen = getRGBHex(green);
            stringBlue = getRGBHex(blue);
            System.out.println(stringBlue);
            temporaryColor = stringRed + stringGreen + stringBlue;
            temporaryColor = getColorHex(temporaryColor);
            selectedLabel.setBackground(Color.decode(temporaryColor));
        }
    }

    public void mouseReleased(MouseEvent e) {
        String prevColor ="";
        dragging = false;
        if(entered) {
            prevColor = selectedNode.getColor();
            selectedLabel.setBackground(Color.decode(prevColor));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        index = getIndex(e);
        if(index!=-1) {
            if (entered) {
                selectedNode = nodeArray.get(index);
                attributePane.setSelectedNode(selectedNode,labelArray.get(index));
                selectedNode = nodeArray.get(index);
            }
        }
    }

    public void mouseEntered(MouseEvent e){
        index = getIndex(e);
        if(index!=-1) {
            if (labelArray.get(index) == e.getSource())
                entered = true;
            else {
                entered = false;
            }
        }
        else {
            entered = false;
        }
    }
    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {

        index = getIndex(e);

        int distanceX = e.getX() - x;
        int distanceY = e.getY() - y;

        selectedLabel = labelArray.get(index);
        selectedNode = nodeArray.get(index);

        xPos = selectedLabel.getX() + distanceX;
        yPos = selectedLabel.getY() + distanceY;

        if(!entered) attributePane.clearTextFields();
        else attributePane.setSelectedNode(selectedNode,selectedLabel);


        int selectedLabelX,selectedLabelY,selectedLabelHeight,selectedLabelWidth;
        selectedLabelX = selectedLabel.getX();
        selectedLabelY = selectedLabel.getY();
        selectedLabelHeight = selectedLabel.getHeight();
        selectedLabelWidth = selectedLabel.getWidth();

        if (selected) {

            int type = mindMapPane.getCursor().getType();

            switch (type) {
                case Cursor.DEFAULT_CURSOR:
                    selectedLabel.setLocation(selectedLabelX + distanceX,
                            selectedLabelY + distanceY);
                    break;
                case Cursor.N_RESIZE_CURSOR:
                    if (e.getY() < 2) {
                        selectedLabel.setBounds(selectedLabelX, selectedLabelY - 1,
                                selectedLabelWidth, selectedLabelHeight + 1);
                    } else {
                        selectedLabel.setBounds(selectedLabelX, selectedLabelY + 1,
                                selectedLabelWidth, selectedLabelHeight - 1);
                    }
//                    System.out.println("Cursor N");
                    break;
                case Cursor.NW_RESIZE_CURSOR:
                    if (e.getY() < 2 && e.getX() < 2) {
                        selectedLabel.setBounds(selectedLabelX - 1, selectedLabelY - 1,
                                selectedLabelWidth + 1, selectedLabelHeight + 1);

                    } else {
                        selectedLabel.setBounds(selectedLabelX + 1, selectedLabelY + 1,
                                selectedLabelWidth - 1, selectedLabelHeight - 1);
                    }
//                    System.out.println("Cursor NW");
                    break;
                case Cursor.W_RESIZE_CURSOR:
                    if (e.getX() < 3) {
                        selectedLabel.setBounds(selectedLabelX - 1, selectedLabelY,
                                selectedLabelWidth + 1, selectedLabelHeight);
                    } else {
                        selectedLabel.setBounds(selectedLabelX + 1, selectedLabelY,
                                selectedLabelWidth - 1, selectedLabelHeight);
                    }
//                    System.out.println("Cursor W");
                    break;
                case Cursor.SW_RESIZE_CURSOR:
                    if (e.getX() < 3 && e.getY() > selectedLabelHeight - 3) {
                        selectedLabel.setBounds(selectedLabelX - 1, selectedLabelY,
                                selectedLabelWidth + 1, selectedLabelHeight + 1);
                    } else {
                        selectedLabel.setBounds(selectedLabelX + 1, selectedLabelY,
                                selectedLabelWidth - 1, selectedLabelHeight - 1);
                    }
//                    System.out.println("Cursor SW");
                    break;
                case Cursor.S_RESIZE_CURSOR:
                    if (e.getY() > selectedLabelHeight - 3) {
                        selectedLabel.setBounds(selectedLabelX, selectedLabelY,
                                selectedLabelWidth, selectedLabelHeight + 1);
                    } else {
                        selectedLabel.setBounds(selectedLabelX, selectedLabelY,
                                selectedLabelWidth, selectedLabelHeight - 1);
                    }
//                    System.out.println("Cursor S");
                    break;
                case Cursor.SE_RESIZE_CURSOR:
                    if (e.getX() > selectedLabelWidth - 3 && e.getY() > selectedLabelHeight - 3) {
                        selectedLabel.setBounds(selectedLabelX, selectedLabelY,
                                selectedLabelWidth + 1, selectedLabelHeight + 1);
                    } else {
                        selectedLabel.setBounds(selectedLabelX, selectedLabelY,
                                selectedLabelWidth - 1, selectedLabelHeight - 1);
                    }
//                    System.out.println("Cursor SE");
                    break;
                case Cursor.E_RESIZE_CURSOR:
                    if (e.getX() > selectedLabelWidth - 3) {
                        selectedLabel.setBounds(selectedLabelX, selectedLabelY,
                                selectedLabelWidth + 1, selectedLabelHeight);
                    } else {
                        selectedLabel.setBounds(selectedLabelX, selectedLabelY,
                                selectedLabelWidth - 1, selectedLabelHeight);
                    }
//                    System.out.println("Cursor E");
                    break;
                case Cursor.NE_RESIZE_CURSOR:
                    if (e.getX() > selectedLabelWidth - 3 && e.getY() < 3) {
                        selectedLabel.setBounds(selectedLabelX, selectedLabelY - 1,
                                selectedLabelWidth + 1, selectedLabelHeight + 1);
                    } else {
                        selectedLabel.setBounds(selectedLabelX, selectedLabelY + 1,
                                selectedLabelWidth - 1, selectedLabelHeight - 1);
                    }
//                    System.out.println("Cursor NE");
                    break;
                default:
//                    System.out.println("unexpected type: " + type);
            }
            mindMapPane.repaint();
        }
    }

    public void mouseMoved(MouseEvent e) {
        index=getIndex(e);
        if(index!=-1) {
            selectedLabel = labelArray.get(index);
            int selectedLabelHeight = selectedLabel.getHeight();
            int selectedLabelWidth = selectedLabel.getWidth();
            if (entered) {

                if (e.getX() < 4 && e.getY() < 4) {
                    mindMapPane.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                } else if (e.getX() != 0 && e.getY() < 3) {
                    mindMapPane.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                } else if (e.getX() > selectedLabelWidth - 4 && e.getY() < 4) {
                    mindMapPane.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                } else if (e.getX() > selectedLabelWidth - 3 && e.getY() > 3
                        && e.getY() < selectedLabelHeight - 3) {

                    mindMapPane.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                } else if (e.getY() > selectedLabelHeight - 4 && e.getX() > selectedLabelWidth - 4) {
                    mindMapPane.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                } else if (e.getX() != 0 && e.getY() > selectedLabelHeight - 3) {
                    mindMapPane.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                } else if (e.getX() < 4 && e.getY() > selectedLabelHeight - 4) {
                    mindMapPane.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                } else if (e.getX() < 3 && e.getY() != 0) {
                    mindMapPane.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
                } else {
                    mindMapPane.setCursor(Cursor.getDefaultCursor());
                }
            }
            if (!entered)
                mindMapPane.setCursor(Cursor.getDefaultCursor());
        }
        if (!entered) {
            mindMapPane.setCursor(Cursor.getDefaultCursor());
        }
    }

    public int getIndex(MouseEvent e){

        for (int i = 0; i < labelArray.size(); i++) {
            if (labelArray.get(i) == e.getSource()) {
                return i;
            }
        }

        return 1;
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