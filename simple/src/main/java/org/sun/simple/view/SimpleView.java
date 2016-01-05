package org.sun.simple.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 王天明 on 2016/1/4 0004.
 */
public class SimpleView extends View {

    Paint p_path;
    Paint p_point;
    float startX,startY;
    float endX,endY;
    float referX,referY;
    Path path;

    boolean openEditor = false;

    public SimpleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p_path = new Paint();
        p_path.setColor(Color.RED);
        p_path.setStyle(Paint.Style.STROKE);
        p_path.setStrokeWidth(10);

        p_point = new Paint();
        p_point.setColor(Color.YELLOW);
        p_point.setStyle(Paint.Style.STROKE);
        p_point.setStrokeWidth(10);


        startX = 650;
        startY = 300;

        referX = 300;
        referY = 0;

        endX = 100;
        endY = 900;

        path = new Path();
        path.moveTo(startX, startY);
        path.quadTo(referX, referY, endX, endY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        path.moveTo(startX, startY);
        path.quadTo(referX, referY, endX, endY);

        canvas.drawPath(path, p_path);
        canvas.drawPoint(startX, startY, p_point);
        canvas.drawPoint(endX,endY,p_point);
        canvas.drawPoint(referX,referY,p_point);
    }

    public Path getPath() {
        return path;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }

    public float getReferX() {
        return referX;
    }

    public void setReferX(float referX) {
        this.referX = referX;
    }

    public float getReferY() {
        return referY;
    }

    public void setReferY(float referY) {
        this.referY = referY;
    }
}
