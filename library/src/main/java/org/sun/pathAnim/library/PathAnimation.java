package org.sun.pathAnim.library;

import android.graphics.Path;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import org.sun.pathAnim.library.Parser.PathParser;
import org.sun.pathAnim.library.factory.PathParserFactory;
import org.sun.pathAnim.library.factory.impl.PathParserDefaultFactory;

/**
 * Created by 王天明 on 2016/1/5 0005.
 */
public class PathAnimation extends Animation {

    private PathParser pathParser;

    public PathAnimation(Path path) {
        pathParser = new PathParserDefaultFactory().create(path);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float[] pos = pathParser.measurePathWithFraction(interpolatedTime);
        t.getMatrix().setTranslate(pos[0], pos[1]);
    }
}
