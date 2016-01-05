package org.sun.pathAnim.library;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;

/**
 * Created by wangtianming on 2016/1/5 0005.
 */
public class PathAnimInject {

    private Activity activity;
    private FrameLayout animLayout;

    public PathAnimInject(Activity activity) {
        this.activity = activity;
    }

    private FrameLayout injectAnimLayout() {
        if (null == animLayout) {
            this.animLayout = (FrameLayout) this.activity.findViewById(R.id.animLayout);
            if (null == activity.findViewById(R.id.animLayout)) {
                animLayout = new FrameLayout(activity);
                animLayout.setId(R.id.animLayout);
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                animLayout.setLayoutParams(lp);
                animLayout.setBackgroundResource(android.R.color.transparent);
                ((ViewGroup) activity.getWindow().getDecorView()).addView(animLayout);
            }
        }
        return animLayout;
    }

    public PathAnim injectAnimView(final View animView,int width,int height,PathAnim pathAnim) {
        if (animView.getParent() != null) {
            ((ViewGroup) animView.getParent()).removeView(animView);
        }
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(width, height);
        animView.setLayoutParams(lp);
        lp.setMargins(0, 0, 0, 0);
        animView.setPadding(0, 0, 0, 0);
        injectAnimLayout().addView(animView);
        return pathAnim.anim(animView).addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ((ViewGroup)animView.getParent()).removeView(animView);
            }
        });
    }

    public void injectAnimView(final View animView,PathAnim pathAnim) {
        injectAnimView(animView,-2,-2,pathAnim);
    }

    public void destroy() {
        if (animLayout != null) {
            ((ViewGroup) animLayout.getParent()).removeView(animLayout);
            animLayout = null;
        }
    }
}
