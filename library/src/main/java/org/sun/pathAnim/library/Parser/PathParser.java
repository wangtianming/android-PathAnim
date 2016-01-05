package org.sun.pathAnim.library.Parser;


import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;

import com.nineoldandroids.animation.TypeEvaluator;

/**
 * Created by wangtianming on 2016/1/4 0004.
 */
public interface PathParser {

    /**
     * 是否在路径中
     * @param x
     * @param y
     * @return
     */
    boolean contains(float x,float y);
    Rect getBounds();
    Region getRegion();
    TypeEvaluator<PointF> getEvaluator();
    float[] measurePathWithFraction(float fraction);
}