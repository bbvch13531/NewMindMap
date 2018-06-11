package MindMap.EventListener;

        import MindMap.*;

        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.event.MouseAdapter;

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

        // 변경 버튼을 누르면 MindMapPane을 다시 그린다.
        // MindMapPane.drawAgain()
        String[] dataArr = attributePane.getData();
        selectedNode.setText(dataArr[0]);
        selectedNode.setX(Integer.parseInt(dataArr[1]));
        selectedNode.setY(Integer.parseInt(dataArr[2]));
        selectedNode.setHeight(Integer.parseInt(dataArr[3]));
        selectedNode.setWidth(Integer.parseInt(dataArr[4]));
        selectedNode.setColor(dataArr[5]);
//        mindMapPane.draw();
        // draw가 아니라 MindMapPane에서 ModifyBtn 처리하는 새로운 메소드를 호출해야함.
        // Tree에서 selectedNode를 찾아서 그 노드만 수정?
        mindMapPane.updateNode(selectedNode);
    }

    public void UpdateBtnListener(AttributePane abp,MindMapPane mmp,Node node){
        this.attributePane = abp;
        this.mindMapPane = mmp;
        this.selectedNode = node;
    }
}
