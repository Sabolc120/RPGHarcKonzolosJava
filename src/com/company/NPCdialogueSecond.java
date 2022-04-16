package com.company;

import java.util.Scanner;

public class NPCdialogueSecond implements Npcs{
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
            case "Assassin" -> System.out.println("I've never seen this shiny blades, they must be sharp right?");
            case "Orc" -> System.out.println("I saw your hammer and I was thinking..are you an adventurer?");
            case "Dark Warrior" -> System.out.println("This dark armor, and dark claymore..you must be a Dark Knight");
            case "Elf Archer" -> System.out.println("I can recognize your type anytime, as your ears are telling everything");
        }
        System.out.println("Our village has a curse that happens every 10.th years");
        System.out.println("A giant worm appears from our grounds which ates our people, our plants..");
        System.out.println("Recommended level: 50");
        System.out.println("Reward: 300 gold");
    }
    public void Answer(){
        if(this.yesOrNo.equals("Y")){
            this.yesOrNo = "y";
            System.out.println("Thank you very much great warrior, the worm usually appears at the afternoon!");
        }
        else
        {
            this.yesOrNo = "n";
            System.out.println("I heard that an adventurer arrived into our village..false hope, its just a coward.");
        }
    }
}
