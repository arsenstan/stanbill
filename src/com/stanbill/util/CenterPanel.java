package com.stanbill.util;

import java.awt.Component;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.stanbill.gui.panel.WorkingPanel;

public class CenterPanel extends JPanel {   //居中面板

    private double rate;//拉伸比例
    private JComponent c; //显示的组件
    private boolean strech; //是否拉伸

    public CenterPanel(double rate,boolean strech) {
        this.setLayout(null);
        this.rate = rate;
        this.strech = strech;
    }

    public CenterPanel(double rate) {
        this(rate,true);
    }

    public void repaint() {
        if (null != c) {
            Dimension containerSize = this.getSize();
            Dimension componentSize= c.getPreferredSize();

            if(strech)
                c.setSize((int) (containerSize.width * rate), (int) (containerSize.height * rate));
            else
                c.setSize(componentSize);

            c.setLocation(containerSize.width / 2 - c.getSize().width / 2, containerSize.height / 2 - c.getSize().height / 2);
        }
        super.repaint();
    }

    public void show(JComponent p) {
        this.c = p;
        Component[] cs = getComponents();
        for (Component c : cs) {
            remove(c);
        }
        add(p);

        if (p instanceof WorkingPanel)              //workingPanel是CenterPanel类型的，那么只需要在show方法中判断，如果是WorkingPanel类型，那么就可以调用其updateData来做到把数据更新到界面上了。
            ((WorkingPanel) p).updateData();        //点击工具栏上的按钮，不仅可以切换到不同的面板，而且面板上的数据也马上从数据库中更新。

        this.updateUI();
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(200, 200);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(0.85,true);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        JButton b  =new JButton("abc");
        cp.show(b);

    }

}
