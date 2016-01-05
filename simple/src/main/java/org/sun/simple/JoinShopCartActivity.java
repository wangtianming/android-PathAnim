package org.sun.simple;

import android.graphics.Path;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nineoldandroids.animation.ValueAnimator;

import org.sun.pathAnim.library.PathAnim;
import org.sun.pathAnim.library.PathAnimInject;
import org.sun.simple.transform.GlideCircleTransform;

public class JoinShopCartActivity extends AppCompatActivity {

    private static final String URL_IMG = "http://7xpclw.com1.z0.glb.clouddn.com/item1.jpg";
    private int width = 100, height = 100;
    private PathAnimInject pathAnimInject;

    private ImageView img_cart;

    private Path path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_shop_cart);
        findViews();
        preLoadData();
    }

    private void findViews() {
        img_cart = (ImageView) findViewById(R.id.img_cart);
    }

    private void preLoadData() {
        //使用Glide预缓存想要加载的图片,加载的时候更加平滑
        Glide.with(this)
                .load(URL_IMG)
                .error(R.mipmap.ic_launcher)
                .transform(new GlideCircleTransform(this))
                .preload(width, height);
    }

    private void pathInit(int[] startLocation) {
        if (path != null) {
            path.reset();
        } else {
            path = new Path();
        }
        int[] endLocation = new int[2];
        img_cart.getLocationInWindow(endLocation);
        //参考点 随便弄一个喜欢的 我这里设置(300,100)
        path.moveTo(startLocation[0], startLocation[1]);
        path.quadTo(300, 0, endLocation[0], endLocation[1]);
    }

    public void joinCartAnim(View v) {
        final ImageView imageView = new ImageView(this);
        Glide.with(this)
                .load(URL_IMG)
                .error(R.mipmap.ic_launcher)
                .transform(new GlideCircleTransform(this))
                .into(imageView);
        if (path == null) {
            int[] startLocation = new int[2];
            v.getLocationInWindow(startLocation);
            pathInit(startLocation);
        }

        PathAnim pathAnim = PathAnim
                .create(path).setDuration(1500);
        if (null == pathAnimInject) pathAnimInject = new PathAnimInject(this);
        pathAnimInject.injectAnimView(imageView, width, height, pathAnim)
                .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float fraction = animation.getAnimatedFraction();
                        if (fraction < 1) {
                            ViewCompat.setScaleX(imageView, fraction);
                            ViewCompat.setScaleY(imageView, fraction);
                        }
                    }
                }).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != pathAnimInject) {
            pathAnimInject.destroy();
        }
    }
}
