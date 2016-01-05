package org.sun.simple;

import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.animation.ValueAnimator;

import org.sun.pathAnim.library.PathAnim;
import org.sun.pathAnim.library.PathAnimInject;
import org.sun.pathAnim.library.PathAnimation;
import org.sun.simple.view.SimpleView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private SimpleView simpleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        simpleView = (SimpleView) findViewById(R.id.simpleView);
    }

    public void startAnim(View v) {
        PathAnim pathAnim = PathAnim
                .create(simpleView.getPath())
//                .lineTo(100, 100)
//                .lineTo(300, 300)
//                .lineTo(50, 300)
//                .lineTo(500, 500)
                .anim(imageView)
                .setDuration(3000);
        PathAnim.AnimatorDelegate delegate = pathAnim.getAnimatorDelegate();
        delegate.setRepeatCount(1);
        delegate.start();//or pathAnim.start();
    }

    public void startAnimation(View v) {
        PathAnimation pathAnimation = new PathAnimation(simpleView.getPath());
        pathAnimation.setDuration(3000);
        imageView.startAnimation(pathAnimation);
    }

    public void option(View v) {
        startActivityForResult(new Intent(this, OptionActivity.class), 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == 1000 && resultCode == RESULT_OK) {
            simpleView.setStartX(data.getFloatExtra("StartX", 0));
            simpleView.setStartY(data.getFloatExtra("StartY", 0));
            simpleView.setReferX(data.getFloatExtra("ReferX", 0));
            simpleView.setReferX(data.getFloatExtra("ReferY", 0));
            simpleView.setEndX(data.getFloatExtra("EndX", 0));
            simpleView.setEndY(data.getFloatExtra("EndY", 0));
            simpleView.postInvalidate();
        }
    }
}