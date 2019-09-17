package com.stanbill.gui.listener;
/**
 * 监听器ConfigListener，这个监听器是用在更新按钮上的
 */

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import com.stanbill.gui.panel.ConfigPanel;
import com.stanbill.service.ConfigService;
import com.stanbill.util.GUIUtil;

public class ConfigListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel p = ConfigPanel.instance;
        if(!GUIUtil.checkNumber(p.tfBudget, "本月预算"))   //首先判断输入的预算值是否是整数
            return;
        String mysqlPath =p.tfMysqlPath.getText();
        if(0!=mysqlPath.length()){           //接着判断输入的MYSQL安装路径是否正确。 判断办法是看路径对应的bin子目录下是否有mysql.exe文件存在
            File commandFile = new File(mysqlPath,"bin/mysql.exe");
            if(!commandFile.exists()){
                JOptionPane.showMessageDialog(p, "Mysql路径不正确");
                p.tfMysqlPath.grabFocus();
                return;
            }
        }

        // 如果上述两个判断都通过了，那么就调用业务类ConfigService进行数据更新
        ConfigService cs= new ConfigService();
        cs.update(ConfigService.budget,p.tfBudget.getText());
        cs.update(ConfigService.mysqlPath,mysqlPath);

        JOptionPane.showMessageDialog(p, "设置修改成功"); //最后提示设置修改成功

    }

}
