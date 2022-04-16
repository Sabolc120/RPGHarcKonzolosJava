package com.company;


import java.util.Random;
import java.util.Scanner;

public class FightSecond extends Player implements Fights {
    int howMuchPlusLevel;
    boolean didShopping = false;
    public int gold;
    public int monsterHealth;
    public int monsterDamage;
    public int monsterLevel;
    public int monsterLuck;
    public int monsterMana;
    public boolean secondRanDown = false, thirdRanDown = false;
    //Shop változók
    int oneLevelPlusHealth = this.health / this.level;
    int oneLevelUpPlusAttack = this.damage / this.level;
    int oneLevelUpPlusLuck = this.luck / this.level;
    int oneLevelUpPlusMana = this.mana / this.level;
    int levelUpPlusCompanionDamage = this.companionDamage / this.level;

    public FightSecond(String name, int health, int mana, int damage, int luck, int level, String companion, int companionDamage) {
        super(name, health, mana, damage, luck, level, companion, companionDamage);
    }
    public void getHowManyLevel(int gold){
        didShopping = true;
        this.gold = gold;
        int mennyiSzint = 0;
        Scanner sc = new Scanner(System.in);
        boolean shoppingOver = false;
        int answer = 0;
        while(!shoppingOver){
            try{
                System.out.println("My golds: "+this.gold);
                System.out.println("Buy able items: ");
                System.out.println("1. Buy + level for 5 gold");
                System.out.println("2. Exit");
                answer = sc.nextInt();
                switch(answer){
                    case 1:
                        System.out.println("Input how many levels would you like to buy!");
                        mennyiSzint = sc.nextInt();
                        int cost = mennyiSzint * 5;
                        if(cost > this.gold){
                            System.out.println("Not enough gold!");
                            System.out.println("Needed gold: "+cost);
                            System.out.println("Golds you have: "+this.gold);
                        }
                        else{
                            this.howMuchPlusLevel = mennyiSzint;
                            System.out.println("Successful payment, for this much: "+cost);
                            this.level += mennyiSzint;
                            System.out.println("New level: "+this.level);

                            gold -= cost;
                            shoppingOver = true;
                        }
                        break;
                    case 2:
                        shoppingOver = true;
                        break;
                    default:
                        System.out.println("Wrong number, the options are: ");
                        System.out.println("1. Buy level for 5 gold each");
                        System.out.println("2. Exit");
                        continue;
                }
            }
            catch (Exception exception){
                System.out.println("My golds: "+this.gold);
                System.out.println("Buy able items: ");
                System.out.println("1. Buy + level for 5 gold");
                System.out.println("2. Exit");
                answer = sc.nextInt();
            }
            if(didShopping){
                this.health+= getHowMuchHealthPlus(oneLevelPlusHealth, howMuchPlusLevel);
                this.damage += getHowMuchDamagePlus(oneLevelUpPlusAttack, howMuchPlusLevel);
                this.mana += getHowMuchManna(oneLevelUpPlusMana, howMuchPlusLevel);
                this.luck += getHowMuchLuck(oneLevelUpPlusLuck, howMuchPlusLevel);
                this.companionDamage += getHowMuchCompanionDamagePlus(levelUpPlusCompanionDamage, howMuchPlusLevel);
                System.out.println("Your new stats are because of shopping: ");
                System.out.println("Health: "+this.health);
                System.out.println("Damage: "+this.damage);
                System.out.println("Mana: "+this.mana);
                System.out.println("Luck: "+this.luck);
            }
        }
    }
    public void Battle(String[] quotes, String[] skills) {
        switch (companion) {
            case "Fairy" -> this.mana += this.mana * 0.50;
            case "Golem" -> this.health += this.health * 0.20;
        }
        boolean isAttacked = false;
        int randomCrit;
        int critOrNot;
        Scanner beolvas = new Scanner(System.in);
        Random veletlen = new Random();
        this.monsterLevel = 44;
        this.monsterHealth = 160 * this.monsterLevel;
        this.monsterDamage = 10 * this.monsterLevel;
        this.monsterMana = 5 * this.monsterLevel;
        this.monsterLuck = 5 * this.monsterLevel;
        byte counter = 0;
        while (this.monsterHealth > 0 || this.health > 0) {
            if (this.health <= 0) {
                System.out.println("You have been died..");
                System.out.println("Mission failed!");
                System.out.println("Reason: Your level was only: " + this.level);
                System.exit(0);
                break;
            }
            boolean badAnswer = true;
            String skillStringChoosed = "";
            int skillChoosed = 0;
            while(badAnswer){
                System.out.println("Which skill will you choose?");
                System.out.println(skills[0]);
                System.out.println(skills[1]);
                System.out.println(skills[2]);
                try{
                    skillChoosed = beolvas.nextInt();
                    switch (skillChoosed){
                        case 1:
                        case 2:
                        case 3:
                            badAnswer = false;
                            break;
                    }
                }
                catch(Exception exception){
                    System.out.println("You gave me a String instead of a number.");
                    skillStringChoosed = beolvas.next();

                }
            }
            if (skillChoosed == 1 || skillStringChoosed.equals("1")) {
                boolean alreadyAttacked = false;
                if(counter < 4 && companion.equals("Dragon")){
                    counter+=1;
                    alreadyAttacked = true;
                    System.out.println("Critical damage!");
                    System.out.println(quotes[0]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.30));
                    System.out.println("Critical damage: " + Math.round(this.damage * 1.30));
                    //Akkor ha a dragon buff alat lesz legyőzve az ellenség
                    if (this.monsterHealth <= 0){
                        System.out.println("\tGiant worm's new health: " + this.monsterHealth);
                        System.out.println("You have defeated the giant worm!");
                        gold+=180;
                        break;
                    }
                }
                critOrNot = this.luck / 5; //Luck alapján fogom kiszámítani
                randomCrit = veletlen.nextInt(1, 101);
                System.out.println(this.companion+" has attacked the giant worm, dealt the next damage: "+this.companionDamage);
                this.monsterHealth -= this.companionDamage;
                if (this.monsterHealth <= 0){
                    System.out.println("\tGiant worm's new health: " + this.monsterHealth);
                    System.out.println("You have defeated the giant worm!");
                    gold+=180;
                    break;
                }
                if (critOrNot >= randomCrit &&!alreadyAttacked) {
                    System.out.println(quotes[0]);
                    //Akkor crit damage!
                    System.out.println("Critical damage!");
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.30));
                    System.out.println("Critical damage: " + Math.round(this.damage * 1.30));

                    //Akkor ha rögtön az első támadás után meghal.
                    if (this.monsterHealth <= 0){
                        System.out.println("You have defeated the giant worm!");
                        gold+=180;
                        break;
                    }
                } else if(critOrNot <= randomCrit && !alreadyAttacked){
                    System.out.println(quotes[0]);
                    this.monsterHealth = this.monsterHealth - this.damage;
                    System.out.println("Damage: " + this.damage);
                    if (this.monsterHealth <= 0){

                        System.out.println("You have defeated the giant worm");
                        gold+=180;
                        break;
                    }
                }
                System.out.println("\tGiant worm's new health: " + this.monsterHealth);
            }
            if (skillChoosed == 2 && this.mana >= 20 ||skillStringChoosed.equals("2")) {
                secondRanDown = true;
                boolean alreadyAttacked = false;
                if(counter < 4 && companion.equals("Dragon")){
                    counter+=1;
                    alreadyAttacked = true;
                    System.out.println("Critical damage!");
                    System.out.println(quotes[1]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.70));
                    System.out.println("Critical damage: " + Math.round(this.damage * 1.70));
                    //Akkor ha a dragon buff közben hal meg az ellenség. (2)
                    if(this.monsterHealth < 0){
                        System.out.println("\tGiant worm's new health: " + this.monsterHealth);
                        System.out.println("You have defeated the giant worm!");
                        gold+=180;
                        break;
                    }
                }
                this.mana -= 20;
                critOrNot = this.luck / 5; //Luck alapján fogom kiszámítani (1)
                randomCrit = veletlen.nextInt(1, 101);
                System.out.println(this.companion+" has attacked the giant worm, dealt the next damage: "+this.companionDamage);
                this.monsterHealth -= this.companionDamage;
                if (this.monsterHealth <= 0){
                    System.out.println("You have defeated the giant worm!");
                    gold+=180;
                    break;
                }
                if (critOrNot >= randomCrit && !alreadyAttacked) {
                    //Akkor crit damage! (1)
                    System.out.println("Critical damage!");
                    System.out.println(quotes[1]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.70));
                    System.out.println("Critical damage: " + this.damage * 1.70);
                    //Akkor ha rögtön az első támadás után meghal. (1)
                    if (this.monsterHealth <= 0){
                        System.out.println("You have defeated the giant worm!");
                        gold+=180;
                        break;
                    }
                } else if(critOrNot <= randomCrit &&! alreadyAttacked){
                    System.out.println(quotes[1]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.30));
                    System.out.println("Damage: " + Math.round(this.damage * 1.30));
                    //Akkor ha rögtön az első támadás után meghal.
                    if (this.monsterHealth <= 0){
                        System.out.println("You have defeated the giant worm!");
                        gold+=180;
                        break;
                    }
                }
                System.out.println("\tGiant worm's new health: " + this.monsterHealth);
            }
            if (skillChoosed == 3 && this.mana >= 30 || skillStringChoosed.equals("3")) {
                thirdRanDown = true;
                boolean alreadyAttacked = false;
                if(counter < 4 && companion.equals("Dragon")){
                    counter+=1;
                    alreadyAttacked = true;
                    System.out.println("Critical damage!");
                    System.out.println(quotes[2]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 2));
                    System.out.println("Critical damage: " + Math.round(this.damage * 2));
                    //Akkor ha a dragon buff közben hal meg az ellenség. (2)
                    if(this.monsterHealth < 0){
                        System.out.println("\tGiant worm's new health: " + this.monsterHealth);
                        System.out.println("You have defeated the giant worm!");
                        gold+=180;
                        break;
                    }
                }
                System.out.println(this.companion+" has attacked the giant worm, dealt the next damage: "+this.companionDamage);
                this.monsterHealth -= this.companionDamage;
                this.mana -= 30;
                critOrNot = this.luck / 5; //Luck alapján fogom kiszámítani (1)
                randomCrit = veletlen.nextInt(1, 101);
                if (critOrNot >= randomCrit && !alreadyAttacked) {
                    System.out.println(quotes[2]);
                    //Akkor crit damage! (1)
                    System.out.println("Critical damage!");
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 2));
                    System.out.println("Critical damage: " + Math.round(this.damage * 2));
                    //Akkor ha rögtön az első támadás után meghal. (1)
                    if (this.monsterHealth <= 0){
                        System.out.println("You have defeated the giant worm!");
                        gold+=180;
                        break;
                    }
                } else if(critOrNot <= randomCrit && !alreadyAttacked){
                    System.out.println(quotes[2]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.40));
                    System.out.println("Damage: " + Math.round(this.damage * 1.40));
                    //Akkor ha rögtön az első támadás után meghal.
                    if (this.monsterHealth <= 0){
                        System.out.println("You have defeated the giant worm!");
                        gold+=180;
                        break;
                    }
                }
                System.out.println("\tGiant worm's new health: " + this.monsterHealth);
            }
            else if(skillChoosed == 2 && mana < 20 && !secondRanDown || skillChoosed == 3 && mana <30 && !thirdRanDown){
                System.out.println("Not enough Mana, I use the default skill!");
                boolean alreadyAttacked = false;
                if(counter < 4 && companion.equals("Dragon")){
                    counter+=1;
                    alreadyAttacked = true;
                    System.out.println("Critical damage!");
                    System.out.println(quotes[0]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.50));
                    System.out.println("Critical damage: " + Math.round(this.damage * 1.50));
                    //Akkor ha a dragon buff közben hal meg az ellenség. (2)
                    if(this.monsterHealth < 0){
                        System.out.println("\tGiant worm's new health: " + this.monsterHealth);
                        System.out.println("You have defeated the giant worm!");
                        gold+=180;
                        break;
                    }
                }
                System.out.println(this.companion+" has attacked the giant worm, dealt the next damage: "+this.companionDamage);
                this.monsterHealth -= this.companionDamage;
                critOrNot = this.luck / 5; //Luck alapján fogom kiszámítani
                randomCrit = veletlen.nextInt(1, 101);
                if (critOrNot >= randomCrit && !alreadyAttacked) {
                    System.out.println(quotes[0]);
                    //Akkor crit damage!
                    System.out.println("Critical damage!");
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.30));
                    System.out.println("Critical damage: " + Math.round(this.damage * 1.30));
                    //Akkor ha rögtön az első támadás után meghal.
                    if (this.monsterHealth <= 0){
                        System.out.println("You have defeated the giant worm!");
                        gold+=180;
                        break;
                    }
                } else if(critOrNot <= randomCrit && !alreadyAttacked){
                    System.out.println(quotes[0]);
                    this.monsterHealth = this.monsterHealth - Math.round(this.damage);
                    System.out.println("Damage: " + Math.round(this.damage));
                    //Akkor ha rögtön az első támadás után meghal.
                    if (this.monsterHealth <= 0){
                        System.out.println("You have defeated the giant worm!");
                        gold+=180;
                        break;
                    }
                }
                System.out.println("\tGiant worm's new health: " + this.monsterHealth);
            }
            if(skillChoosed == 2 || skillStringChoosed.equals("2")) secondRanDown = false;
            else if(skillChoosed == 3 || skillStringChoosed.equals("3")) thirdRanDown = false;
            //Itt jön a giant wormnek a támadása!
            boolean dodged = false;
            int dodgeOrNot = this.luck / 5 + this.level /8;
            int dodgededOrNot = veletlen.nextInt(1, 101);
            if(dodgeOrNot >= dodgededOrNot){
                System.out.println(quotes[3]);
                dodged = true;
            }
            int monsterAttack = veletlen.nextInt(1, 4);
            if (monsterAttack == 1 && !dodged) {
                System.out.println("\tThe giant worm grabs your leg!");
                System.out.println("\tOccurred damage: " + this.monsterDamage);
                this.health = this.health - this.monsterDamage;
                System.out.println(this.name + "' s new health: " + this.health);
            } else if (monsterAttack == 2 && monsterMana >= 10 && !dodged){
                System.out.println("\tThe giant worm tries to eat you!");
                this.monsterMana = this.monsterMana - 10;
                System.out.println("\tOccurred damage: " + this.monsterDamage * 1.20);
                this.health = (int) (this.health - this.monsterDamage * 1.20);
                System.out.println(this.name + "' s new health: " + this.health);
            } else if (monsterAttack == 3 && monsterMana >= 20 && !dodged) {
                System.out.println("\tThe giant worm bites your leg, its bleeding!");
                critOrNot = this.monsterLuck / 5;
                randomCrit = veletlen.nextInt(1, 101);
                if (critOrNot >= randomCrit) {
                    isAttacked = true;
                    System.out.println("\tCritical hit!");
                    this.monsterMana = this.monsterMana - 20;
                    System.out.println("\tOccurred damage: " + this.monsterDamage * 1.60);
                    this.health -= this.monsterDamage * 1.60;
                    System.out.println(this.name + "' s new health: " + this.health);
                }
                else{
                    this.monsterMana = this.monsterMana - 20;
                    System.out.println("\tOccurred damage: " + this.monsterDamage * 1.50);
                    this.health = (int) (this.health - this.monsterDamage * 1.50);
                    System.out.println(this.name + "' s new health: " + this.health);
                }
            } else if(!dodged) {
                System.out.println("\tThe giant worm bites you!");
                System.out.println("Occurred damage: " + this.monsterDamage);
                this.health = this.health - this.monsterDamage;
                System.out.println(this.name + "' s new health: " + this.health);
            }
        }
    }
    public void fightQuotes(String race) {
        System.out.println("You are walking around in peace, when the clock hits afternoon..");
        System.out.println("You feel an earthquake, then the worm suddenly appears!");
        switch (race) {
            case "Assassin" -> {
                String[] attackQuotes = new String[]{"You slashes into the giant worm!", "You thrown your blades towards giant worm, nice hit!", "You knife the worm's mouth!", "You successfully dodged the giant worm's attack!, no damage taken."};
                String[] skills = new String[]{"1. Dual slash", "2. Blade throw ", "3. Cyclone Spin"};
                Battle(attackQuotes, skills);
            }
            case "Orc" -> {
                String[] attackQuotesOrc = new String[]{"You slap the giant worm with your hammer!", "You grab your hammer with your both hands, and slam it on the giant worm!",
                        "You are attacking from a high point, and using up your weight and the hammer's you strike down onto the worm!","You successfully defended the giant worm's attack!, no damage taken."};
                String[] skillsOrc = new String[]{"1. Basic slam", "2. Powerful slam", "3. Finisher Slam"};
                Battle(attackQuotesOrc, skillsOrc);
            }
            case "Dark Warrior" -> {
                String[] attackQuotesDark = new String[]{"You slash the giant worm with your claymore!", "You throw your claymore into the giant worm!",
                        "You make a big and wide slash into the worm!", "You successfully defended the giant worm's attack!, no damage taken."};
                String[] skillsDark = new String[]{"1. Basic claymore slash", "2. Claymore throw", "3. Claymore stagger then stab"};
                Battle(attackQuotesDark, skillsDark);
            }
            case "Elf Archer" -> {
                String[] attackQuotesElf = new String[]{"You shot a basic arrow into the giant worm", "You shot double arrows into the giant worm!",
                        "You shot a weak point on the giant worm!","You successfully dodged the giant worm's attack!, no damage taken."};
                String[] skillsElf = new String[]{"1. Basic shot", "2. Double arrow shot", "3. Weak-point shot"};
                Battle(attackQuotesElf, skillsElf);
            }
        }
    }
    public int getHowMuchCompanionDamagePlus(int levelUpPlusCompanionDamage, int level) {
        this.levelUpPlusCompanionDamage = levelUpPlusCompanionDamage;
        return levelUpPlusCompanionDamage * level;
    }
    public int getHowMuchHealthPlus(int oneLevelPlusHealth, int level) {
        this.oneLevelPlusHealth = oneLevelPlusHealth;
        return oneLevelPlusHealth * level;
    }
    public int getHowMuchDamagePlus(int oneLevelUpPlusAttack, int level) {
        this.oneLevelUpPlusAttack = oneLevelUpPlusAttack;
        return oneLevelUpPlusAttack * level;
    }
    public int getHowMuchManna(int oneLevelUpPlusMana, int level) {
        this.oneLevelUpPlusMana = oneLevelUpPlusMana;
        return oneLevelUpPlusMana * level;
    }
    public int getHowMuchLuck(int oneLevelUpPlusLuck, int level) {
        this.oneLevelUpPlusLuck = oneLevelUpPlusLuck;
        return oneLevelUpPlusLuck * level;
    }
    public int getGold() {
        return gold;
    }
}