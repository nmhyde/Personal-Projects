package com.example.broom.homeworktwo.CharacterBuilding;

import android.os.Parcel;
import android.os.Parcelable;

public class Monster extends Character implements Parcelable{
    //monster class
    int gold, experience;
    public Monster(String name, int health, int maxHealth, int attack, int stamina, int strength, int gold, int experience){
        //calls parent's constructor
        super(name, health, maxHealth, attack, stamina, strength);
        //initializes what parent(character) doesnt have
        this.gold = gold;
        this.experience = experience;
    }
    public int getGold(){return gold;}
    public int getExperience(){return experience;}

    public void setGold(int gold){this.gold = gold;}
    public void setExperience(int health){this.experience = experience;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.gold);
        dest.writeInt(this.experience);
    }

    protected Monster(Parcel in) {
        super(in);
        this.gold = in.readInt();
        this.experience = in.readInt();
    }

    public static final Creator<Monster> CREATOR = new Creator<Monster>() {
        @Override
        public Monster createFromParcel(Parcel source) {
            return new Monster(source);
        }

        @Override
        public Monster[] newArray(int size) {
            return new Monster[size];
        }
    };
}
