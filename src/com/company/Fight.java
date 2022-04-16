package com.company;


import java.util.Random;
import java.util.Scanner;

public class Fight extends Player{
    public int gold;
    public int monsterHealth;
    public int monsterDamage;
    public int monsterLevel;
    public int monsterLuck;
    public int monsterMana;
    public boolean secondRanDown = false, thirdRanDown = false, monsterAttacked = false;
    public Fight(String name, int health, int mana, int damage, int luck, int level, String companion, int companionDamage) {
        super(name, health, mana, damage, luck, level, companion, companionDamage);
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
        this.monsterLevel = 20;
        this.monsterHealth = 160 * this.monsterLevel;
        this.monsterDamage = 10 * this.monsterLevel;
        this.monsterMana = 5 * this.monsterLevel;
        this.monsterLuck = 5 * this.monsterLevel;
        byte counter = 1;
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
                    System.out.println("Dragon has run, counter: "+counter);
                    alreadyAttacked = true;
                    System.out.println("Critical damage!");
                    System.out.println(quotes[0]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.30));
                    System.out.println("Critical damage: " + Math.round(this.damage * 1.30));
                    //Akkor ha a dragon buff alat lesz legyőzve az ellenség
                    if (this.monsterHealth <= 0){
                        System.out.println("\tWerewolves new health: " + this.monsterHealth);
                        System.out.println("You have defeated the werewolves!");
                        gold+=120;
                        break;
                    }
                }
                critOrNot = this.luck / 5; //Luck alapján fogom kiszámítani
                randomCrit = veletlen.nextInt(1, 101);
                System.out.println(this.companion+" has attacked the werewolf, dealt the next damage: "+this.companionDamage);
                this.monsterHealth -= this.companionDamage;
                if (this.monsterHealth <= 0){
                    System.out.println("\tWerewolves new health: " + this.monsterHealth);
                    System.out.println("You have defeated the werewolves!");
                    gold+=120;
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
                        System.out.println("You have defeated the werewolves!");
                        gold+=120;
                        break;
                    }
                } else if(critOrNot <= randomCrit && !alreadyAttacked){
                    System.out.println(quotes[0]);
                    this.monsterHealth = this.monsterHealth - this.damage;
                    System.out.println("Damage: " + this.damage);
                    if (this.monsterHealth <= 0){

                        System.out.println("You have defeated the werewolves!");
                        gold+=120;
                        break;
                    }
                }
                System.out.println("\tWerewolves new health: " + this.monsterHealth);
            }
            if (skillChoosed == 2 && this.mana >= 20 ||skillStringChoosed.equals("2")) {
                secondRanDown = true;
                boolean alreadyAttacked = false;
                if(counter < 4 && companion.equals("Dragon")){
                    counter+=1;
                    System.out.println("Dragon has run, counter: "+counter);
                    alreadyAttacked = true;
                    System.out.println("Critical damage!");
                    System.out.println(quotes[1]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.70));
                    System.out.println("Critical damage: " + Math.round(this.damage * 1.70));
                    //Akkor ha a dragon buff közben hal meg az ellenség. (2)
                    if(this.monsterHealth < 0){
                        System.out.println("\tWerewolves new health: " + this.monsterHealth);
                        System.out.println("You have defeated the werewolves!");
                        gold+=120;
                        break;
                    }
                }
                isAttacked = true;
                this.mana -= 20;
                critOrNot = this.luck / 5; //Luck alapján fogom kiszámítani (1)
                randomCrit = veletlen.nextInt(1, 101);
                System.out.println(this.companion+" has attacked the werewolf, dealt the next damage: "+this.companionDamage);
                this.monsterHealth -= this.companionDamage;
                if (this.monsterHealth <= 0){
                    System.out.println("You have defeated the werewolves!");
                    gold+=120;
                    break;
                }
                if (critOrNot >= randomCrit && !alreadyAttacked) {
                    //Akkor crit damage! (1)
                    System.out.println("Critical damage!");
                    System.out.println(quotes[1]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.70));
                    System.out.println("Critical damage: " + Math.round(this.damage * 1.70));
                    //Akkor ha rögtön az első támadás után meghal. (1)
                    if (this.monsterHealth <= 0){
                        System.out.println("You have defeated the werewolves!");
                        gold+=120;
                        break;
                    }
                } else if(critOrNot <= randomCrit &&! alreadyAttacked){
                    System.out.println(quotes[1]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.30));
                    System.out.println("Damage: " + Math.round(this.damage * 1.30));
                    //Akkor ha rögtön az első támadás után meghal.
                    if (this.monsterHealth <= 0){
                        System.out.println("You have defeated the werewolves!");
                        gold+=120;
                        break;
                    }
                }
                System.out.println("\tWerewolves new health: " + this.monsterHealth);
            }
            if (skillChoosed == 3 && this.mana >= 30 || skillStringChoosed.equals("3")) {
                thirdRanDown = true;
                boolean alreadyAttacked = false;
                if(counter < 4 && companion.equals("Dragon")){
                    counter+=1;
                    System.out.println("Dragon has run, counter: "+counter);
                    alreadyAttacked = true;
                    System.out.println("Critical damage!");
                    System.out.println(quotes[2]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 2));
                    System.out.println("Critical damage: " + Math.round(this.damage * 2));
                    //Akkor ha a dragon buff közben hal meg az ellenség. (2)
                    if(this.monsterHealth < 0){
                        System.out.println("\tWerewolves new health: " + this.monsterHealth);
                        System.out.println("You have defeated the werewolves!");
                        gold+=120;
                        break;
                    }
                }
                System.out.println(this.companion+" has attacked the werewolf, dealt the next damage: "+this.companionDamage);
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
                        System.out.println("You have defeated the werewolves!");
                        gold+=120;
                        break;
                    }
                } else if(critOrNot <= randomCrit && !alreadyAttacked){
                    System.out.println(quotes[2]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.40));
                    System.out.println("Damage: " + Math.round(this.damage * 1.40));
                    //Akkor ha rögtön az első támadás után meghal.
                    if (this.monsterHealth <= 0){
                        System.out.println("You have defeated the werewolves!");
                        gold+=120;
                        break;
                    }
                }
                System.out.println("\tWerewolves new health: " + this.monsterHealth);
            }
            else if(skillChoosed == 2 && mana < 20  && !secondRanDown || skillChoosed == 3 && mana <30 && !thirdRanDown){
                System.out.println("Not enough Mana, I use the default skill!");
                boolean alreadyAttacked = false;
                if(counter < 4 && companion.equals("Dragon")){
                    counter+=1;
                    System.out.println("Dragon has run, counter: "+counter);
                    alreadyAttacked = true;
                    System.out.println("Critical damage!");
                    System.out.println(quotes[0]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.50));
                    System.out.println("Critical damage: " + Math.round(this.damage * 1.50));
                    //Akkor ha a dragon buff közben hal meg az ellenség. (2)
                    if(this.monsterHealth < 0){
                        System.out.println("\tWerewolves new health: " + this.monsterHealth);
                        System.out.println("You have defeated the werewolves!");
                        gold+=120;
                        break;
                    }
                }
                System.out.println(this.companion+" has attacked the werewolf, dealt the next damage: "+this.companionDamage);
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
                        System.out.println("You have defeated the werewolves!");
                        gold+=120;
                        break;
                    }
                } else if(critOrNot <= randomCrit && !alreadyAttacked){
                    System.out.println(quotes[0]);
                    this.monsterHealth = this.monsterHealth - Math.round(this.damage);
                    System.out.println("Damage: " + Math.round(this.damage));
                    //Akkor ha rögtön az első támadás után meghal.
                    if (this.monsterHealth <= 0){
                        System.out.println("You have defeated the werewolves!");
                        gold+=120;
                        break;
                    }
                }
                System.out.println("\tWerewolves new health: " + this.monsterHealth);
            }
            if(skillChoosed == 2 || skillStringChoosed.equals("2")) secondRanDown = false;
            else if(skillChoosed == 3 || skillStringChoosed.equals("3")) thirdRanDown = false;
            //Itt jön a farkasnak a támadása!
            boolean dodged = false;
            int dodgeOrNot = this.luck / 5 + this.level /8;
            int dodgededOrNot = veletlen.nextInt(1, 101);
            if(dodgeOrNot >= dodgededOrNot){
                System.out.println(quotes[3]);
                dodged = true;
            }
            int monsterAttack = veletlen.nextInt(1, 4);
            if (monsterAttack == 1 && !dodged) {
                monsterAttacked = true;
                System.out.println("\tThe werewolf bites you!");
                System.out.println("\tOccured damage: " + this.monsterDamage);
                this.health = this.health - this.monsterDamage;
                System.out.println(this.name + "' s new health: " + this.health);
            } else if (monsterAttack == 2 && monsterMana >= 10 && !dodged) {
                monsterAttacked = true;
                System.out.println("\tThe werewolf claws you!");
                this.monsterMana = this.monsterMana - 10;
                System.out.println("\tOccured damage: " + this.monsterDamage * 1.20);
                this.health = (int) (this.health - this.monsterDamage * 1.20);
                System.out.println(this.name + "' s new health: " + this.health);
            } else if (monsterAttack == 3 && monsterMana >= 20 && !dodged) {
                critOrNot = this.monsterLuck / 5;
                randomCrit = veletlen.nextInt(1, 101);
                if (critOrNot >= randomCrit && !monsterAttacked) {
                    System.out.println("The werewolf jumps on you and claws you!");
                    System.out.println("\tCritical hit!");
                    this.monsterMana = this.monsterMana - 20;
                    System.out.println("\tCccurred amage: " + this.monsterDamage * 1.60);
                    this.health -= this.monsterDamage * 1.60;
                    System.out.println(this.name + "' s new health: " + this.health);
                }
                else{
                    System.out.println("\tThe werewolf jumps on you and claws you!");
                    this.monsterMana = this.monsterMana - 20;
                    System.out.println("\tOccurred damage: " + this.monsterDamage * 1.30);
                    this.health = (int) (this.health - this.monsterDamage * 1.30);
                    System.out.println(this.name + "' s new health: " + this.health);
                }
            } else if(!dodged) {
                System.out.println("\tThe werewolf bites you!");
                System.out.println("Occurred damage: " + this.monsterDamage);
                this.health = this.health - this.monsterDamage;
                System.out.println(this.name + "' s new health: " + this.health);
            }
            monsterAttacked = false;
        }
    }
    public void fightQuotes(String race) {
        System.out.println("You slowly approach the werewolves...");
        System.out.println("They have seen you, and before you blink they are already attacking!");
        switch (race) {
            case "Assassin" -> {
                String[] attackQuotes = new String[]{"You slashes into the werewolf!", "You thrown your blades towards the werewolves, nice hit!", "The werewolves don't eve" +
                        "n know what happened..", "You successfully dodged the werewolves attack!, no damage taken."};
                String[] skills = new String[]{"1. Dual slash", "2. Blade throw ", "3. Cyclone Spin"};
                Battle(attackQuotes, skills);
            }
            case "Orc" -> {
                String[] attackQuotesOrc = new String[]{"You slap the wolf on the face with your hammer!", "You grab your hammer with your both hands, and slam it on the wolf!",
                        "You use up your momentum while you sway your hammer, and after the hit the wolf flies away!","You successfully defended the werewolves attack!, no damage taken."};
                String[] skillsOrc = new String[]{"1. Basic slam", "2. Powerful slam", "3. Finisher Slam"};
                Battle(attackQuotesOrc, skillsOrc);
            }
            case "Dark Warrior" -> {
                String[] attackQuotesDark = new String[]{"You slash the werewolf with your claymore!", "You throw your claymore into the werewolf!",
                        "You stagger the werewolf and stab it on the ground!", "You successfully defended the werewolves attack!, no damage taken."};
                String[] skillsDark = new String[]{"1. Basic claymore slash", "2. Claymore throw", "3. Claymore stagger then stab"};
                Battle(attackQuotesDark, skillsDark);
            }
            case "Elf Archer" -> {
                String[] attackQuotesElf = new String[]{"You shot a basic arrow into the wolf!", "You shot double arrows into the wolf!",
                        "You shot a weak point on the wolf!","You successfully dodged the werewolves attack!, no damage taken."};
                String[] skillsElf = new String[]{"1. Basic shot", "2. Double arrow shot", "3. Weak-point shot"};
                Battle(attackQuotesElf, skillsElf);
            }
        }
    }
    public int getGold() {
        return gold;
    }
}