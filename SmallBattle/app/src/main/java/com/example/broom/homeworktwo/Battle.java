package com.example.broom.homeworktwo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.CountDownTimer;
import com.example.broom.homeworktwo.CharacterBuilding.Monster;
import com.example.broom.homeworktwo.CharacterBuilding.Hero;
import com.example.broom.homeworktwo.CharacterBuilding.Character;

import java.util.Locale;

public class Battle extends Activity {
    //textviews for players attributes


    //textviews for enemy attributes



    static int playerLVL, skillPoints, playerSTR, playerMGC, playerSTM, playerMANA, playerHP, playerATK;
    static int playerMaxHP, playerMaxMANA;
    static int meleeCooldown, spellCoolDown;
    String name;
    static boolean battling;
    static boolean enemyAlive = true, playerAlive = true;
    static boolean meleeAttackCoolDown = false, spellAttackCoolDown = false, enemyAttackCoolDown = false;
    TextView melee, spellCast;
    Hero hero;
    Monster menacingFlower = new Monster("Menacing Flower", 666, 666, 15, 0, 15, 2, 10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        Intent intent = getIntent();
        hero = (Hero)intent.getParcelableExtra("Hero");

        name = intent.getStringExtra("HeroName");
        playerHP = intent.getIntExtra("playerHP", hero.getHealth());
        playerMaxHP = intent.getIntExtra("playerMaxHP", hero.getMaxHealth());
        playerATK = intent.getIntExtra("playerATK", hero.getAttack());
        playerSTM = intent.getIntExtra("playerSTM", hero.getStamina());
        playerSTR = intent.getIntExtra("playerSTR", hero.getStrength());
        playerMANA = intent.getIntExtra("playerMANA", hero.getMana());
        playerMaxMANA = intent.getIntExtra("playerMaxMANA", hero.getMaxMana());
        playerMGC = intent.getIntExtra("playerMGC", hero.getMagic());
        skillPoints = intent.getIntExtra("skillPoints", hero.getSkillPoints());
        battling = intent.getBooleanExtra("battling", true);

        if(savedInstanceState != null){
            name = savedInstanceState.getString("HeroName");
            playerHP = savedInstanceState.getInt("playerHP");
            playerMaxHP = savedInstanceState.getInt("playerMaxHP");
            playerATK = savedInstanceState.getInt("playerATK");
            playerSTM = savedInstanceState.getInt("playerSTM");
            playerSTR = savedInstanceState.getInt("playerSTR");
            playerMANA = savedInstanceState.getInt("playerMANA");
            playerMaxMANA = savedInstanceState.getInt("playerMaxMANA");
            playerMGC = savedInstanceState.getInt("playerMGC");
            playerLVL = savedInstanceState.getInt("playerLVL");
            skillPoints = savedInstanceState.getInt("skillPoints");
            battling = savedInstanceState.getBoolean("battling");
        }//end savedstate checker thing

        final TextView currentSkillPoints = (TextView)findViewById(R.id.currentSkillPoints);
        final TextView currentStrength = (TextView)findViewById(R.id.currentStrength);
        final TextView currentMagic = (TextView)findViewById(R.id.currentMagic);
        final TextView currentStamina = (TextView)findViewById(R.id.currentStamina);
        final TextView currentHealth = (TextView)findViewById(R.id.currentHealth);
        final TextView currentMana = (TextView)findViewById(R.id.currentMana);
        final TextView currentAttack = (TextView)findViewById(R.id.currentAttack);

        //display current health, mana, and attack
        String health = "\n" + hero.getHealth() + "/" + hero.getMaxHealth();
        currentHealth.append(health);

        //display current mana
        String mana = "\n" + hero.getMana() + "/" + hero.getMaxMana();
        currentMana.append(mana);

        //display current attack
        String attack = "\n" + hero.getAttack();
        currentAttack.append(attack);

        //display current skillPoints
        String points = "" + hero.getSkillPoints();
        currentSkillPoints.append(points);

        //display current strength
        String strengthPoints = "" + hero.getStrength();
        currentStrength.append(strengthPoints);

        //display current magic
        String magicPoints = "" + hero.getMagic();
        currentMagic.append(magicPoints);

        //display current stamina
        String staminaPoints = "" + hero.getStamina();
        currentStamina.append(staminaPoints);

        //display enemy name
        final TextView enemyName = (TextView)findViewById(R.id.enemyName);
        String enemysName = "" + menacingFlower.getName();
        enemyName.append(enemysName);

        //this lets me keep enemy's health updated per attack!
        final TextView enemyHealth = (TextView)findViewById(R.id.enemyHealth);
        String enemyHP = "Health: " + menacingFlower.getHealth() + "/" + menacingFlower.getMaxHealth();
        enemyHealth.setText(enemyHP);

        run();
    }//end onCreate

