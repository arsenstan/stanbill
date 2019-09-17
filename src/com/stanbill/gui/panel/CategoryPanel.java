package com.stanbill.gui.panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.stanbill.entity.Category;
import com.stanbill.gui.model.CategoryTableModel;
import com.stanbill.service.CategoryService;
import com.stanbill.util.ColorUtil;
import com.stanbill.util.GUIUtil;
import com.stanbill.gui.listener.CategoryListener;

public class CategoryPanel extends WorkingPanel{   //CategoryPanel继承WorkingPanel
    static {
        GUIUtil.useLNF();
    }

    public static CategoryPanel instance = new CategoryPanel();

    public JButton bAdd = new JButton("新增");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");
    String columNames[] = new String[] { "分类名称", "消费次数" };

    public CategoryTableModel ctm = new CategoryTableModel();
    public JTable t = new JTable(ctm);

    public CategoryPanel() {
        GUIUtil.setColor(ColorUtil.blueColor, bAdd, bEdit, bDelete);
        JScrollPane sp = new JScrollPane(t);
        JPanel pSubmit = new JPanel();
        pSubmit.add(bAdd);
        pSubmit.add(bEdit);
        pSubmit.add(bDelete);

        this.setLayout(new BorderLayout());
        this.add(sp, BorderLayout.CENTER);
        this.add(pSubmit, BorderLayout.SOUTH);

        addListener();  //为3个按钮加上监听器
    }

    public void addListener() {
        CategoryListener listener = new CategoryListener();
        bAdd.addActionListener(listener);
        bEdit.addActionListener(listener);
        bDelete.addActionListener(listener);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(CategoryPanel.instance);
    }

    /**
     * 为CategoryPanel新增加一个getSelectedCategory，方便获取JTable上当前选中的Category对象
     * @return
     */
    public Category getSelectedCategory() {
        int index = t.getSelectedRow();
        return ctm.cs.get(index);
    }

    /**
     * 增加一个updateData方法，用于在增加，删除，和修改之后，更新表格中的信息，并默认选中第一行。
     * 除此之外，还进行判断，如果表格里没有数据，删除和修改按钮不可使用。
     */
    public void updateData() {
        ctm.cs = new CategoryService().list();
        t.updateUI();
        t.getSelectionModel().setSelectionInterval(0, 0);

        if(0==ctm.cs.size()){
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        }
        else{
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }

}
