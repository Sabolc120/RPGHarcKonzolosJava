package com.company;

import java.util.Scanner;

public class NPCdialogueThird implements Npcs{
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
            case "Assassin" -> System.out.println("I heard rumours about an assasin who is highly skilled, so it was you");
            case "Orc" -> System.out.println("Your weapon seems being used a lot, you can help us in fight, right?");
            case "Dark Warrior" -> System.out.println("When a dark knight is more skilled than the others, they have more red fades..you have a lot.");
            case "Elf Archer" -> System.out.println("I didn't even heard that you were here, you seem skilled, please help us");
        }
        System.out.println("Our mines are being occupied by an army of monster rabbits");
        System.out.println("We can't get our coal, our anything metal alike..at this rate we will be back to the prehistoric times ");
        System.out.println("Recommended level: 95");
        System.out.println("Reward: 300 gold");
    }
    public void Answer(){
        if(this.yesOrNo.equals("Y")){
            this.yesOrNo = "y";
            System.out.println("Thank you warrior, you can find the entrance in the east part of the village");
        }
        else
        {
            this.yesOrNo = "n";
            System.out.println("prehistoric times here we come...");
        }
    }
}
