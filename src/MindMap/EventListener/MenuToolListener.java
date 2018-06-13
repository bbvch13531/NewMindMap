package MindMap.EventListener;

import MindMap.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import javax.swing.*;

public class MenuToolListener implements ActionListener {
    static boolean isSaved = false;
    static String content = "";
    FileDialog mSave;
    FileDialog mOpen;

    MindMap mindMap;
    NodeTreeModel nodeTreeModel;
    MindMapPane mindMapPane;
    TextEditorPane textEditorPane;
    AttributePane attributePane;
    JTextArea jTextArea;

    public MenuToolListener(MindMap mindMap, MindMapPane mindMapPane, TextEditorPane textEditorPane, AttributePane attributePane, NodeTreeModel nodeTreeModel){
        this.mindMap = mindMap;
        this.nodeTreeModel = nodeTreeModel;
        this.mindMapPane = mindMapPane;
        this.textEditorPane = textEditorPane;
        this.attributePane = attributePane;

        this.jTextArea = textEditorPane.getTextArea();

        mSave = new FileDialog(mindMap, "저장", FileDialog.SAVE);
        mOpen = new FileDialog(mindMap, "열기", FileDialog.LOAD);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String path="";
        System.out.println(cmd);

        Gson gson = new Gson();
        switch (cmd){
            case "새로 만들기":
                mindMap.hide();
                new MindMap();
                break;
            case "열기":
                try {
                    mOpen.setVisible(true);
                    path = mOpen.getDirectory() + mOpen.getFile();
                    System.out.println(path);
                } catch(Exception exception){
                    exception.printStackTrace();
                    System.out.println("Load Error");
                }

                try{
                    JsonReader jsonReader = new JsonReader(new FileReader(path));

                    nodeTreeModel = gson.fromJson(jsonReader,NodeTreeModel.class);
                    mindMapPane.updateNodeTreeModel(nodeTreeModel);
                    mindMapPane.draw();
                    load(nodeTreeModel,jTextArea);
                } catch(Exception exception){
                    exception.printStackTrace();
                    System.out.println("Load Error");
                }
                break;
            case "저장":
                if(isSaved == false){
                    mSave.setVisible(true);
                    path = mSave.getDirectory() + mSave.getFile();

                    try{
                        FileWriter writer = new FileWriter(path + ".json");
                        nodeTreeModel.getRoot().nodeArray = new ArrayList<Node>();
                        writer.write(gson.toJson(nodeTreeModel));
                        writer.close();
                    } catch (Exception exception){
                        exception.printStackTrace();
                    }
                    String Filename = mSave.getFile();
                    mindMap.setTitle(Filename);

                    isSaved = true;
                    nodeTreeModel.getRoot().getNodeArray(nodeTreeModel.getRoot());
                }
                else{
                    path = mSave.getDirectory() + mSave.getFile();
                    try{
                        FileWriter writer = new FileWriter(path + ".json");

                        writer.write(gson.toJson(nodeTreeModel));
                        writer.close();
                    } catch(Exception exception){
                        exception.printStackTrace();
                    }
                }
                break;
            case "다른 이름으로 저장":
                mSave.setVisible(true);
                path = mSave.getDirectory() + mSave.getFile();
                try{
                    FileWriter writer = new FileWriter(path + ".json");
                    writer.write(gson.toJson(nodeTreeModel));
                    writer.close();
                } catch (Exception exception){
                    exception.printStackTrace();
                }
                String Filename = mSave.getFile();
                mindMap.setTitle(Filename);
                isSaved = true;
                break;
            case "적용":
                ApplyBtnListener applyBtnListener = textEditorPane.getApplyBtnListener();
                applyBtnListener.actionPerformed(e);
                break;
            case "변경":
                ModifyBtnListener modifyBtnListener = attributePane.getModifyBtnListener();
                modifyBtnListener.actionPerformed(e);
                break;
            case "닫기":
                System.exit(-1);
                break;
            default:
                break;
        }
    }
    public void load(NodeTreeModel nodeTreeModel, JTextArea jTextArea){
        Node root = nodeTreeModel.getRoot();
        ArrayList<Node> nodeArrayList = root.getNodeArray(root);
        String line="";
        for(int i=0; i<nodeArrayList.size(); i++){
            int depth = nodeArrayList.get(i).getDepth();

            for(int j=0; j<depth; j++){
                line += "\t";
            }
            line += nodeArrayList.get(i).getText();
            line += '\n';

        }
        jTextArea.setText(line);
        this.nodeTreeModel = nodeTreeModel;
    }
}
