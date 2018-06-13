package MindMap.EventListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
import com.google.gson.Gson;
import MindMap.*;

import javax.swing.*;

public class ApplyBtnListener implements ActionListener{
    TextEditorPane textEditorPane;
    MindMapPane mindMapPane;
    NodeTreeModel nodeTreeModel;

    public ApplyBtnListener(TextEditorPane tep,MindMapPane mmp,NodeTreeModel ntm){
        this.textEditorPane = tep;
        this.mindMapPane = mmp;
        this.nodeTreeModel = ntm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Node parent, cur;
        int depth=0,head=0,tail=0;
        String rawText= null;
        JTextArea textArea = textEditorPane.getTextArea();
//        System.out.println(textArea.getText());
        //        textArea의 string을 가지고와서 nodetreemodel을 만든다.
//        System.out.println(nodeTreeModel.getRoot());
        for(int i=0; i<textArea.getLineCount();i++){
            depth=0;
            Node data = new Node();
            try{
                head = textArea.getLineStartOffset(i);
                tail = textArea.getLineEndOffset(i);
                rawText = textArea.getText(head,tail-head);
                StringBuffer buf = new StringBuffer(rawText);
                buf = buf.deleteCharAt(buf.length()-1);
                rawText = new String(buf);

            } catch (Exception exception){
                System.err.print("lineOffset Exception");
                break;
            }
            data.setColor("0xff99cc");
            data.setX(i*50);
            data.setY(i*100);
            data.setHeight(30);
            data.setWidth(50);

            if(!rawText.contains("\t")){
                nodeTreeModel.setRoot(data);
            }
            parent = nodeTreeModel.getRoot();
            while(rawText.contains("\t")){
                StringBuffer buf = new StringBuffer(rawText);
                buf.deleteCharAt(0);
                rawText = new String(buf);

                depth++;
            }
            data.setText(rawText);

            if(depth>0){
                for(int j=0;j<depth-1;j++){
                    ArrayList<Node> nodeList = parent.getChild();
                    cur = nodeList.get(nodeList.size() - 1);
                    parent = cur;
                }
                parent.addChild(data);
                nodeTreeModel.increaseSize();
            }
        }

        int MaxDepth = 0 , compareDepth = 0;
        Node root = nodeTreeModel.getRoot();
        ArrayList<Node> nodeArray = root.getNodeArray(root);

        for(int i=0;i<nodeArray.size();i++){
            compareDepth = nodeArray.get(i).getDepth();
            if(MaxDepth < compareDepth){
                MaxDepth = compareDepth;
            }
        }

        String[] s = new String[MaxDepth+1];
        for(int i = 0; i <= MaxDepth; i++){
            String decodeColor = "0x";
            double randRed = Math.random()*225+17;
            double randGreen = Math.random()*225+17;
            double randBlue = Math.random()*225+17;
            s[i] = decodeColor+Integer.toHexString((int)randRed)+Integer.toHexString((int)randGreen)+Integer.toHexString((int)randBlue);
            System.out.println(s[i].toString());
        }
        for(int i=0;i<nodeArray.size(); i++) {
            for (int j = 0; j <= MaxDepth; j++) {
                if (nodeArray.get(i).getDepth() == j)
                    nodeArray.get(i).setColor(s[j].toString());
            }
        }

        mindMapPane.updateNodeTreeModel(nodeTreeModel);
        mindMapPane.draw();
//        System.out.println(json);

    }
}
