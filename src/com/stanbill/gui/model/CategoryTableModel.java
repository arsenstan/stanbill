package com.stanbill.gui.model;
/**
 * 之前做分类信息原型的时候CategoryTableModel里使用的都是模拟数据，现在应该使用数据库中的数据了。
 */

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.stanbill.entity.Category;
import com.stanbill.service.CategoryService;

public class CategoryTableModel extends AbstractTableModel {

    String[] columnNames = new String[] { "分类名称", "消费次数"};

    // 使用从Service返回的List作为TableModel的数据

    public List<Category> cs = new CategoryService().list();  //存放的数据由String集合，改为了Category集合，并且从CategoryService的list()方法获取数据

    public int getRowCount() {

        return cs.size();
    }

    public int getColumnCount() {

        return columnNames.length;
    }

    public String getColumnName(int columnIndex) {

        return columnNames[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    // 先通过cs.get(rowIndex)获取行对应的Category对象
    // 然后根据columnIndex返回对应的属性
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category h = cs.get(rowIndex);
        if (0 == columnIndex)
            return h.name;
        if (1 == columnIndex)
            return h.recordNumber;

        return null;
    }

}
