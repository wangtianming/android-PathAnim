package org.sun.pathAnim.library.Parser.impl;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;

import com.nineoldandroids.animation.TypeEvaluator;

import org.sun.pathAnim.library.Parser.PathParser;

/**
 * Created by 王天明 on 2016/1/4 0004.
 */
public final class PathParserImpl implements PathParser {

    private static final Region MAX_CLIP =
            new Region(Integer.MIN_VALUE, Integer.MIN_VALUE,
                    Integer.MAX_VALUE, Integer.MAX_VALUE);

    final Path path;
    final Region region;
    float length;
    final PathMeasure measure;
    final Rect bounds;
    float pos[] = new float[2];

    public PathParserImpl(Path path) {
        this.path = path;
        measure = new PathMeasure();
        measure.setPath(path, false);
        this.length = measure.getLength();
        region = new Region();
        region.setPath(path, MAX_CLIP);
        bounds = region.getBounds();
    }

    public Region getRegion() {
        return region;
    }

    @Override
    public boolean contains(float x, float y) {
        return region.contains((int) x, (int) y);
    }

    @Override
    public Rect getBounds() {
        return bounds;
    }

    @Override
    public TypeEvaluator<PointF> getEvaluator() {
        return new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue) {
                PointF point = new PointF();
                measurePath(fraction);
                point.x = pos[0];
                point.y = pos[1];
                return point;
            }
        };
    }

    private void measurePath(float fraction) {
        measure.getPosTan(fraction * measure.getLength(), pos, null);
    }
    @Override
    public float[] measurePathWithFraction(float fraction) {
        measurePath(fraction);
        return pos;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        path.reset();
        measure.getSegment(0.0f, length, path, true);
        path.rLineTo(0.0f, 0.0f);
    }

}
