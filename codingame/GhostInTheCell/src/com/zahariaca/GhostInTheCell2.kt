package com.zahariacaimport
import java.util.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val factoryCount = input.nextInt() // the number of factories
    val linkCount = input.nextInt() // the number of links between factories
    var bombCount = 0;
    val factoriesMap = mutableMapOf<Int, Factory>()
    for (i in 0 until linkCount) {
        val factoryRoot: Int = input.nextInt()
        val factoryDestination: Int = input.nextInt()
        val distance: Int = input.nextInt()
        val rootFactory = factoriesMap.getOrDefault(factoryRoot, Factory(factoryRoot, mutableListOf(Pair(factoryDestination, distance))))
        rootFactory.addDistancePair(Pair(factoryDestination, distance))
        factoriesMap[factoryRoot] = rootFactory
    }
/*
    map.forEach { (k, v) ->
        System.err.println("factoryRoot: $k")
        System.err.println("factory object: $v")
    }
*/

    // game loop
    while (true) {
        val entityCount = input.nextInt() // the number of entities (e.g. factories and troops)
        for (i in 0 until entityCount) {
            val entityId = input.nextInt()
            val entityType = input.next()
            val factoryId = input.nextInt()
            val owner = input.nextInt()
            val numberOfTroops = input.nextInt()
            val arg4 = input.nextInt()
            val arg5 = input.nextInt()
            if (entityType == "FACTORY") {
                val factory = factoriesMap[factoryId]
                factory!!.owner = owner
                factory.numberOfTroops = numberOfTroops
            }
//                if (arg1 == 1) {
//                    friendlyFactories.add(Factory(entityId, arg1, arg2, arg3, arg4, arg5))
//                } else if (arg3 != 0) {
//                    enemyFactories.add(Factory(entityId, arg1, arg2, arg3, arg4, arg5))
//                } else {
//                    uselessFactories.add(Factory(entityId, arg1, arg2, arg3, arg4, arg5))
//                }
//            }
        }

        factoriesMap.asSequence()
                .filter { it.value.owner == 1 }
                .joinToString(separator = ";") {
                    friendlyFactory ->
                    val list = friendlyFactory.value.distancePairs
                    val closest = list.minBy { it.second }
                    "MOVE ${friendlyFactory.value.factoryId} ${closest!!.first} ${friendlyFactory.value.numberOfTroops - 5}"
                }
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

data class Factory(val factoryId: Int, var distancePairs: MutableList<Pair<Int, Int>>, var owner: Int = -2, var numberOfTroops: Int = -2, var productionLevel: Int = -2, var arg4: Int = -2, var arg5: Int = -2) {

    fun addDistancePair(pair: Pair<Int, Int>) {
        distancePairs.add(pair)
    }
}