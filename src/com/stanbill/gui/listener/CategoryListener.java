package com.stanbill.gui.listener;
/**
 * CategoryListener监听器，增加，编辑和删除按钮都使用这个监听器。
 *
 * 1. 与监听器ToolBarListener 类似的，根据事件的发出源，判断是哪个按钮触发了这个监听器，并做相应的功能。
 *
 * 2. 如果是增加，弹出输入框，校验输入内容不为空后，通过CategoryService.add()添加到数据库。
 *
 * 3. 如果是编辑，弹出输入框，校验输入内容不为空后，根据CategoryPanel中getSelectedCategory()获取id, 然后再通过CategoryService.update更新到数据库
 *
 * 4. 如果是删除，首先判断是否有消费记录，如果有消费记录，则不能删除。 接着进行删除确认提示，确认后，通过CategoryService.delete()进行删除。
 *
 * 5. 最后调用p.updateData();更新数据
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.stanbill.entity.Category;
import com.stanbill.gui.panel.CategoryPanel;
import com.stanbill.service.CategoryService;

public class CategoryListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryPanel p = CategoryPanel.instance;

        JButton b = (JButton) e.getSource();
        if (b == p.bAdd) {
            String name = JOptionPane.showInputDialog(null);
            if (0 == name.length()) {
                JOptionPane.showMessageDialog(p, "分类名称不能为空");
                return;
            }

            new CategoryService().add(name);

        }
        if (b == p.bEdit) {
            Category c = p.getSelectedCategory();
            int id = c.id;
            String name = JOptionPane.showInputDialog("修改分类名称", c.name);
            if (0 == name.length()) {
                JOptionPane.showMessageDialog(p, "分类名称不能为空");
                return;
            }

            new CategoryService().update(id, name);
        }
        if (b == p.bDelete) {
            Category c = p.getSelectedCategory();
            if (0 != c.recordNumber) {
                JOptionPane.showMessageDialog(p, "本分类下有消费记录存在，不能删除");
                return;
            }
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确认要删除？"))
                return;

            int id = c.id;
            new CategoryService().delete(id);
        }
        p.updateData();
    }

}
