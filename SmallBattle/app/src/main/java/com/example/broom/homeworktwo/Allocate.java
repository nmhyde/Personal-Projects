package com.example.broom.homeworktwo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.broom.homeworktwo.CharacterBuilding.Monster;
import com.example.broom.homeworktwo.CharacterBuilding.Hero;
import com.example.broom.homeworktwo.CharacterBuilding.Character;

import java.util.Locale;

public class Allocate extends Activity {
    String name = null;
    int playerSTM = 0;
    int playerHP = 100;
    int playerMaxHP = playerHP;
    int playerSTR = 0;
    int playerATK = 10;
    int playerMGC = 0;
    int playerMANA = 10;
    int playerMaxMANA = playerMANA;
    int playerLVL = 1;
    int skillPoints = 20;
    boolean battling;
    //dummy hero so i can make stuff with it throughout this class
    Hero hero = new Hero(null, 0,0,0,0,0,0,0,0,0, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate);
        if(savedInstanceState != null){
            battling = savedInstanceState.getBoolean("battling");
            name = savedInstanceState.getString("HeroName");
            playerHP = savedInstanceState.getInt("playerHP");
            playerMaxHP = savedInstanceState.getInt("playerMaxHP");
            playerATK = savedInstanceState.getInt("playerATK");
            playerSTM = savedInstanceState.getInt("playerSTM");
            playerMANA = savedInstanceState.getInt("playerMANA");
            playerMaxMANA = savedInstanceState.getInt("playerMaxMANA");
            playerMGC = savedInstanceState.getInt("playerMGC");
            playerLVL = savedInstanceState.getInt("playerLVL");
            skillPoints = savedInstanceState.getInt("skillPoints");
        }
        run();

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("HeroName", name);
        savedInstanceState.putInt("playerHP", playerHP);
        savedInstanceState.putInt("playerMaxHP", playerMaxHP);
        savedInstanceState.putInt("playerATK", playerATK);
        savedInstanceState.putInt("playerSTM", playerSTM);
        savedInstanceState.putInt("playerSTR", playerSTR);
        savedInstanceState.putInt("playerMANA", playerMANA);
        savedInstanceState.putInt("playerMaxMANA", playerMaxMANA);
        savedInstanceState.putInt("playerMGC", playerMGC);
        savedInstanceState.putInt("playerLVL", playerLVL);
        savedInstanceState.putInt("skillPoints", skillPoints);
        savedInstanceState.putBoolean("battling", battling);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    public void onClickToBattle(View view) {
        //get access to the text edit view and name the hero whatever the user inputs
        EditText myEditText = (EditText) findViewById(R.id.nameHero);
        name = myEditText.getText().toString();
        hero.setName(name);

        if(skillPoints == 0 && !(name.equals(""))) {

            //set int variables to whatever
            hero.setHealth(playerHP);
            hero.setMaxHealth(playerHP);
            hero.setAttack(playerATK);
            hero.setStamina(playerSTM);
            hero.setStrength(playerSTR);
            hero.setMana(playerMANA);
            hero.setMaxMana(playerMaxMANA);
            Log.d("max mana", "playermaxmana is: " + playerMaxMANA);
            Log.d("max mana", "hero.getmaxmana is: " + hero.getMaxMana());
            hero.setMagic(playerMGC);
            hero.setLevel(1);
            hero.setSkillPoints(skillPoints);

            battling = true;
            Intent intent = new Intent(this, Battle.class);
            intent.putExtra("Hero", hero);
            intent.putExtra("battling", battling);
            startActivity(intent);
            finish();

        }
    }//end onClickToBattle

    public void onClickMinusStrength(View view) {
        if(playerSTR != 0 && skillPoints < 20) {
            final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
            animation.setDuration(500); // duration - half of a second
            animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
            animation.setRepeatCount(1); // Repeat animation infinitely
            animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
            final Button minus = (Button) findViewById(R.id.minusStrength);
            minus.startAnimation(animation);
            playerSTR--;
            skillPoints++;
            playerATK = playerATK - 10;
        }
    }// end onClickMinusStrength

