package com.company;


import java.util.Random;
import java.util.Scanner;

public class FightThird extends Player{
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

    public FightThird(String name, int health, int mana, int damage, int luck, int level, String companion, int companionDamage) {
        super(name, health, mana, damage, luck, level, companion, companionDamage);
    }

    public void getHowManyLevel(int gold, int playerLevel) {
        didShopping = true;
        this.gold = gold;
        int mennyiSzint = 0;
        Scanner sc = new Scanner(System.in);
        boolean shoppingOver = false;
        int answer = 0;
        while (!shoppingOver) {
            try {
                System.out.println("My golds: " + this.gold);
                System.out.println("Buy able items: ");
                System.out.println("1. Buy + level for 5 gold");
                System.out.println("2. Exit");
                answer = sc.nextInt();
                switch (answer) {
                    case 1:
                        System.out.println("Input how many levels would you like to buy!");
                        mennyiSzint = sc.nextInt();
                        int cost = mennyiSzint * 5;
                        if (cost > this.gold) {
                            System.out.println("Not enough gold!");
                            System.out.println("Needed gold: " + cost);
                            System.out.println("Golds you have: " + this.gold);
                        } else {
                            this.level = 0;
                            this.howMuchPlusLevel = mennyiSzint + playerLevel;
                            System.out.println("Successful payment, for this much: " + cost);
                            this.level += mennyiSzint + playerLevel;
                            System.out.println("New level: " + this.level);

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
            } catch (Exception exception) {
                System.out.println("My golds: " + this.gold);
                System.out.println("Buy able items: ");
                System.out.println("1. Buy + level for 5 gold");
                System.out.println("2. Exit");
                answer = sc.nextInt();
            }
            if (didShopping) {
                this.health += getHowMuchHealthPlus(oneLevelPlusHealth, howMuchPlusLevel);
                this.damage += getHowMuchDamagePlus(oneLevelUpPlusAttack, howMuchPlusLevel);
                this.mana += getHowMuchManna(oneLevelUpPlusMana, howMuchPlusLevel);
                this.luck += getHowMuchLuck(oneLevelUpPlusLuck, howMuchPlusLevel);
                this.companionDamage += getHowMuchCompanionDamagePlus(levelUpPlusCompanionDamage, howMuchPlusLevel);
                System.out.println("Your new stats are because of shopping: ");
                System.out.println("Health: " + this.health);
                System.out.println("Damage: " + this.damage);
                System.out.println("Mana: " + this.mana);
                System.out.println("Luck: " + this.luck);
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
        this.monsterLevel = 95;
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
            while (badAnswer) {
                System.out.println("Which skill will you choose?");
                System.out.println(skills[0]);
                System.out.println(skills[1]);
                System.out.println(skills[2]);
                try {
                    skillChoosed = beolvas.nextInt();
                    switch (skillChoosed) {
                        case 1:
                        case 2:
                        case 3:
                            badAnswer = false;
                            break;
                    }
                } catch (Exception exception) {
                    System.out.println("You gave me a String instead of a number.");
                    skillStringChoosed = beolvas.next();

                }
            }
            if (skillChoosed == 1 || skillStringChoosed.equals("1")) {
                boolean alreadyAttacked = false;
                if (counter < 4 && companion.equals("Dragon")) {
                    counter += 1;
                    alreadyAttacked = true;
                    System.out.println("Critical damage!");
                    System.out.println(quotes[0]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.30));
                    System.out.println("Critical damage: " + Math.round(this.damage * 1.30));
                    //Akkor ha a dragon buff alat lesz legyőzve az ellenség
                    if (this.monsterHealth <= 0) {
                        System.out.println("\tRabbit's army new health: " + this.monsterHealth);
                        System.out.println("You have defeated the rabbit army!!");
                        gold += 400;
                        break;
                    }
                }
                critOrNot = this.luck / 5; //Luck alapján fogom kiszámítani
                randomCrit = veletlen.nextInt(1, 101);
                System.out.println(this.companion + " has attacked the rabbit's, dealt the next damage: " + this.companionDamage);
                this.monsterHealth -= this.companionDamage;
                if (this.monsterHealth <= 0) {
                    System.out.println("\tRabbit's army new health: " + this.monsterHealth);
                    System.out.println("You have defeated the rabbit's army!");
                    gold += 400;
                    break;
                }
                if (critOrNot >= randomCrit && !alreadyAttacked) {
                    System.out.println(quotes[0]);
                    //Akkor crit damage!
                    System.out.println("Critical damage!");
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.30));
                    System.out.println("Critical damage: " + Math.round(this.damage * 1.30));

                    //Akkor ha rögtön az első támadás után meghal.
                    if (this.monsterHealth <= 0) {
                        System.out.println("You have defeated the rabbit's army!");
                        gold += 400;
                        break;
                    }
                } else if (critOrNot <= randomCrit && !alreadyAttacked) {
                    System.out.println(quotes[0]);
                    this.monsterHealth = this.monsterHealth - this.damage;
                    System.out.println("Damage: " + this.damage);
                    if (this.monsterHealth <= 0) {

                        System.out.println("You have defeated the rabbit's army!");
                        gold += 400;
                        break;
                    }
                }
                System.out.println("\tRabbit's army new health: " + this.monsterHealth);
            }
            if (skillChoosed == 2 && this.mana >= 20 || skillStringChoosed.equals("2")) {
                secondRanDown = true;
                boolean alreadyAttacked = false;
                if (counter < 4 && companion.equals("Dragon")) {
                    counter += 1;
                    alreadyAttacked = true;
                    System.out.println("Critical damage!");
                    System.out.println(quotes[1]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.70));
                    System.out.println("Critical damage: " + Math.round(this.damage * 1.70));
                    //Akkor ha a dragon buff közben hal meg az ellenség. (2)
                    if (this.monsterHealth < 0) {
                        System.out.println("\tRabbit's army new health: " + this.monsterHealth);
                        System.out.println("You have defeated the rabbit's army!");
                        gold += 400;
                        break;
                    }
                }
                this.mana -= 20;
                critOrNot = this.luck / 5; //Luck alapján fogom kiszámítani (1)
                randomCrit = veletlen.nextInt(1, 101);
                System.out.println(this.companion + " has attacked the rabbit's, dealt the next damage: " + this.companionDamage);
                this.monsterHealth -= this.companionDamage;
                if (this.monsterHealth <= 0) {
                    System.out.println("You have defeated the rabbit's army!");
                    gold += 400;
                    break;
                }
                if (critOrNot >= randomCrit && !alreadyAttacked) {
                    //Akkor crit damage! (1)
                    System.out.println("Critical damage!");
                    System.out.println(quotes[1]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.70));
                    System.out.println("Critical damage: " + this.damage * 1.70);
                    //Akkor ha rögtön az első támadás után meghal. (1)
                    if (this.monsterHealth <= 0) {
                        System.out.println("You have defeated the rabbit's army!");
                        gold += 400;
                        break;
                    }
                } else if (critOrNot <= randomCrit && !alreadyAttacked) {
                    System.out.println(quotes[1]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.30));
                    System.out.println("Damage: " + Math.round(this.damage * 1.30));
                    //Akkor ha rögtön az első támadás után meghal.
                    if (this.monsterHealth <= 0) {
                        System.out.println("You have defeated the rabbit's army!");
                        gold += 400;
                        break;
                    }
                }
                System.out.println("\tRabbit's army new health: " + this.monsterHealth);
            }
            if (skillChoosed == 3 && this.mana >= 30 || skillStringChoosed.equals("3")) {
                thirdRanDown = true;
                boolean alreadyAttacked = false;
                if (counter < 4 && companion.equals("Dragon")) {
                    counter += 1;
                    alreadyAttacked = true;
                    System.out.println("Critical damage!");
                    System.out.println(quotes[2]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 2));
                    System.out.println("Critical damage: " + Math.round(this.damage * 2));
                    //Akkor ha a dragon buff közben hal meg az ellenség. (2)
                    if (this.monsterHealth < 0) {
                        System.out.println("\tRabbit's army new health: " + this.monsterHealth);
                        System.out.println("You have defeated the rabbit's army!");
                        gold += 400;
                        break;
                    }
                }
                System.out.println(this.companion + " has attacked the rabbit's, dealt the next damage: " + this.companionDamage);
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
                    if (this.monsterHealth <= 0) {
                        System.out.println("You have defeated the rabbit's army!");
                        gold += 400;
                        break;
                    }
                } else if (critOrNot <= randomCrit && !alreadyAttacked) {
                    System.out.println(quotes[2]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.40));
                    System.out.println("Damage: " + Math.round(this.damage * 1.40));
                    //Akkor ha rögtön az első támadás után meghal.
                    if (this.monsterHealth <= 0) {
                        System.out.println("You have defeated the rabbit's army!");
                        gold += 400;
                        break;
                    }
                }
                System.out.println("\tRabbit's army new health: " + this.monsterHealth);
            } else if (skillChoosed == 2 && mana < 20 && !secondRanDown || skillChoosed == 3 && mana < 30 && !thirdRanDown) {
                System.out.println("Not enough Mana, I use the default skill!");
                boolean alreadyAttacked = false;
                if (counter < 4 && companion.equals("Dragon")) {
                    counter += 1;
                    alreadyAttacked = true;
                    System.out.println("Critical damage!");
                    System.out.println(quotes[0]);
                    this.monsterHealth = (int) (this.monsterHealth - Math.round(this.damage * 1.50));
                    System.out.println("Critical damage: " + Math.round(this.damage * 1.50));
                    //Akkor ha a dragon buff közben hal meg az ellenség. (2)
                    if (this.monsterHealth < 0) {
                        System.out.println("\tRabbit's army new health: " + this.monsterHealth);
                        System.out.println("You have defeated the rabbit's army!");
                        gold += 400;
                        break;
                    }
                }
                System.out.println(this.companion + " has attacked the rabbit's, dealt the next damage: " + this.companionDamage);
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
                    if (this.monsterHealth <= 0) {
                        System.out.println("You have defeated the rabbit's army!");
                        gold += 400;
                        break;
                    }
                } else if (critOrNot <= randomCrit && !alreadyAttacked) {
                    System.out.println(quotes[0]);
                    this.monsterHealth = this.monsterHealth - Math.round(this.damage);
                    System.out.println("Damage: " + Math.round(this.damage));
                    //Akkor ha rögtön az első támadás után meghal.
                    if (this.monsterHealth <= 0) {
                        System.out.println("You have defeated the rabbit's army!");
                        gold += 400;
                        break;
                    }
                }
                System.out.println("\tRabbit's army new health: " + this.monsterHealth);
            }
            if (skillChoosed == 2 || skillStringChoosed.equals("2")) secondRanDown = false;
            else if (skillChoosed == 3 || skillStringChoosed.equals("3")) thirdRanDown = false;
            //Itt jön a  rabbit army a támadása!
            boolean dodged = false;
            int dodgeOrNot = this.luck / 5 + this.level / 8;
            int dodgededOrNot = veletlen.nextInt(1, 101);
            if (dodgeOrNot >= dodgededOrNot) {
                System.out.println(quotes[3]);
                dodged = true;
            }
            int monsterAttack = veletlen.nextInt(1, 4);
            if (monsterAttack == 1 && !dodged) {
                System.out.println("\tThe rabbit army tries to take you down");
                System.out.println("\tOccurred damage: " + this.monsterDamage);
                this.health = this.health - this.monsterDamage;
                System.out.println(this.name + "' s new health: " + this.health);
            } else if (monsterAttack == 2 && monsterMana >= 10 && !dodged) {
                System.out.println("\tThe rabbits tried to hurt a civilian, but you saved them..");
                this.monsterMana = this.monsterMana - 10;
                System.out.println("\tOccurred damage: " + this.monsterDamage * 1.20);
                this.health = (int) (this.health - this.monsterDamage * 1.20);
                System.out.println(this.name + "' s new health: " + this.health);
            } else if (monsterAttack == 3 && monsterMana >= 20 && !dodged) {
                critOrNot = this.monsterLuck / 5;
                randomCrit = veletlen.nextInt(1, 101);
                System.out.println("\tThe rabbit army ambushed you!");
                if (critOrNot >= randomCrit) {
                    isAttacked = true;
                    System.out.println("\tCritical hit!");
                    this.monsterMana = this.monsterMana - 20;
                    System.out.println("\tOccurred damage: " + this.monsterDamage * 1.60);
                    this.health -= this.monsterDamage * 1.60;
                } else {
                    this.monsterMana = this.monsterMana - 20;
                    System.out.println("\tOccurred damage: " + this.monsterDamage * 1.50);
                    this.health = (int) (this.health - this.monsterDamage * 1.50);
                }
                System.out.println(this.name + "' s new health: " + this.health);
            } else if (!dodged) {
                System.out.println("\tThe rabbit army tries to take you down");
                System.out.println("Occurred damage: " + this.monsterDamage);
                this.health = this.health - this.monsterDamage;
                System.out.println(this.name + "' s new health: " + this.health);
            }
        }
    }

    public void fightQuotes(String race) {
        System.out.println("You just entered into the cave, but you already hear rabbit sounds..");
        System.out.println("Its getting more and more noisy, and when you arrive, you see a bunch of rabbits with glowing red eyes!");
        switch (race) {
            case "Assassin" -> {
                String[] attackQuotes = new String[]{"You slashes into the giant rabbits!", "You thrown your blades towards rabbit's and blow it up, nice hit!", "You did a nice wide slash in the air, catched a lot rabbits with it!", "You successfully dodged the rabbit's army attack!, no damage taken."};
                String[] skills = new String[]{"1. Dual slash", "2. Surprise blade thrown", "3. Air slash"};
                Battle(attackQuotes, skills);
            }
            case "Orc" -> {
                String[] attackQuotesOrc = new String[]{"You are making pancakes with your hammer!", "You did a battle roar for taunting the rabbits, then you throw them away with your hammer!",
                        "You made a very big slam into the ground, creating a mini earthquake, the rabbits are falling into it!", "You successfully defended the giant rabbit's army attack!, no damage taken."};
                String[] skillsOrc = new String[]{"1. Basic slam", "2. Battle roar", "3. Earthquake slam"};
                Battle(attackQuotesOrc, skillsOrc);
            }
            case "Dark Warrior" -> {
                String[] attackQuotesDark = new String[]{"You slashes into the rabbit's army", "You throw your claymore into the rabbit's army central!",
                        "You made your sword spinning, and thrown it into the rabbit's army!", "You successfully defended the giant rabbits army attack, no damage taken."};
                String[] skillsDark = new String[]{"1. Basic claymore slash", "2. Claymore throw", "3. Spinning blade"};
                Battle(attackQuotesDark, skillsDark);
            }
            case "Elf Archer" -> {
                String[] attackQuotesElf = new String[]{"You shot 4x arrows at once, nice accuracy!", "You shot a trap arrow into the rabbit crowd, trapping them!",
                        "You shot an exploding arrow into the rabbit crowd!", "You successfully dodged the rabbit's army attack!, no damage taken."};
                String[] skillsElf = new String[]{"1. 4x Shot", "2. Trap arrow", "3. Exploding arrow"};
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