package com.zahariaca/*
import java.util.*

*/
/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **//*

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val factoryCount = input.nextInt() // the number of factories
    val linkCount = input.nextInt() // the number of links between factories
    var bombCount = 0;
    val map = mutableMapOf<Int, ArrayList<Pair<Int, Int>>>()
    for (i in 0 until linkCount) {
        val factory1: Int = input.nextInt()
        val factory2: Int = input.nextInt()
        val distance: Int = input.nextInt()
        val distanceArray = map.getOrDefault(factory1, arrayListOf())
        distanceArray.add(Pair(factory2, distance))
        map[factory1] = distanceArray
    }
    */
/*map.forEach { (k, v) ->
        System.err.println("factory1: $k")
        v.indices.forEach{ System.err.println("    Pair(factory2,distance)=${v[it]}")}
    }*//*


    // game loop
    while (true) {
        val entityCount = input.nextInt() // the number of entities (e.g. factories and troops)
        val friendlyFactories = mutableListOf<Factory>()
        val enemyFactories = mutableListOf<Factory>()
        val uselessFactories = mutableListOf<Factory>()
        for (i in 0 until entityCount) {
            val entityId = input.nextInt()
            val entityType = input.next()
            val arg1 = input.nextInt()
            val arg2 = input.nextInt()
            val arg3 = input.nextInt()
            val arg4 = input.nextInt()
            val arg5 = input.nextInt()
            if (entityType == "FACTORY") {
                if (arg1 == 1) {
                    friendlyFactories.add(Factory(entityId, arg1, arg2, arg3, arg4, arg5))
                } else if (arg3 != 0) {
                    enemyFactories.add(Factory(entityId, arg1, arg2, arg3, arg4, arg5))
                } else {
                    uselessFactories.add(Factory(entityId, arg1, arg2, arg3, arg4, arg5))
                }
            }
        }

        System.err.println(friendlyFactories)
        System.err.println(enemyFactories)

        if (friendlyFactories.isEmpty() || enemyFactories.isEmpty()) {
            println("WAIT")
        } else {
            var command: String = "MSG PREPARE!;";

            friendlyFactories
                    .filter { it.numberOfTroops >= 5 }
                    .forEach {
                        val distanceList = map[it.factoryId] ?: arrayListOf()
                        val sortedList = distanceList.sortedBy { pair -> pair.second }
                        val numberOfAvailableTroops = it.numberOfTroops - 5
                        command += sortedList.joinToString(separator = ";") { pair ->
                            "MOVE ${it.factoryId} ${pair.first} ${numberOfAvailableTroops / sortedList.size}"
                        }
                    }

            println(command)
//            val strongestFactory = friendlyFactories.maxBy { it.numberOfTroops }
//
//            val maxNumberOfTroops = strongestFactory!!.numberOfTroops - 5
//            val command = enemyFactories.take(3).joinToString(separator = ";") { eF -> "MOVE ${strongestFactory.factoryId} ${eF.factoryId} ${strongestFactory.numberOfTroops / enemyFactories.size}" }
//            if (bombCount == 0 || bombCount == 20) {
//                val strongestEnemyFactory = enemyFactories.filter { it.owner == -1 }.maxBy { it.numberOfTroops }
//                println("$command;BOMB ${strongestFactory!!.factoryId} ${strongestEnemyFactory!!.factoryId}")
//            } else {
//                println(command)
//                ++bombCount
//            }
            // Write an action using println()
            // To debug: System.err.println("Debug messages...");


            // Any valid action, such as "WAIT" or "MOVE source destination cyborgs"
//        println("WAIT")
        }
    }
}

data class Factory(val factoryId: Int, val owner: Int, val numberOfTroops: Int, val productionLevel: Int, val arg4: Int, val arg5: Int) {}*/
