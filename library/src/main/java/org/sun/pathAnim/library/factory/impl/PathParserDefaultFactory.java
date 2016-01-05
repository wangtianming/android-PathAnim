package org.sun.pathAnim.library.factory.impl;

import android.graphics.Path;

import org.sun.pathAnim.library.Parser.PathParser;
import org.sun.pathAnim.library.factory.PathParserFactory;
import org.sun.pathAnim.library.Parser.impl.PathParserImpl;

/**
 * Created by wangtianming on 2016/1/5 0005.
 */
public class PathParserDefaultFactory implements PathParserFactory {

    @Override
    public PathParser create(Path path) {
        return new PathParserImpl(path);
    }
}
