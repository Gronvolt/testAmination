package com.example.testamination;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ----- Toolbar -------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.WHITE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_animation_rotation: {
                final FrameLayout flOne = (FrameLayout) this.findViewById(R.id.flOne);

                ValueAnimator anim = (ValueAnimator)
                        AnimatorInflater.loadAnimator(this, R.animator.rotation_value_animator);

                //		anim.setInterpolator(new AnticipateOvershootInterpolator());

                anim.addUpdateListener(
                        new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                float value = (float) animation.getAnimatedValue();
                                flOne.setRotation(value);
                            }
                        });

                anim.start();
            }
            return true;

            case R.id.action_animation_complex: {
                final FrameLayout flTwo = (FrameLayout) this.findViewById(R.id.flTwo);

                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(
                        this, R.animator.complex_value_animator);

                // ----- Add an event handler for the amount of rotation ---------------
                // ----- Добавляем обработчик события для величины поворота ------------
                ArrayList<Animator> arrL = set.getChildAnimations();

                ValueAnimator vaRotate = (ValueAnimator) arrL.get(0);
                vaRotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        flTwo.setRotation(value);
                    }
                });

                // ----- Add an event handler for the x position value -----------------
                // ----- Добавляем обработчик события для величины x позиции -----------
                ValueAnimator vaX = (ValueAnimator) arrL.get(1);
                vaX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        flTwo.setX(value);
                    }
                });

                set.start();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void btnClick(View v) {
        final Button btn1 = (Button) this.findViewById(R.id.btn1);

        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(
                this, R.animator.complex_value_animator);

        // ----- Add an event handler for the amount of rotation ---------------
        // ----- Добавляем обработчик события для величины поворота ------------
        ArrayList<Animator> arrL = set.getChildAnimations();

        ValueAnimator vaRotate = (ValueAnimator) arrL.get(0);
        vaRotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                btn1.setRotation(value);
            }
        });

        // ----- Add an event handler for the x position value -----------------
        // ----- Добавляем обработчик события для величины x позиции -----------
        ValueAnimator vaX = (ValueAnimator) arrL.get(1);
        vaX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                btn1.setX(value);
            }
        });

        set.start();

    }
}