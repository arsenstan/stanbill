package com.stanbill.gui.panel;
/**
 * RecordPanel 消费记录面板做了如下改动
 * 1. 继承了WorkingPanel，从而必须提供addListener()和updateData()方法
 * 2. 提供getSelectedCategory()用于获取当前选中的分类对象。
 * 3. addListener() 给按钮添加监听
 * 4. updateData()更新数据，主要是更新下拉框中的分类信息，并且让各个输入框信息重置，以及让焦点停留在金额的输入框上
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import com.stanbill.gui.model.CategoryComboBoxModel;
import com.stanbill.util.GUIUtil;
import com.stanbill.util.ColorUtil;
import com.stanbill.entity.Category;
import com.stanbill.gui.listener.RecordListener;
import com.stanbill.service.CategoryService;

/*
这个面板采用BorderLayout 分为北面和中间。

北面是一个JPanel，中间也是一个JPanel。

北面的JPanel使用4行2列的 GridLayout。

中间的JPanel使用默认的FlowLayout,
 */
public class RecordPanel extends WorkingPanel{
    static{
        GUIUtil.useLNF();
    }
    public static RecordPanel instance = new RecordPanel();

    JLabel lSpend = new JLabel("花费(￥)");
    JLabel lCategory = new JLabel("分类");
    JLabel lComment = new JLabel("备注");
    JLabel lDate = new JLabel("日期");

    public JTextField tfSpend = new JTextField("0");

    //下拉框用到了JComboBox，在创建这个下拉框的时候，借助了类似于TableModel的形式，使用ComboBoxModel把数据分离开来，这样做的目的是为了便于下拉框中分类信息的更新。
    // 因为在后续的开发中，分类信息是从数据库中取出来的，很有可能发生变化。
    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
   // public JComboBox<String> cbCategory = new JComboBox<>(cbModel);
    public JComboBox<Category> cbCategory = new JComboBox<>(cbModel);

    public JTextField tfComment = new JTextField();

    //本面板使用到了JXDatePicker 这个日期控件，用于显示当前日期。
    // Swing没有自带的日期控件。需要用到swingx-core-1.6.2.jar
    public JXDatePicker datepick = new JXDatePicker(new Date());

    JButton bSubmit = new JButton("记一笔");

    public RecordPanel() {
        GUIUtil.setColor(ColorUtil.grayColor, lSpend,lCategory,lComment,lDate);      //给标签和按钮分别上灰色和淡蓝色
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);
        JPanel pInput =new JPanel();
        JPanel pSubmit = new JPanel();
        int gap = 40;
        pInput.setLayout(new GridLayout(4,2,gap,gap));

        pInput.add(lSpend);
        pInput.add(tfSpend);
        pInput.add(lCategory);
        pInput.add(cbCategory);
        pInput.add(lComment);
        pInput.add(tfComment);
        pInput.add(lDate);
        pInput.add(datepick);

        pSubmit.add(bSubmit);

        //把不同的子面板进行布局
        this.setLayout(new BorderLayout());
        this.add(pInput,BorderLayout.NORTH);
        this.add(pSubmit,BorderLayout.CENTER);

        addListener();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(RecordPanel.instance);
    }

    public Category getSelectedCategory(){
        return (Category) cbCategory.getSelectedItem();
    }

    @Override
    public void updateData() {
        cbModel.cs = new CategoryService().list();
        cbCategory.updateUI();
        resetInput();
        tfSpend.grabFocus();
    }

    public void resetInput(){
        tfSpend.setText("0");
        tfComment.setText("");
        if(0!=cbModel.cs.size())
            cbCategory.setSelectedIndex(0);
        datepick.setDate(new Date());
    }

    @Override
    public void addListener() {
        // TODO Auto-generated method stub
        RecordListener listener = new RecordListener();
        bSubmit.addActionListener(listener);
    }


}
