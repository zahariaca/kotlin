/*
import java.util.*;
import java.io.*;
import java.math.*;

*/
/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **//*

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int factoryCount = in.nextInt(); // the number of factories
        int linkCount = in.nextInt(); // the number of links between factories
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < linkCount; i++) {
            int factory1 = in.nextInt();
            int factory2 = in.nextInt();
            int distance = in.nextInt();
            List<Integer> distanceArray = map.getOrDefault(factory1, new ArrayList<>(Collections.nCopies(15, 0)));
            distanceArray.add(factory2, distance);
            map.put(factory1, distanceArray);
        }
        map.forEach((k,v) -> {
            System.out.println("factory1: " + k);
            v.forEach((v1) -> System.out.println("    distance="+v1));
        });
        // game loop
        while (true) {
            System.err.println("START WHILE ----");
            System.err.println("Input: " + in);
            int entityCount = in.nextInt(); // the number of entities (e.g. factories and troops)
            int myFactoryId = -1;
            int enemyFactoryId = -1;
            int troopsNb = -1;
            List<Factory> friendlyFactories = new ArrayList<>();
            List<Factory> enemyFactories = new ArrayList<>();
            for (int i = 0; i < entityCount; i++) {
                int entityId = in.nextInt();
                String entityType = in.next();
                int arg1 = in.nextInt(); // factory owner
                int arg2 = in.nextInt(); // number of troops
                int arg3 = in.nextInt(); // factory production 0-3
                int arg4 = in.nextInt();
                int arg5 = in.nextInt();
                if (entityType.equals("FACTORY")) {
                    if (arg1 == 1) {
                        friendlyFactories.add(new Factory(entityId, arg1, arg2, arg3, arg4, arg5));
                    } else if (arg3 != 0) {
                        enemyFactories.add(new Factory(entityId, arg1, arg2, arg3, arg4, arg5));
                    }
//                    if (arg1 == 1) {
////                        if (troopsNb <= arg2) {
////                            System.err.println("owner(arg1): " + arg1 + "\nnumber of troops(arg2): " + arg2 + "\ntroopsNb: " + troopsNb);
////                            myFactoryId = entityId;
////                            troopsNb = arg2;
////                            System.err.println("myFactoryId=" + myFactoryId + " troopsNb=" +arg2);
////                        }
//                        friendlyFactories.add(new Factory())
//                    } else if (arg3 != 0) {
//                        // enemyFactoryId = entityId;
//                        enemyFactoryIds.add(entityId);
//                    }
                }
            }

//            enemyFactoryIds.forEach(s -> System.err.print(s +" "));
//            System.err.println();

            // if (myFactoryId != -1 && enemyFactoryId != -1 && troopsNb > 5) {
            //     System.out.println("MOVE "+myFactoryId+" "+enemyFactoryId+" "+ troopsNb / 2 + "; MSG test");
            // } 
            // else {
            //     System.out.println("WAIT");
            // }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // Any valid action, such as "WAIT" or "MOVE source destination cyborgs"
            System.out.println("WAIT");
        }
    }
}
class DistancePair {
    public int factory;
    public int distance;

    public DistancePair(int factory, int distance) {
        this.factory = factory;
        this.distance = distance;
    }
}
//data class Factory(val factoryId: Int, val owner: Int, val numberOfTroops: Int, val productionLevel: Int, val arg4: Int, val arg5: Int) {}
class Factory {
    public int factoryId = -2;
    public int owner = -2;
    public int numberOfTroops = -2;
    public int productionLevel = -2;
    public int arg4 = -2;
    public int arg5 = -2;

    public Factory(int factoryId, int owner, int numberOfTroops, int productionLevel, int arg4, int arg5) {
        this.factoryId = factoryId;
        this.owner = owner;
        this.numberOfTroops = numberOfTroops;
        this.productionLevel = productionLevel;
        this.arg4 = arg4;
        this.arg5 = arg5;
    }
}*/
