package com.stanbill.gui.panel;

import static com.stanbill.util.GUIUtil.showPanel;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.stanbill.entity.Record;
import com.stanbill.service.ReportService;
import com.stanbill.util.ChartUtil;

/*
v1:ReportPanel就比较简单了，使用的是ChartUtil现成的方法生成了当月的模拟数据。

v2:ReportPanel报表面板类继承WorkingPanel，实现updateDate()方法来更新界面上的数据
 */

public class ReportPanel extends WorkingPanel {
    public static ReportPanel instance = new ReportPanel();

    JLabel l = new JLabel();

    public ReportPanel() {
        this.setLayout(new BorderLayout());
        List<Record> rs = new ReportService().listThisMonthRecords();
        Image i = ChartUtil.getImage(rs, 400, 300);
        ImageIcon icon = new ImageIcon(i);
        l.setIcon(icon);
        this.add(l);
        addListener();
    }

    public static void main(String[] args) {
        showPanel(ReportPanel.instance);
    }

    @Override
    public void updateData() {
        List<Record> rs = new ReportService().listThisMonthRecords();
        Image i = ChartUtil.getImage(rs, 350, 250);
        ImageIcon icon = new ImageIcon(i);
        l.setIcon(icon);
    }

    @Override
    public void addListener() {

    }
}
/*
public class ReportPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }

    public static ReportPanel instance = new ReportPanel();

    public JLabel l = new JLabel();

    //在构造其中获取图表，并把图表设置在Label上，然后显示这个Label
    public ReportPanel() {
        this.setLayout(new BorderLayout());
        Image i =ChartUtil.getImage(400, 300);
        ImageIcon icon= new ImageIcon(i);
        l.setIcon(icon);
        this.add(l);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ReportPanel.instance);
    }

}
*/