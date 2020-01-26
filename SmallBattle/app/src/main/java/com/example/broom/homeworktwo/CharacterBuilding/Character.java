package com.example.broom.homeworktwo.CharacterBuilding;

import android.os.Parcel;
import android.os.Parcelable;

public class Character implements Parcelable{
    private String name;
    private int health, maxHealth, attack, stamina, strength;

    public Character(String name, int health, int maxHealth, int attack, int stamina, int strength){
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.stamina = stamina;
        this.strength = strength;
    }
    public String getName() {return name;}
    public int getHealth(){return health;}
    public int getMaxHealth(){return maxHealth;}
    public int getAttack(){return attack;}
    public int getStamina(){return stamina;}
    public int getStrength(){return strength;}

    public void setName(String name){this.name = name;}
    public void setHealth(int health){this.health = health;}
    public void setMaxHealth(int maxHealth){this.maxHealth = maxHealth;}
    public void setAttack(int attack){this.attack = attack;}
    public void setStamina(int stamina){this.stamina = stamina;}
    public void setStrength(int strength){this.strength = strength;}

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

    protected Character(Parcel in) {
        name = in.readString();
        health = in.readInt();
        maxHealth = in.readInt();
        attack = in.readInt();
        stamina = in.readInt();
        strength = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(health);
        dest.writeInt(maxHealth);
        dest.writeInt(attack);
        dest.writeInt(stamina);
        dest.writeInt(strength);
    }
}