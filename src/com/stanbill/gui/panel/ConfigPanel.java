package com.stanbill.gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.stanbill.gui.listener.ConfigListener;
import com.stanbill.util.ColorUtil;
import com.stanbill.util.GUIUtil;
import com.stanbill.service.ConfigService;

/*
本面板使用BorderLayout，分北面和居中。
北面是一个JPanel，里面放4个组件，使用4行1列的GridLayout的布局
居中是一个JPanel,就放了一个按钮
 */
public class ConfigPanel extends WorkingPanel {
    static{
        GUIUtil.useLNF();
    }
    public static ConfigPanel instance = new ConfigPanel();

    JLabel lBudget = new JLabel("本月预算(￥)");
    public JTextField tfBudget = new JTextField("0");

    JLabel lMysql = new JLabel("Mysql安装目录");
    public JTextField tfMysqlPath = new JTextField("");

    JButton bSubmit = new JButton("更新");

    public ConfigPanel() {
        GUIUtil.setColor(ColorUtil.grayColor, lBudget,lMysql);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);

        JPanel pInput =new JPanel();
        JPanel pSubmit = new JPanel();
        int gap =40;
        pInput.setLayout(new GridLayout(4,1,gap,gap));

        pInput.add(lBudget);
        pInput.add(tfBudget);
        pInput.add(lMysql);
        pInput.add(tfMysqlPath);
        this.setLayout(new BorderLayout());
        this.add(pInput,BorderLayout.NORTH);

        pSubmit.add(bSubmit);
        this.add(pSubmit,BorderLayout.CENTER);

        addListener();  //并在构造方法中调用addListener()

    }

    /**
     * 因为ConfigPanel之前没有updateData()，所以需要提供该方法
     * 在updateData()中，通过ConfigService获取预算和MYSQL路径数据，并显示在组件上
     */
    @Override
    public void updateData() {
        String budget = new ConfigService().get(ConfigService.budget);
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        tfBudget.setText(budget);
        tfMysqlPath.setText(mysqlPath);
        tfBudget.grabFocus();
    }

    /**
     * 在ConfigPanel添加监听器
     */
    public void addListener() {
        ConfigListener l =new ConfigListener();
        bSubmit.addActionListener(l);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ConfigPanel.instance);
    }

}
