package com.example.broom.homeworktwo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.broom.homeworktwo.CharacterBuilding.Hero;

public class VictoryOrDeath extends Activity {
    Hero hero;
    static boolean battling, enemyAlive, playerAlive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory_or_death);
        Intent intent = getIntent();
        hero = intent.getParcelableExtra("Hero");
        battling = intent.getBooleanExtra("battling", battling);
        enemyAlive = intent.getBooleanExtra("enemyAlive", enemyAlive);
        playerAlive = intent.getBooleanExtra("playerAlive", playerAlive);

        if(playerAlive == true){
            //set image for victory
            ImageView imgView = (ImageView)findViewById(R.id.result);
            imgView.setImageResource(R.drawable.party);
            imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            //set text for victory
            TextView txtView = (TextView)findViewById(R.id.diedOrLived);
            txtView.setText("You have lived! Congratulations");
        }else if(enemyAlive == true){
            //set image for death
            ImageView imgView = (ImageView)findViewById(R.id.result);
            imgView.setImageResource(R.drawable.death);
            imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            //set text for victory
            TextView txtView = (TextView)findViewById(R.id.diedOrLived);
            txtView.setTextColor(getResources().getColor(R.color.red));
            txtView.setText("YOU HAVE DIED");
        }

    }
}
