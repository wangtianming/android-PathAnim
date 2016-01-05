package org.sun.simple;

import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import org.sun.pathAnim.library.PathAnim;

public class CirclePathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_path);

        ImageView imageView = (ImageView) findViewById(R.id.imgView);
        Path path = new Path();
        path.moveTo(100, 100);
        path.addArc(new RectF(100, 100, 500, 500), 0, 360);

        PathAnim.AnimatorDelegate delegate = PathAnim.create(path).anim(imageView).getAnimatorDelegate();
        delegate.setRepeatCount(Integer.MAX_VALUE);
        delegate.setDuration(1000);
        delegate.setInterpolator(new LinearInterpolator());
        delegate.start();
    }
}
