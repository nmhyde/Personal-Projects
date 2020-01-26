package com.example.broom.homeworktwo.CharacterBuilding;

import android.os.Parcel;

public class Hero extends Character {
    int mana, maxMana, magic, level, skillPoints;
    public Hero(String name, int health, int maxHealth, int attack, int stamina, int strength, int mana, int maxMana, int magic, int level, int skillPoints){
        //calls parent class's constructor
        super(name, health, maxHealth, attack, stamina, strength);
        //have to initialize the parameters that are not in the super (character) class
        this.mana = mana;
        this.mana = maxMana;
        this.magic = magic;
        this.level = level;
        this.skillPoints = skillPoints;
    }
    public int getMana(){return mana;}
    public int getMaxMana(){return maxMana;}
    public int getMagic(){return magic;}
    public int getLevel(){return level;}
    public int getSkillPoints(){return skillPoints;}

    public void setMana(int mana){this.mana = mana;}
    public void setMaxMana(int mana){this.maxMana = maxMana;}
    public void setMagic(int magic){this.magic = magic;}
    public void setLevel(int level){this.level = level;}
    public void setSkillPoints(int skillPoints){this.skillPoints = skillPoints;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.mana);
        dest.writeInt(this.magic);
        dest.writeInt(this.level);
        dest.writeInt(this.skillPoints);
    }

    protected Hero(Parcel in) {
        super(in);
        this.mana = in.readInt();
        this.magic = in.readInt();
        this.level = in.readInt();
        this.skillPoints = in.readInt();
    }

    public static final Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel source) {
            return new Hero(source);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };
}
