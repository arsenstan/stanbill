package com.stanbill.util;

import java.awt.Color;
/*
定义了几种颜色
blueColor 淡蓝色
grayColor 灰色
backgroundColor 背景色
warningColor 警告红色
 */
public class ColorUtil {
    public static Color blueColor = Color.decode("#3399FF");
    public static Color grayColor = Color.decode("#999999");
    public static Color backgroundColor = Color.decode("#eeeeee");
    public static Color warningColor = Color.decode("#FF3333");
    //getByPercentage根据进度显示不同的颜色
    // 当进度是接近0的时候，就会显示绿色
    //当进度接近100的时候，就会显示红色
    //并在不同的颜色间过渡
    public static Color getByPercentage(int per) {
        if (per > 100)
            per = 100;
        int r = 51;
        int g = 255;
        int b = 51;
        float rate = per / 100f;
        r = (int) ((255 - 51) * rate + 51);
        g = 255 - r + 51;
        Color color = new Color(r, g, b);
        return color;
    }
}