    public void onClickMinusMagic(View view) {
        if(playerMGC != 0 && skillPoints < 20) {
            final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
            animation.setDuration(500); // duration - half of a second
            animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
            animation.setRepeatCount(1); // Repeat animation infinitely
            animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
            final Button minus = (Button) findViewById(R.id.minusMagic);
            minus.startAnimation(animation);
            skillPoints++;
            playerMGC--;
            playerMANA = playerMANA - 5;
            playerMaxMANA = playerMANA;
        }
    }// end onClickMinusMagic

    public void onClickMinusStamina(View view) {
        if(playerSTM != 0 && skillPoints < 20) {
            final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
            animation.setDuration(500); // duration - half of a second
            animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
            animation.setRepeatCount(1); // Repeat animation infinitely
            animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
            final Button minus = (Button) findViewById(R.id.minusStamina);
            minus.startAnimation(animation);
            playerSTM--;
            skillPoints++;
            playerHP = playerHP - 10;
            playerMaxHP = playerHP;
        }
    }// end onClickMinusStamina

    public void onClickAddStrength(View view) {
        if(skillPoints != 0) {
            final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
            animation.setDuration(500); // duration - half of a second
            animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
            animation.setRepeatCount(1); // Repeat animation infinitely
            animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
            final Button minus = (Button) findViewById(R.id.addStrength);
            minus.startAnimation(animation);
            playerSTR++;
            skillPoints--;
            playerATK = playerATK + 10;
        }
    }//end onClickAddStrength

    public void onClickAddMagic(View view) {
        if(skillPoints != 0) {
            final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
            animation.setDuration(500); // duration - half of a second
            animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
            animation.setRepeatCount(1); // Repeat animation infinitely
            animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
            final Button minus = (Button) findViewById(R.id.addMagic);
            minus.startAnimation(animation);
            skillPoints--;
            playerMGC++;
            playerMANA = playerMANA +5;
            playerMaxMANA = playerMANA;
        }
    }//end onClickAddMagic

    public void onClickAddStamina(View view) {
        if(skillPoints != 0) {
            final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
            animation.setDuration(500); // duration - half of a second
            animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
            animation.setRepeatCount(1); // Repeat animation infinitely
            animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
            final Button minus = (Button) findViewById(R.id.addStamina);
            minus.startAnimation(animation);
            playerSTM++;
            skillPoints--;
            playerHP = playerHP + 10;
            playerMaxHP = playerHP;
        }
    }//end onClickAddStamina

    private void run(){
        final TextView currentSkillPoints = (TextView)findViewById(R.id.currentSkillPoints);
        final TextView currentStrength = (TextView)findViewById(R.id.currentStrength);
        final TextView currentMagic = (TextView)findViewById(R.id.currentMagic);
        final TextView currentStamina = (TextView)findViewById(R.id.currentStamina);
        final TextView currentHealth = (TextView)findViewById(R.id.currentHealth);
        final TextView currentMana = (TextView)findViewById(R.id.currentMana);
        final TextView currentAttack = (TextView)findViewById(R.id.currentAttack);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                //displays skill points left
                String points = String.format(Locale.getDefault(), "Skill points left: %d ", skillPoints);
                currentSkillPoints.setText(points);

                //display current strength attribute amount
                String strength = String.format(Locale.getDefault(), "Current Strength: %d ", playerSTR);
                currentStrength.setText(strength);

                //display current magic attribute amount
                String magic = String.format(Locale.getDefault(), "Current Magic: %d ", playerMGC);
                currentMagic.setText(magic);

                //display current stamina attribute amount
                String stamina = String.format(Locale.getDefault(), "Current Stamina: %d ", playerSTM);
                currentStamina.setText(stamina);

                //display current max health
                String health = String.format(Locale.getDefault(), "Health: " + playerHP + " / " + playerMaxHP);
                currentHealth.setText(health);

                //display current max mana
                String mana = String.format(Locale.getDefault(), "Mana: " + playerMANA + " / " + playerMaxMANA);
                currentMana.setText(mana);

                //display current attack
                String attack = String.format(Locale.getDefault(), "Attack: %d ", playerATK);
                currentAttack.setText(attack);

                //use postDelayed on handler to have no delay...idk why im using it haha
                handler.postDelayed(this, 0);
            }//end overriden run method
        }); //end runnable

    }//end run method
} //end allocate class

//next thing i need to do is fix margins for the images and textviews at bottom