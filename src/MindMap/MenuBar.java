package MindMap;

import MindMap.EventListener.MenuToolListener;

import javax.swing.*;
public class MenuBar extends JMenuBar{

    MenuToolListener menuToolListener;
    MenuBar(MenuToolListener menuToolListener){
        this.menuToolListener = menuToolListener;

        JMenuItem[]  menuItem = new JMenuItem[7];
        String[] itemTitle = {"새로 만들기","열기","저장","다른 이름으로 저장","닫기","적용","변경"};
        JMenu title = new JMenu("메뉴");

        for(int i = 0; i < menuItem.length; i++){
            menuItem[i] = new JMenuItem(itemTitle[i]); // 메뉴아이템 생성
            menuItem[i].addActionListener(menuToolListener); // 메뉴아이템에 액션 리스너 등록.
            title.add(menuItem[i]);// 메뉴 아이템을 스크린 메뉴에 삽입
            if (i == menuItem.length - 1)
                break;
            title.addSeparator();
        }
        this.add(title);
        setVisible(true);
    }
}
