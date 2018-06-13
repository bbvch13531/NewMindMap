package MindMap;

import MindMap.EventListener.MenuToolListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ToolBar extends JToolBar {
    MenuToolListener menuToolListener;
    ToolBar(MenuToolListener menuToolListener){
        this.menuToolListener = menuToolListener;

        String[] itemTitle = { "새로 만들기", "열기", "저장", "다른 이름으로 저장 ", "닫기", "적용", "변경" };
        JButton[] barItem = new JButton[7];

        this.setBorder(new EmptyBorder(new Insets(0,0,0,0)));
        for(int i=0; i <barItem.length; i++) {
            barItem[i] = new JButton(itemTitle[i]);
            barItem[i].addActionListener(menuToolListener);
            this.add(barItem[i]);
        }
    }
}
