package com.company;

import java.util.Scanner;

public class Npcdialogue implements Npcs {
    public String yesOrNo;
    public String npcName;
    public void AcceptOrNot(String name){
        boolean answered = false;
        while(!answered){
            System.out.println("Do you accept the quest "+name+" ?");
            System.out.println("Y as yes");
            System.out.println("N as no");
            Scanner answerSC = new Scanner(System.in);
            try{
                String answer = answerSC.next().toUpperCase();
                switch (answer) {
                    case "Y" -> {
                        this.yesOrNo = "Y";
                        answered = true;
                    }
                    case "N" -> {
                        this.yesOrNo = "N";
                        answered = true;
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + answer);
                }
            }
            catch(Exception exception){
                System.out.println("You gave me a number and not an Y or N");
            }
        }
    }
    public void Quest(String race){
        System.out.println("Hello adventurer!");
        switch (race) {
            case "Assassin" -> System.out.println("I see dual blades on your belt with royal marks! you must know how to fight, would you like to help on our village?");
            case "Orc" -> System.out.println("I see a very big hammer on your back with made by a very rare ore! You must know how to use it, right?? would you like to help on our village?");
            case "Dark Warrior" -> System.out.println("That claymore is almost as big as you, Its not a decoration, right?? would you like to help on our village?");
            case "Elf Archer" -> System.out.println("Sharp and long ears, a very big bow on your back, you must be an elf! Your clan is famous about its bow skills, would you like to help on our village?");
        }
        System.out.println("Every night there are werewolves stealing our crops, and our breeding animals.");
        System.out.println("We need someone who is capable stopping it!");
        System.out.println("Recommended level: 20");
        System.out.println("Reward: 120 gold");
    }
    public void Answer(){
        if(this.yesOrNo.equals("Y")){
            this.yesOrNo = "y";
            System.out.println("You will be rewarded greatly! thank you very much, great warrior!");
        }
        else
        {
            this.yesOrNo = "n";
            System.out.println("Seems like your weapons are only for decoration...I will spread this rumour!!");
        }
    }
}
