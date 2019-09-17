package com.stanbill.util;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIUtil {   //图形界面工具类
    private static String imageFolder = "e:/project/hutubill/img";
    //给按钮设置图标和文本以及提示文字
    public static void setImageIcon(JButton b, String fileName, String tip) {
        ImageIcon i = new ImageIcon(new File(imageFolder, fileName).getAbsolutePath());
        b.setIcon(i);
        b.setPreferredSize(new Dimension(61, 81));
        b.setToolTipText(tip);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);
    }
    //给多个组件设置前景色
    public static void setColor(Color color, JComponent... cs) {
        for (JComponent c : cs) {
            c.setForeground(color);
        }
    }
    //快速显示一个面板的内容
    /**
     *
     * @param p
     * @param strechRate 拉伸比例1表示满屏幕
     */
    public static void showPanel(JPanel p,double strechRate) {
        GUIUtil.useLNF();
        JFrame f = new JFrame();
        f.setSize(500, 500);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(strechRate);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        cp.show(p);
    }
    public static void showPanel(JPanel p) {      //方便测试用
        showPanel(p,0.85);
    }
    //校验一个组件内容是否是数字格式
    public static boolean checkNumber(JTextField tf, String input) {
        if (!checkEmpty(tf, input))
            return false;
        String text = tf.getText().trim();
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, input + " 需要是整数");
            tf.grabFocus();
            return false;
        }
    }
    //校验一个组件的内容是否是零
    public static boolean checkZero(JTextField tf, String input) {
        if (!checkNumber(tf, input))
            return false;
        String text = tf.getText().trim();

        if (0 == Integer.parseInt(text)) {
            JOptionPane.showMessageDialog(null, input + " 不能为零");
            tf.grabFocus();
            return false;
        }
        return true;
    }
    //校验一个输入框内容是否是空
    public static boolean checkEmpty(JTextField tf, String input) {
        String text = tf.getText().trim();                     //JTextField 输入 setText 设置文本 getText 获取文本
                                                              //JTextField 是单行文本框，如果要输入多行数据，请使用JTextArea
        if (0 == text.length()) {
            JOptionPane.showMessageDialog(null, input + " 不能为空");        //JOptionPane 用于弹出对话框
                                                                                                        //JOptionPane.showConfirmDialog(f, "是否 使用外挂 ？");
                                                                                                       //表示询问，第一个参数是该对话框以哪个组件对齐
                                                                                                       //JOptionPane.showInputDialog(f, "请输入yes，表明使用外挂后果自负");
                                                                                                       //接受用户的输入
                                                                                                       //JOptionPane.showMessageDialog(f, "你使用外挂被抓住！ 罚拣肥皂3次！");
                                                                                                        //显示消息
            tf.grabFocus();                                    //表示让输入框获取焦点
            return false;
        }
        return true;

    }

    public static int getInt(JTextField tf) {
        return Integer.parseInt(tf.getText());
    }
    //使用水晶皮肤
    public static void useLNF() {
        try {
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
