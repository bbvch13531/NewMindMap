package MindMap;

import MindMap.EventListener.MenuToolListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MenuBar extends JMenuBar{

    MenuToolListener menuToolListener;
    MenuBar(MenuToolListener menuToolListener){
        this.menuToolListener = menuToolListener;

        Border border = BorderFactory.createLineBorder(Color.black, 3);
        this.setBorder(border);

        JMenuItem[]  menuItem = new JMenuItem[7];
        String[] itemTitle = {"새로 만들기","열기","저장","다른 이름으로 저장","닫기","적용","변경"};
        JMenu title = new JMenu("메뉴");

        for(int i = 0; i < menuItem.length; i++){
            menuItem[i] = new JMenuItem(itemTitle[i]);
            menuItem[i].addActionListener(menuToolListener);
            title.add(menuItem[i]);
            if (i == menuItem.length - 1)
                break;
            title.addSeparator();
        }
        this.add(title);
        setVisible(true);
    }
}
