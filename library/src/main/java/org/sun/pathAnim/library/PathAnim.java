package org.sun.pathAnim.library;

import android.annotation.TargetApi;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.animation.ValueAnimator;

import org.sun.pathAnim.library.Parser.PathParser;
import org.sun.pathAnim.library.factory.PathParserFactory;
import org.sun.pathAnim.library.factory.impl.PathParserDefaultFactory;

import java.util.ArrayList;

/**
 * Created by 王天明 on 2016/1/5 0005.
 */
public final class PathAnim {

    private void dumpLog(String msg) {
        if (DEBUG) Log.d(TAG, msg);
    }

    private PathAnim() {
        path = new Path();
    }

    private PathAnim(Path path, PathParserFactory pathParserFactory) {
        this.path = new Path();
        this.path.addPath(path);
        this.pathParserFactory = pathParserFactory;
    }

    public static PathAnim create(Path path) {
        return new PathAnim(path, new PathParserDefaultFactory());
    }

    public static PathAnim create(Path path, PathParserFactory pathParserFactory) {
        return new PathAnim(path, pathParserFactory);
    }

    public static PathAnim create() {
        return new PathAnim();
    }

    public PathAnim setDuration(long duration) {
        this.duration = duration;
        if (valueAnimator != null) {
            valueAnimator.setDuration(duration);
        }
        return this;
    }

    public PathAnim offsetX(float offsetX) {
        this.offsetX = offsetX;
        return this;
    }

    public PathAnim offsetY(float offsetY) {
        this.offsetY = offsetY;
        return this;
    }

