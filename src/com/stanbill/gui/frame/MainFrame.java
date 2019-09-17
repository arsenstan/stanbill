package com.stanbill.gui.frame;

import javax.swing.JFrame;

import com.stanbill.gui.panel.MainPanel;
import com.stanbill.util.GUIUtil;
/*
主窗体-MainFrame
前面做的界面类panel都是面板类，不能独立运行。 整个项目的运行，还是需要一个顶级容器类 JFrame来容纳这些面板类。
 */
public class MainFrame extends JFrame{
    public static MainFrame instance = new MainFrame();

    private MainFrame(){
        this.setSize(500,450);
        this.setTitle("stanbill");
        this.setContentPane(MainPanel.instance);  //在主窗体中通过setContentPane把MainPanel设置为内容面板
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        instance.setVisible(true);
    }

}
