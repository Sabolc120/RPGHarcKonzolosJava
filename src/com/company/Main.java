package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Player player = new Player("", 100, 20, 20, 15, 1, "", 20);
        System.out.println("Creating character!");
        player.whichSword();
        player.levelBonus(player.health, player.damage, player.luck, player.mana);
        player.whichRace();
        Npcdialogue firstQuest = new Npcdialogue();
        firstQuest.Quest(player.getRace());
        firstQuest.AcceptOrNot(player.getName());
        firstQuest.Answer();
        FightSecond secondFight = new FightSecond(player.getName(),player.getHealth(),player.getMana(),player.getDamage(),player.getLuck(),player.getLevel(),player.getCompanion(),player.getCompanionDamage());
        FightThird thirdFight = new FightThird(player.getName(),player.getHealth(),player.getMana(),player.getDamage(),player.getLuck(),player.getLevel(),player.getCompanion(),player.getCompanionDamage());
        if(firstQuest.yesOrNo.equals("y")){
            System.out.println("First quest accepted");
            Fight harc = new Fight(player.getName(),player.getHealth(),player.getMana(),player.getDamage(),player.getLuck(),player.getLevel(),player.getCompanion(),player.getCompanionDamage());
            harc.fightQuotes(player.getRace()); //Ezzel kezdem meg a csatát
            Scanner sc = new Scanner(System.in);
            boolean isOver = false;
            while(!isOver){
                try{
                    System.out.println("Its very very recommended to spend all your gold and buy new levels! The monsters will be getting very strong.");
                    System.out.println("Would you like to buy something?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    byte answer = Byte.parseByte(sc.next());
                    if(answer == 1){
                        secondFight.getHowManyLevel(harc.getGold());
                        isOver = true;
                    }
                    else{
                        System.out.println("There are only 2 inputs.");
                        System.out.println("1. Yes I want to shop");
                        System.out.println("2. No I dont want to");
                    }
                }
                catch(Exception exception){
                    System.out.println("Incorrect input(2), Try it again!");
                }
            }
        }
        else{
            System.out.println("First quest declined.");
        }
        NPCdialogueSecond secondQuest = new NPCdialogueSecond(); //Létrehozok egy objektumot csak a második küldetésre
        secondQuest.Quest(player.getRace()); //Előhívja az NPC mondanivalóját
        secondQuest.AcceptOrNot(player.getName()); //Felteszi a kérdést hogy elfogadjuk a küldetést vagy nem
        secondQuest.Answer(); //Az alapján hogy elfogadtuk vagy elutasítottuk fog mondani még valamit az NPC
        Scanner sc = new Scanner(System.in);
        if(secondQuest.yesOrNo.equals("y")){
            System.out.println("Second quest accepted");
            secondFight.fightQuotes(player.getRace());
            boolean isOver = false;
            while(!isOver){
                try{
                    System.out.println("Its very very recommended to spend all your gold and buy new levels! The monsters will be getting very strong.");
                    System.out.println("Would you like to buy something?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    byte answer = Byte.parseByte(sc.next());
                    if(answer == 1){
                        thirdFight.getHowManyLevel(secondFight.getGold(), secondFight.getLevel());
                        isOver = true;
                    }
                    else{
                        System.out.println("There are only 2 inputs.");
                        System.out.println("1. Yes I want to shop");
                        System.out.println("2. No I dont want to");
                    }
                }
                catch(Exception exception){
                    System.out.println("Incorrect input(2), Try it again!");
                }
            }
        }
        else{
            System.out.println("Second quest declined");
        }

        NPCdialogueThird thirdQuest = new NPCdialogueThird(); //Létrehozok egy objektumot csak a második küldetésre
        thirdQuest.Quest(player.getRace()); //Előhívja az NPC mondanivalóját
        thirdQuest.AcceptOrNot(player.getName()); //Felteszi a kérdést hogy elfogadjuk a küldetést vagy nem
        thirdQuest.Answer(); //Az alapján hogy elfogadtuk vagy elutasítottuk fog mondani még valamit az NPC
        if(secondQuest.yesOrNo.equals("y")) {
            System.out.println("Third quest accepted");
            thirdFight.fightQuotes(player.getRace());
        }
        else System.out.println("Third quest declined");
        System.out.println("Köszönöm szépen hogy megnézted a programom, ezzel a programnak vége lett.");
    }
}