    public PathAnim anim(final View animView) {
        if (null == animView) {
            throw new NullPointerException("animView is null!");
        }
        this.pathParser = pathParserFactory.create(path);
        final int width = animView.getWidth();
        final int height = animView.getHeight();
        valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(duration);
        valueAnimator.setObjectValues(new PointF(0, 0));
        if (interpolator != null)
            valueAnimator.setInterpolator(interpolator);
        valueAnimator.setEvaluator(pathParser.getEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                dumpLog("x:" + point.x);
                dumpLog("y:" + point.y);
                ViewCompat.setX(animView, point.x - (width / 2) + offsetX);
                ViewCompat.setY(animView, point.y - (height / 2) + offsetY);
            }
        });
        animatorDelegate = new AnimatorDelegate(valueAnimator);
        return this;
    }

    public AnimatorDelegate getAnimatorDelegate() {
        if (animatorDelegate == null) {
            throw new IllegalArgumentException("before call this,you must be call Method anim(View v)");
        }
        return animatorDelegate;
    }

    public PathAnim addListener(Animator.AnimatorListener listener) {
        if (valueAnimator == null) {
            throw new IllegalArgumentException("before call this,you must be call Method anim(View v)");
        }
        valueAnimator.addListener(listener);
        return this;
    }

    public PathAnim addUpdateListener(ValueAnimator.AnimatorUpdateListener listener) {
        if (valueAnimator == null) {
            throw new IllegalArgumentException("before call this,you must be call Method anim(View v)");
        }
        valueAnimator.addUpdateListener(listener);
        return this;
    }

    public PathAnim setInterpolator(Interpolator value) {
        this.interpolator = value;
        if (valueAnimator != null) {
            valueAnimator.setInterpolator(value);
        }
        return this;
    }

    public void start() {
        if (valueAnimator == null) {
            throw new IllegalArgumentException("before call this,you must be call Method anim(View v)");
        }
        valueAnimator.start();
    }

    public void cancel() {
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    /////////////////////////////////////////////path delegate///////////////////////////////////////////
    public void reset() {
        path.reset();
    }

    public PathAnim addRect(float left, float top, float right, float bottom, Path.Direction dir) {
        path.addRect(left, top, right, bottom, dir);
        return this;
    }

    public void close() {
        path.close();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public boolean op(Path path1, Path path2, Path.Op op) {
        return path.op(path1, path2, op);
    }

    public PathAnim offset(float dx, float dy, Path dst) {
        path.offset(dx, dy, dst);
        return this;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PathAnim arcTo(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean forceMoveTo) {
        path.arcTo(left, top, right, bottom, startAngle, sweepAngle, forceMoveTo);
        return this;
    }

    public PathAnim addPath(Path src) {
        path.addPath(src);
        return this;
    }

    public PathAnim addRoundRect(RectF rect, float rx, float ry, Path.Direction dir) {
        path.addRoundRect(rect, rx, ry, dir);
        return this;
    }

    public void rewind() {
        path.rewind();
    }

    public PathAnim arcTo(RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo) {
        path.arcTo(oval, startAngle, sweepAngle, forceMoveTo);
        return this;
    }

    public PathAnim addOval(RectF oval, Path.Direction dir) {
        path.addOval(oval, dir);
        return this;
    }

    public PathAnim transform(Matrix matrix, Path dst) {
        path.transform(matrix, dst);
        return this;
    }

    public PathAnim rLineTo(float dx, float dy) {
        path.rLineTo(dx, dy);
        return this;
    }

    public PathAnim addRect(RectF rect, Path.Direction dir) {
        path.addRect(rect, dir);
        return this;
    }

    public PathAnim addPath(Path src, Matrix matrix) {
        path.addPath(src, matrix);
        return this;
    }

    public PathAnim rQuadTo(float dx1, float dy1, float dx2, float dy2) {
        path.rQuadTo(dx1, dy1, dx2, dy2);
        return this;
    }

    public PathAnim addCircle(float x, float y, float radius, Path.Direction dir) {
        path.addCircle(x, y, radius, dir);
        return this;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PathAnim addOval(float left, float top, float right, float bottom, Path.Direction dir) {
        path.addOval(left, top, right, bottom, dir);
        return this;
    }

    public PathAnim set(Path src) {
        path.set(src);
        return this;
    }

    public PathAnim lineTo(float x, float y) {
        path.lineTo(x, y);
        return this;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PathAnim addArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle) {
        path.addArc(left, top, right, bottom, startAngle, sweepAngle);
        return this;
    }

    public PathAnim incReserve(int extraPtCount) {
        path.incReserve(extraPtCount);
        return this;
    }

    public PathAnim addArc(RectF oval, float startAngle, float sweepAngle) {
        path.addArc(oval, startAngle, sweepAngle);
        return this;
    }

    public PathAnim moveTo(float x, float y) {
        path.moveTo(x, y);
        return this;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PathAnim addRoundRect(float left, float top, float right, float bottom, float rx, float ry, Path.Direction dir) {
        path.addRoundRect(left, top, right, bottom, rx, ry, dir);
        return this;
    }

    public Path.FillType getFillType() {
        return path.getFillType();
    }

    public boolean isEmpty() {
        return path.isEmpty();
    }

    public boolean isInverseFillType() {
        return path.isInverseFillType();
    }

    public PathAnim arcTo(RectF oval, float startAngle, float sweepAngle) {
        path.arcTo(oval, startAngle, sweepAngle);
        return this;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public boolean isConvex() {
        return path.isConvex();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PathAnim addRoundRect(float left, float top, float right, float bottom, float[] radii, Path.Direction dir) {
        path.addRoundRect(left, top, right, bottom, radii, dir);
        return this;
    }

    public PathAnim offset(float dx, float dy) {
        path.offset(dx, dy);
        return this;
    }

    public PathAnim rMoveTo(float dx, float dy) {
        path.rMoveTo(dx, dy);
        return this;
    }

    public PathAnim addRoundRect(RectF rect, float[] radii, Path.Direction dir) {
        path.addRoundRect(rect, radii, dir);
        return this;
    }

    public PathAnim computeBounds(RectF bounds, boolean exact) {
        path.computeBounds(bounds, exact);
        return this;
    }

    public PathAnim rCubicTo(float x1, float y1, float x2, float y2, float x3, float y3) {
        path.rCubicTo(x1, y1, x2, y2, x3, y3);
        return this;
    }

    public PathAnim setLastPoint(float dx, float dy) {
        path.setLastPoint(dx, dy);
        return this;
    }

    public PathAnim toggleInverseFillType() {
        path.toggleInverseFillType();
        return this;
    }

    public boolean isRect(RectF rect) {
        return path.isRect(rect);
    }

    public PathAnim transform(Matrix matrix) {
        path.transform(matrix);
        return this;
    }

    public PathAnim cubicTo(float x1, float y1, float x2, float y2, float x3, float y3) {
        path.cubicTo(x1, y1, x2, y2, x3, y3);
        return this;
    }

    public PathAnim setFillType(Path.FillType ft) {
        path.setFillType(ft);
        return this;
    }

    public PathAnim addPath(Path src, float dx, float dy) {
        path.addPath(src, dx, dy);
        return this;
    }

    public PathAnim quadTo(float x1, float y1, float x2, float y2) {
        path.quadTo(x1, y1, x2, y2);
        return this;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public boolean op(Path path, Path.Op op) {
        return this.path.op(path, op);
    }
    /////////////////////////////////////////////path delegate end///////////////////////////////////////////


    public final class AnimatorDelegate {

        private ValueAnimator valueAnimator;

        public AnimatorDelegate(ValueAnimator valueAnimator) {
            this.valueAnimator = valueAnimator;
        }

        public ValueAnimator getValueAnimator() {
            return valueAnimator;
        }

        public ArrayList<Animator.AnimatorListener> getListeners() {
            return valueAnimator.getListeners();
        }

        public boolean isStarted() {
            return valueAnimator.isStarted();
        }

        public long getCurrentPlayTime() {
            return valueAnimator.getCurrentPlayTime();
        }

        public ValueAnimator setDuration(long duration) {
            return valueAnimator.setDuration(duration);
        }

        public Object getAnimatedValue() {
            return valueAnimator.getAnimatedValue();
        }

        public void reverse() {
            valueAnimator.reverse();
        }

        public void end() {
            valueAnimator.end();
        }

        public long getStartDelay() {
            return valueAnimator.getStartDelay();
        }

        public void setEvaluator(TypeEvaluator value) {
            valueAnimator.setEvaluator(value);
        }

        public boolean isRunning() {
            return valueAnimator.isRunning();
        }

        public void removeUpdateListener(ValueAnimator.AnimatorUpdateListener listener) {
            valueAnimator.removeUpdateListener(listener);
        }

        public int getRepeatCount() {
            return valueAnimator.getRepeatCount();
        }

        public void setupEndValues() {
            valueAnimator.setupEndValues();
        }

        public void setRepeatMode(int value) {
            valueAnimator.setRepeatMode(value);
        }

        public void setIntValues(int... values) {
            valueAnimator.setIntValues(values);
        }

        public int getRepeatMode() {
            return valueAnimator.getRepeatMode();
        }

        public void setupStartValues() {
            valueAnimator.setupStartValues();
        }

        public float getAnimatedFraction() {
            return valueAnimator.getAnimatedFraction();
        }

        public void setTarget(Object target) {
            valueAnimator.setTarget(target);
        }

        public void setInterpolator(Interpolator value) {
            valueAnimator.setInterpolator(value);
        }

        public void cancel() {
            valueAnimator.cancel();
        }

        public long getDuration() {
            return valueAnimator.getDuration();
        }

        public void setFloatValues(float... values) {
            valueAnimator.setFloatValues(values);
        }

        public void addUpdateListener(ValueAnimator.AnimatorUpdateListener listener) {
            valueAnimator.addUpdateListener(listener);
        }

        public void setStartDelay(long startDelay) {
            valueAnimator.setStartDelay(startDelay);
        }

        public PropertyValuesHolder[] getValues() {
            return valueAnimator.getValues();
        }

        public Interpolator getInterpolator() {
            return valueAnimator.getInterpolator();
        }

        public void setCurrentPlayTime(long playTime) {
            valueAnimator.setCurrentPlayTime(playTime);
        }

        public void removeListener(Animator.AnimatorListener listener) {
            valueAnimator.removeListener(listener);
        }

        public void setValues(PropertyValuesHolder... values) {
            valueAnimator.setValues(values);
        }

        public void addListener(Animator.AnimatorListener listener) {
            valueAnimator.addListener(listener);
        }

        public void removeAllListeners() {
            valueAnimator.removeAllListeners();
        }

        public Object getAnimatedValue(String propertyName) {
            return valueAnimator.getAnimatedValue(propertyName);
        }

        public void setRepeatCount(int value) {
            valueAnimator.setRepeatCount(value);
        }

        public void start() {
            valueAnimator.start();
        }

        public void removeAllUpdateListeners() {
            valueAnimator.removeAllUpdateListeners();
        }

        public void setObjectValues(Object... values) {
            valueAnimator.setObjectValues(values);
        }
    }

    private Path path;
    private PathParser pathParser;
    private long duration = 500;
    private ValueAnimator valueAnimator;
    private float offsetX = .0f, offsetY = .0f;
    private Interpolator interpolator;
    private final static boolean DEBUG = false;
    private final static String TAG = "PathAnim";
    private AnimatorDelegate animatorDelegate;
    private PathParserFactory pathParserFactory;
}