    public void onClickAttackEnemy(View view){
        if(meleeAttackCoolDown == false && battling == true) {
            meleeAttackCoolDown = true;
            menacingFlower.setHealth(menacingFlower.getHealth() - hero.getAttack());

            //this lets me keep enemy's health updated per attack!
            final TextView enemyHealth = (TextView)findViewById(R.id.enemyHealth);
            String enemyHP = "Health: " + menacingFlower.getHealth() + "/" + menacingFlower.getMaxHealth();
            enemyHealth.setText(enemyHP);
            if(menacingFlower.getHealth() < 1){
                menacingFlower.setHealth(0);
                enemyHP = "Health: " + menacingFlower.getHealth() + "/" + menacingFlower.getMaxHealth();
                enemyHealth.setText(enemyHP);
                battling = false;
                enemyAlive = false;
            }

            melee = (TextView) findViewById(R.id.attack);
            CountDownTimer CoolDownForAttack = new CountDownTimer(4000, 1000) {
                public void onTick(long millisUntilFinished) {

                    melee.setText("Cooldown: " + millisUntilFinished / 1000);
                }//end on tick

                public void onFinish() {
                    melee.setText("attack");
                    meleeAttackCoolDown = false;
                }//end onFinish
            }.start(); //end CountDownTimer
        }
    }//end onClickAttackEnemy

    public void onClickCastSpell(View view){
        if((spellAttackCoolDown == false) && (battling == true)) {
            spellAttackCoolDown = true; //set cooldowntimer boolean to true
            hero.setMana(hero.getMana() - 10); //subtract mana from hero's mana

            //this lets me update player's mana
            final TextView currentMana = (TextView)findViewById(R.id.currentMana);
            String mana = "Current Mana: " + "\n" + hero.getMana() + "/" + hero.getMaxMana();
            currentMana.setText(mana);

            //stuff that gives the monster a green tint when poisoned
            PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(getResources().getColor(R.color.lightGreen),PorterDuff.Mode.MULTIPLY);
            final ImageView imgView = (ImageView)findViewById(R.id.menacingFlower);
            imgView.getDrawable().setColorFilter(porterDuffColorFilter);
            imgView.setBackgroundColor(Color.TRANSPARENT);

            final TextView enemyHealth = (TextView)findViewById(R.id.enemyHealth); //create textview to update health

            spellCast = (TextView) findViewById(R.id.castSpell);
            CountDownTimer CooldownForSpell = new CountDownTimer(8000, 1000) {
                public void onTick(long millisUntilFinished) {

                    spellCast.setText("Cooldown: " + millisUntilFinished / 1000);

                    //this lets me keep enemy's health updated per tick
                    menacingFlower.setHealth(menacingFlower.getHealth() - (hero.getMagic() * 3));
                    String enemyHP = "Health: " + menacingFlower.getHealth() + "/" + menacingFlower.getMaxHealth();
                    enemyHealth.setText(enemyHP);
                    if(menacingFlower.getHealth() < 1){
                        menacingFlower.setHealth(0);
                        enemyHP = "Health: " + menacingFlower.getHealth() + "/" + menacingFlower.getMaxHealth();
                        enemyHealth.setText(enemyHP);
                        battling = false;
                        enemyAlive = false;
                    }

                }//end on tick

                public void onFinish() {
                    spellCast.setText("spell");
                    spellAttackCoolDown = false;

                    //removes tint because poison ends
                    imgView.getDrawable().setColorFilter(null);
                }//end onFinish
            }.start(); //end CountDownTimer
        }
    }//end onClickCastSpell

    public void onClickRunAway(View view){
        final TextView noRunning = (TextView)findViewById(R.id.noRunning);
        String cantRun = "There is no running!";
        noRunning.setText(cantRun);


        CountDownTimer CooldownForSpell = new CountDownTimer(3000, 3000) {
            public void onTick(long millisUntilFinished) {

            }//end on tick

            public void onFinish() {
                noRunning.setText("");
            }//end onFinish
        }.start(); //end CountDownTimer

    }//end onClickRunAway

    private void run(){
            //textview so i can update enemy's health
            final Handler handler = new Handler();
            //textview to update hero health
            final TextView currentHealth = (TextView) findViewById(R.id.currentHealth);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(battling == false){
                        if(playerAlive == false) {
                            AfterBattle();
                        } else if (enemyAlive == false){
                            AfterBattle();
                        }
                    }
                    if(battling == true) {
                        if (hero.getHealth() < 1 || menacingFlower.getHealth() < 1) {
                            battling = false;
                            if(hero.getHealth() < 1){
                                playerAlive = false;
                            } else if(menacingFlower.getHealth() < 1){
                                enemyAlive = false;
                            }
                        }//end if to check health
                        hero.setHealth(hero.getHealth() - menacingFlower.getAttack());
                        String health = "Current Health: " + "\n" + hero.getHealth() + "/" + hero.getMaxHealth();
                        currentHealth.setText(health);


                    }//end if check for battling
                    handler.postDelayed(this, 5000);
                }//end overriden run method
            }); //end runnable

    }//end run method

    public void AfterBattle(){
        Intent intent = new Intent(this, VictoryOrDeath.class);
        intent.putExtra("Hero", hero);
        intent.putExtra("battling", battling);
        intent.putExtra("enemyAlive",enemyAlive);
        intent.putExtra("playerAlive", playerAlive);
        startActivity(intent);
    }

    public void getResults()
    {
        Intent intent = new Intent(this,Results.class);
        String results;
        if(hero.getHealth() <= 0)
        {
            results = "YOU LOSE!";

            //Call intent to open new activity for when hero loses.
        }
        else if(menacingFlower.getHealth() <= 0)
        {
            results = "YOU WIN!";
            //call intent to open new activity for when hero wins.
        }
        else
        {
            results = "YOU FLED LIKE A COWARD!";
            //call intent to open new activity for when the hero flees.
        }
        intent.putExtra("results",results);
        startActivity(intent);
    }

}//end battle class

