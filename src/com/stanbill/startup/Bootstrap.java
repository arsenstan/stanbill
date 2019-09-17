package com.stanbill.startup;
/**
 * 启动类
 * 所有的进程都有个启动入口，一般说来，稍微复杂点的程序，就会有一个类，专门负责启动的事情，通常这个类会叫做startup.Bootstrap
 */

import javax.swing.SwingUtilities;

import com.stanbill.gui.frame.MainFrame;
import com.stanbill.gui.panel.MainPanel;
import com.stanbill.gui.panel.SpendPanel;
import com.stanbill.util.GUIUtil;

public class Bootstrap {
    public static void main(String[] args) throws Exception{
        GUIUtil.useLNF();

        /**
         * 使用SwingUtilities.invokeAndWait的方式启动图形界面
         */
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                MainFrame.instance.setVisible(true);          //显示主窗体，并让主面板下方的workingPanel显示消费一览Panel
                MainPanel.instance.workingPanel.show(SpendPanel.instance);
            }
        });
    }
}
