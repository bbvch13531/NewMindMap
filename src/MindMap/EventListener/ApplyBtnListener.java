package MindMap.EventListener;

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
            }
            data.setColor("0xffffff");
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
        Gson gson = new Gson();
        System.out.println(nodeTreeModel);
        String json = gson.toJson(nodeTreeModel);
        System.out.println(mindMapPane);

        mindMapPane.updateNodeTreeModel(nodeTreeModel);
        mindMapPane.draw();
//        System.out.println(json);


    }

}
