package com.zahariaca.bronze;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Grab the pellets as fast as you can!
 **/
class Player {

    private static final String ROCK = "ROCK";
    private static final String PAPER = "PAPER";
    private static final String SCISSORS = "SCISSORS";

    static class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class PacMan {
        Coordinate currentPosition;
        int id;
        String type;
        int speedTurnsLeft;
        int cooldown;
        int minDist;

        Coordinate goToPossition;

        PacMan(int id, int x, int y, String type, int speedTurnsLeft, int cooldown) {
            this.currentPosition = new Coordinate(x, y);
            this.id = id;
            this.type = type;
            this.speedTurnsLeft = speedTurnsLeft;
            this.cooldown = cooldown;
            this.goToPossition = new Coordinate(0, 0);
            this.minDist = Integer.MAX_VALUE;
        }

        public void setCurrentPosition(int x, int y) {
            this.currentPosition = new Coordinate(x, y);
            this.minDist = Integer.MAX_VALUE;
        }

        public void setGoToPossition(int x, int y) {
            this.goToPossition = new Coordinate(x, y);
        }

        public void computeDistance(int x, int y) {
            int currentX = currentPosition.x;
            int currentY = currentPosition.y;
            int distance = (int) Math.sqrt(Math.pow((currentX - x), 2) + Math.pow((currentY - y), 2));
            if (minDist > distance) {
                minDist = distance;
                goToPossition = new Coordinate(x, y);
            }
        }

        public String getMoveAction() {
            StringBuilder msg = new StringBuilder();
            msg.append("MOVE ").append(id).append(" ").append(goToPossition.x).append(" ").append(goToPossition.y);
            return msg.toString();
        }
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // size of the grid
        int height = in.nextInt(); // top left corner is (x=0, y=0)
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < height; i++) {
            String row = in.nextLine(); // one line of the grid: space " " is floor, pound "#" is wall
        }

        Map<Integer, PacMan> myPacs = new HashMap<>();
        boolean firstTurn = true;

        // game loop
        while (true) {
            int myScore = in.nextInt();
            int opponentScore = in.nextInt();
            int visiblePacCount = in.nextInt(); // all your pacs and enemy pacs in sight
            // PacMan pac;
            for (int i = 0; i < visiblePacCount; i++) {
                int pacId = in.nextInt(); // pac number (unique within a team)
                boolean mine = in.nextInt() != 0; // true if this pac is yours
                int x = in.nextInt();
                int y = in.nextInt();
                String typeId = in.next();
                int speedTurnsLeft = in.nextInt();
                int abilityCooldown = in.nextInt();
                if (mine) {
                    if (firstTurn) {
                        myPacs.put(pacId, new PacMan(pacId, x, y, typeId, speedTurnsLeft, abilityCooldown));
                    } else {
                        PacMan pac = myPacs.get(pacId);
                        pac.setCurrentPosition(x, y);
                        // pac.type = typeId;
                        pac.speedTurnsLeft = speedTurnsLeft;
                        pac.cooldown = abilityCooldown;
                    }
                }
            }
            if (firstTurn) {
                firstTurn = false;
            }
            int visiblePelletCount = in.nextInt(); // all pellets in sight
            for (int i = 0; i < visiblePelletCount; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int value = in.nextInt(); // amount of points this pellet is worth
                for (PacMan pac : myPacs.values()) {
                    pac.computeDistance(x, y);
                }
            }

            // To debug: System.err.println("Debug messages...");
            // MOVE <pacId> <x> <y>
            // System.out.println("MOVE " + pac.id + " " + pac.coord.x + " " + pac.coord.y);
            StringBuilder output = new StringBuilder();
            for (PacMan pac : myPacs.values()) {
                output.append(pac.getMoveAction()).append("|");
            }
            output.setLength(output.length() - 1);
            System.out.println(output);
        }
    }
}
