package com.stanbill.gui.panel;

/**
 * WorkingPanel是一个抽象类，其中声明了方法addListener()和updateData().
 *
 * 不同的面板类，都应该继承这个抽象类，进而必须提供addListener和updateData方法。
 */
import javax.swing.JPanel;

public abstract class WorkingPanel  extends JPanel{
    public abstract void updateData();
    public abstract void addListener();
}