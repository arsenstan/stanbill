package com.stanbill.gui.panel;
/*
MainPanel 是主窗体的ContentPanel，采用的是BorderLayerout的布局方式。
北边是一个工具条
中间是一个空白的Panel，用于将来显示不同的功能面板
 */
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.stanbill.gui.listener.ToolBarListener;
import com.stanbill.util.CenterPanel;
import com.stanbill.util.GUIUtil;

public class MainPanel extends JPanel {
    static {                             //在静态初始化块中调用LookAndFeel，设置水晶皮肤。 因为这段话必须放在最前面才能生效，所以需要在这里调用
        GUIUtil.useLNF();
    }

    public static MainPanel instance = new MainPanel(); //接着采用单例模式，为的是后续的监听器访问这个容器里的组件的便利性
    //然后是把各种按钮声明为public 的属性，同样也是为了后续的监听器访问这个容器里的组件的便利性
    public JToolBar tb = new JToolBar();
    public JButton bSpend = new JButton();
    public JButton bRecord = new JButton();
    public JButton bCategory = new JButton();
    public JButton bReport = new JButton();
    public JButton bConfig = new JButton();
    public JButton bBackup = new JButton();
    public JButton bRecover = new JButton();
    //工作面板，用于将来显示不同的功能面板。 目前暂时是空白的
    public CenterPanel workingPanel;

    private MainPanel() {
        //使用GUI.setImageIcon设置这些按钮的图标、文字和提示
        GUIUtil.setImageIcon(bSpend, "home.png", "消费一览");
        GUIUtil.setImageIcon(bRecord, "record.png", "记一笔");
        GUIUtil.setImageIcon(bCategory, "category2.png", "消费分类");
        GUIUtil.setImageIcon(bReport, "report.png", "月消费报表");
        GUIUtil.setImageIcon(bConfig, "config.png", "设置");
        GUIUtil.setImageIcon(bBackup, "backup.png", "备份");
        GUIUtil.setImageIcon(bRecover, "restore.png", "恢复");

        tb.add(bSpend);
        tb.add(bRecord);
        tb.add(bCategory);
        tb.add(bReport);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);
        tb.setFloatable(false);

        workingPanel = new CenterPanel(0.8);

        setLayout(new BorderLayout());
        add(tb, BorderLayout.NORTH);
        add(workingPanel, BorderLayout.CENTER);

        addListener();
    }

    /**
     * 实例化一个ToolBarListener 监听器，工具栏上的按钮，都加上这么一个监听器对象即可。
     */
    private void addListener() {
        ToolBarListener listener = new ToolBarListener();

        bSpend.addActionListener(listener);
        bRecord.addActionListener(listener);
        bCategory.addActionListener(listener);
        bReport.addActionListener(listener);
        bConfig.addActionListener(listener);
        bBackup.addActionListener(listener);
        bRecover.addActionListener(listener);
    }

    //最后使用GUIUtil.showPanel方法，显示这个面板实例，测试起来非常方便  参数"1" 表示满屏显示
    public static void main(String[] args) {
        GUIUtil.showPanel(MainPanel.instance, 1);
    }
}
