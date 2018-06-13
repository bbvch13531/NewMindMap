package MindMap.EventListener;

import MindMap.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyBtnListener implements ActionListener  {
    AttributePane attributePane;
    MindMapPane mindMapPane;
    Node selectedNode;

    public ModifyBtnListener(AttributePane abp,MindMapPane mmp,Node node){
        this.attributePane = abp;
        this.mindMapPane = mmp;
        this.selectedNode = node;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String[] dataArr = attributePane.getData();
        selectedNode.setText(dataArr[0]);
        selectedNode.setX(Integer.parseInt(dataArr[1]));
        selectedNode.setY(Integer.parseInt(dataArr[2]));
        selectedNode.setHeight(Integer.parseInt(dataArr[3]));
        selectedNode.setWidth(Integer.parseInt(dataArr[4]));
        selectedNode.setColor(dataArr[5]);
        mindMapPane.updateNode(selectedNode);
    }

    public void UpdateBtnListener(AttributePane abp,MindMapPane mmp,Node node){
        this.attributePane = abp;
        this.mindMapPane = mmp;
        this.selectedNode = node;
    }
}
