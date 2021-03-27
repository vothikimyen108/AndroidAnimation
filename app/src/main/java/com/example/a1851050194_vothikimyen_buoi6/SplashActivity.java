package com.example.a1851050194_vothikimyen_buoi6;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class SplashActivity extends AppCompatActivity {

    ImageView ivTop, ivBottom, ivHeart, ivBeat;
    TextView textView;
    CharSequence charSequence;
    int index;
    long delay = 200;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );
        AnhXa();
        //set full man hinh
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //khai bao hieu ung cho top
        Animation animationTop = AnimationUtils.loadAnimation( this,R.anim.top_ware );
        //bat dau hieu usng top
        ivTop.setAnimation(animationTop);
        //tao obiect hieu ung
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                ivHeart,
                PropertyValuesHolder.ofFloat( "scaleX",1.2f),
                PropertyValuesHolder.ofFloat( "scaleY",1.2f)
        );
        objectAnimator.setDuration(500);
        objectAnimator.setRepeatCount( ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode( ValueAnimator.REVERSE);
        objectAnimator.start();
        //load gif
        Glide.with( this ).load( "https://i.giphy.com/lo9OHBZzaM7W8Lg5L0.gif").asGif().diskCacheStrategy( DiskCacheStrategy.ALL ).into( ivBeat );
        //khai bao hieu ung cho bottom
        Animation animationbottom = AnimationUtils.loadAnimation( this,R.anim.bot_ware );
        //bat dau hieu usng top
        ivBottom.setAnimation(animationbottom);
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                startActivity( new Intent(SplashActivity.this,MainActivity.class).setFlags( Intent.FLAG_ACTIVITY_NEW_TASK ));
                finish();
            }
        },4000);

    }
    private void AnhXa() {
        ivTop = findViewById(R.id.iv_top);
        ivBottom = findViewById(R.id.iv_bottm);
        ivHeart = findViewById(R.id.iv_heart);
        ivBeat = findViewById(R.id.iv_beat);
        textView = findViewById(R.id.textView);
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            textView.setText( charSequence.subSequence( 0,index++));
            if(index<=charSequence.length()){
                handler.postDelayed( runnable,delay);
            }
        }
    };
    //tao hieu ung cho text
    public void animaText(CharSequence cs){
        charSequence = cs;
        index =0;
        textView.setText("");
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable,delay);
    }
}