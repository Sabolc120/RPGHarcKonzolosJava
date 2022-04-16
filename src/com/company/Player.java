package com.company;

import java.util.Random;
import java.util.Scanner;

public class Player {
    public String name;
    public String race;
    public int health;
    public int mana;
    public int damage;
    public int luck;
    public int level;
    public String companion;
    public int companionDamage;

    public Player(String name, int health, int mana, int damage, int luck, int level, String companion, int companionDamage) {
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.damage = damage;
        this.luck = luck;
        this.level = level;
        this.companion = companion;
        this.companionDamage = companionDamage;
    }

    public void whichSword(){
        System.out.println("Your sword will be decided automatic, with RNG.");
        Random randomNum = new Random();
        int yourBlade = randomNum.nextInt(20, 30); //Kiosztok egy bónusz sebzést egy kis randommal
        System.out.println("Your sword has the next damage: "+yourBlade);
        this.damage = yourBlade; //Hozzáadom azt a sebzést
    }
    public void levelBonus(int health, int damage, int luck, int mana){
        Random randonNum = new Random();
        this.level = randonNum.nextInt(20,25); //Itt fogom kiosztani a szintet.
        this.health += health + (health / 1.5) * level; //1. Hozzáadom az alap értéket az objektumból, elosztom kettővel, majd megszorzom a szinttel.
        System.out.println("Your level is this many: "+level);
        System.out.println("> SYSTEM Calculating health....will be: "+this.health);
        this.damage += damage +(damage / 2.8) * level; //2. Ugyanezt a logikát alkalmazom a sebzésen
        System.out.println("> SYSTEM Calculating damage.....will be: "+this.damage);
        this.mana += mana+(mana / 7) * level; //Túl sok mana csalásnak számítana :)
        this.mana = Math.round(this.mana);
        System.out.println("> SYSTEM Calculating mana........will be: "+this.mana);
        this.luck += luck +(luck / 8) * level; //Luck: Crit Rate és Dodge esély is egyben
        System.out.println("> SYSTEM Calculating luck......... will be: "+this.luck);
        System.out.println("Level bonuses has been added!");
    }
    public void whichRace(){ //Metódus aminél a faj választás fog lezajlódni
        boolean choosed = false;
        while(!choosed){
            System.out.println("Please choose a race! input the corresponding number.");
            System.out.println("1. Orc");
            System.out.println("2. Assassin");
            System.out.println("3. Dark Warrior");
            System.out.println("4. Elf Archer");
            String[] racesArray = new String[]{"Orc","Assassin","Dark Warrior","Elf Archer"}; //Tömbből választom ki a fajt
            Scanner beolvas = new Scanner(System.in);
            try{
                int pickedRace = beolvas.nextInt();
                switch (pickedRace) {
                    case 1 -> {
                        this.race = racesArray[0];
                        choosed = true;
                    }
                    case 2 -> {
                        this.race = racesArray[1];
                        choosed = true;
                    }
                    case 3 -> {
                        this.race = racesArray[2];
                        choosed = true;
                    }
                    case 4 -> {
                        choosed = true;
                        this.race = racesArray[3];
                    }
                    default -> System.out.println("You only can choose from 4 options, the choosing cant be less than 1 or higher than 4.");
                }
            }
            catch(Exception exception){
                System.out.println("You gave me a String instead of a number, enter a number.");
            }
        }
        boolean choosedCompanion = false;
        while(!choosedCompanion){
            System.out.println("Please choose a companion!."); //Támogató kiválasztása
            System.out.println("1. Golem");
            System.out.println("2. Dragon");
            System.out.println("3. Fairy");
            System.out.println("4. Companion explanation");
            String[] companionArray = new String[]{"Golem","Dragon","Fairy"};
            Scanner beolvas = new Scanner(System.in);
            try{
                int pickedRace = beolvas.nextInt();
                switch (pickedRace) {
                    case 1 -> {
                        this.companion = companionArray[0];
                        choosedCompanion = true;
                    }
                    case 2 -> {
                        this.companion = companionArray[1];
                        choosedCompanion = true;
                    }
                    case 3 -> {
                        this.companion = companionArray[2];
                        choosedCompanion = true;
                    }
                    case 4 ->{
                        System.out.println("Companions will help you in your fight, and will give you special buffs.");
                        System.out.println("Golem gives you the 25% of your max health as a bonus at every fight");
                        System.out.println("with Dragon your first 3 attack will be guaranteed Critical attack");
                        System.out.println("Fairy gives you 50% more manna which will be added to your maximum manna.");
                    }
                    default -> System.out.println("You only can choose from 3 options, the choosing cant be less than 1 or higher than 3.");
                }
            }
            catch(Exception exception){
                System.out.println("You gave me a String instead of a number, enter a number.");
            }
        }
        System.out.println("Your race has been selected it is: "+this.race);
        System.out.println("Your companion has been selected it is: "+this.companion);
        switch (this.race) { //Miután meglett a faj, hozzá lesz adva aszerinti bónusz statisztika
            case "Orc" -> {
                this.health += this.health * 0.30;
                this.damage += this.damage * 0.20;
            }
            case "Assassin" -> {
                this.damage += this.damage * 0.30;
                this.luck += this.luck * 0.20;
            }
            case "Dark Warrior" -> {
                this.damage += this.damage * 0.10;
                this.health += this.health * 0.10;
                this.luck += this.luck * 0.10;
                this.mana += this.mana * 0.10;
            }
            case "Elf Archer" -> {
                this.damage += this.damage * 0.15;
                this.luck += this.luck * 0.35;
            }
        }
        this.companionDamage += this.damage / 4.5; //Nem kell hogy nagy szerepe legyen a harcban, de azért legyen.
        System.out.println("> SYSTEM Calculating new health.....will be: "+this.health);
        System.out.println("> SYSTEM Calculating new damage.....will be: "+this.damage);
        System.out.println("> SYSTEM Calculating new mana.....will be: "+this.mana);
        System.out.println("> SYSTEM Calculating new luck.....will be: "+this.luck);
        System.out.println("> SYSTEM Calculating "+this.companion+" 's damage....."+this.companionDamage);
        System.out.print("Please enter username!: ");
        Scanner beolvas = new Scanner(System.in);
        String playerName = beolvas.next();
        System.out.println("Welcome in Material for presume RPG! "+playerName);
        this.name = playerName;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public int getDamage() {
        return damage;
    }

    public int getLuck() {
        return luck;
    }

    public int getLevel() {
        return level;
    }

    public String getRace() {
        return race;
    }

    public String getName() {
        return name;
    }

    public String getCompanion() {
        return companion;
    }

    public int getCompanionDamage() {
        return companionDamage;
    }
}
