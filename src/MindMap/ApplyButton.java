package MindMap;

import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class ApplyButton extends JPanel {
    JButton btn;
    ApplyButton() {
        setLayout(new BorderLayout());
        btn = new JButton("적용");
        btn.setBackground(Color.RED);
        btn.setFont(new Font("a", Font.BOLD, 15));
        add(btn, BorderLayout.SOUTH);
        setVisible(true);
    }
}