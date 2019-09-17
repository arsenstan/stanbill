package com.stanbill.gui.panel;

import javax.swing.JButton;

import com.stanbill.gui.listener.BackupListener;
import com.stanbill.util.ColorUtil;
import com.stanbill.util.GUIUtil;
/*
V1:备份面板BackupPanel 就很简单了，直接放了个按钮在上面。

V2:BackupPanel 备份面板类继承WorkingPanel 类，并实现addListener
 */
public class BackupPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static BackupPanel instance = new BackupPanel();
    JButton bBackup = new JButton("备份");

    public BackupPanel() {
        GUIUtil.setColor(ColorUtil.blueColor, bBackup);
        this.add(bBackup);
        addListener();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(BackupPanel.instance);
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        BackupListener listener = new BackupListener();
        bBackup.addActionListener(listener);
    }

}
/*
public class BackupPanel extends JPanel {
    static{
        GUIUtil.useLNF();
    }

    public static BackupPanel instance = new BackupPanel(); //单例化
    JButton bBackup =new JButton("备份");     //组件属性

    public BackupPanel() {
        GUIUtil.setColor(ColorUtil.blueColor, bBackup);
        this.add(bBackup);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(BackupPanel.instance);
    }

}
*/
