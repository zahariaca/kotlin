import java.util.*
import kotlin.math.abs

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)

    // game loop
    while (true) {
        val actionCount = input.nextInt() // the number of spells and recipes in play

        val customerRequests = mutableListOf<Brew>()
        var castList = mutableListOf<Cast>()
        var learnList = mutableListOf<Learn>()
        var myInventory = Materials()
        for (i in 0 until actionCount) {
            val actionId = input.nextInt() // the unique ID of this spell or recipe
            val actionType = input.next() // in the first league: BREW; later: CAST, OPPONENT_CAST, LEARN, BREW
            val delta0 = input.nextInt() // tier-0 ingredient change
            val delta1 = input.nextInt() // tier-1 ingredient change
            val delta2 = input.nextInt() // tier-2 ingredient change
            val delta3 = input.nextInt() // tier-3 ingredient change
            val materials = Materials(delta0, delta1, delta2, delta3)
            val price = input.nextInt() // the price in rupees if this is a potion
            val tomeIndex =
                input.nextInt() // in the first two leagues: always 0; later: the index in the tome if this is a tome spell, equal to the read-ahead tax
            val taxCount =
                input.nextInt() // in the first two leagues: always 0; later: the amount of taxed tier-0 ingredients you gain from learning this spell
            val castable =
                input.nextInt() != 0 // in the first league: always 0; later: 1 if this is a castable player spell
            val repeatable =
                input.nextInt() != 0 // for the first two leagues: always 0; later: 1 if this is a repeatable player spell

            when (actionType) {
                "BREW" -> customerRequests.add(Brew(actionId, price, materials, calculateWeight(materials)))
                "CAST" -> castList.add(Cast(actionId, materials, castable, repeatable))
                "LEARN" -> learnList.add(Learn(actionId, tomeIndex, taxCount, repeatable))
            }
        }

        System.err.println("Brew list:")
//        customerRequests.sortBy { it.price }
        customerRequests.forEach { System.err.println("    $it") }
        System.err.println("Cast list:")
        castList.forEach { System.err.println("    $it") }

        for (i in 0 until 2) {
            val inv0 = input.nextInt() // tier-0 ingredients in inventory
            val inv1 = input.nextInt()
            val inv2 = input.nextInt()
            val inv3 = input.nextInt()
            val score = input.nextInt() // amount of rupees
            if (i == 0) {
                myInventory = Materials(inv0, inv1, inv2, inv3)
                System.err.println("My inventory: $myInventory")
            }
        }

        val brewableList = mutableListOf<Brew>()

        customerRequests.forEach { req ->
//            System.err.println("Can afford? ${req.id} ${req.materials.canAfford(myInventory)}")
            if (myInventory.canAfford(req.materials, 1)) {
                brewableList.add(req)
            }
        }

        val highestBrew = getHighestOrder(customerRequests)
        System.err.println("highestBrew: \n    $highestBrew")

        if (myInventory.t3 + highestBrew.materials.t3 >= 0) {
//            System.err.println("removing cast for t3")
            castList.removeAt(3)
        }
        if (myInventory.t2 + highestBrew.materials.t2 >= 1) {
//            System.err.println("removing cast for t2")
            castList.removeAt(2)
        }
        if (myInventory.t1 + highestBrew.materials.t1 >= 1) {
//            System.err.println("removing cast for t1")
            castList.removeAt(1)
        }
        if (myInventory.t0 >= 5) {
//            System.err.println("removing cast for t0")
            castList.removeAt(0)
        }

        val castableList = castList.filter { it.castable }
//        System.err.println("castableList:")
//        castableList.forEach { System.err.println("    $it") }

        var command = ""
        val godTier = arrayOf(0, 2, 3, 4, 12, 13, 14, 15, 16, 29, 30, 38, 86) //0,29 not god tier
//        val godTier = arrayOf(0, 2, 3, 4, 12, 13, 14, 15, 16, 33, 29, 30, 37, 11, 31, 34, 35, 36, 21, 32, 38, 39, 40)
        var godTierLearn = mutableListOf<Learn>()
        godTier.forEach { id -> learnList.find { it.id == id }?.let { godTierLearn.add(it) } }
        godTierLearn.sortByDescending { it.tomeIndex }

        var godTierAffordable = godTierLearn.filter { it.canAfford(myInventory) }.toMutableList()
        godTierAffordable.sortBy { it.tomeIndex }
        System.err.println("God Tier Learn list:")
        godTierAffordable.forEach { System.err.println("    $it") }


        var customerReqCanAfford = customerRequests.filter { it.materials.canAfford(myInventory, 1) }.toMutableList()
        System.err.println("customerReqCanAfford: ")
        customerReqCanAfford.sortBy { it.price }
        customerReqCanAfford.forEach { System.err.println("    $it") }


        if (myInventory.canAfford(highestBrew.materials, 1)) {
            // in the first league: BREW <id> | WAIT; later: BREW <id> | CAST <id> [<times>] | LEARN <id> | REST | WAIT
            if (customerReqCanAfford.size > 1) {
                // check which recipe pays better
                System.err.println(">>> brew command with brew that pays best: ${customerReqCanAfford[0]}")
                customerReqCanAfford.sortByDescending { it.price }
                command = customerReqCanAfford[0].getCommand()
            } else {
                System.err.println(">>> brew command with highest brew: $highestBrew")
                command = highestBrew.getCommand()
            }
        } else if (customerReqCanAfford.size >= 2) {
            System.err.println(">>> brew command: ${customerReqCanAfford[0]}")
            customerReqCanAfford.sortByDescending { it.price }
            command = customerReqCanAfford[0].getCommand()
        } else if (godTierAffordable.isNotEmpty()) {
            var cheapestLearn = godTierAffordable[0]
            System.err.println(">>> learn command: $cheapestLearn")
            command = cheapestLearn.getCommand()
        } else if (castableList.isNotEmpty()) {
            for (cast in castableList) {
                if (myInventory.canHold(cast.materials, 1)
                    && myInventory.canAfford(cast.materials, 1)
                ) {

                    // check if 

                    if (cast.repeatable && myInventory.canAfford(cast.materials, 2)) {
                        System.err.println(">>> cast double command: $cast")
                        command = cast.getDoubleCommand()
                        break
                    } else {
                        System.err.println(">>> cast command: $cast")
                        command = cast.getCommand()
                        break
                    }
                }
            }

            //
            highestBrew.materials
        }

        if (command.isBlank()) {
            System.err.println(">>> rest command")
            println("REST")
        } else {
//            println("$command Target: ${highestBrew.id}|${highestBrew.price}")
            println(command)
        }

    }
}

fun getHighestOrder(customerRequests: MutableList<Brew>): Brew {
    if (customerRequests.size >= 2
        && customerRequests[0].price == customerRequests[1].price
        && customerRequests[0].weight > customerRequests[1].weight
    ) {
        return customerRequests[1]
    }
    return customerRequests[0]

}

fun calculateWeight(materials: Materials): Int {
    return abs(materials.t0) * 1 + abs(materials.t1) * 2 + abs(materials.t2) * 3 + abs(materials.t3) * 4
}

data class Brew(val id: Int, val price: Int, val materials: Materials, val weight: Int) {

    fun getCommand(): String {
        return "BREW $id"
    }

}

data class Cast(val id: Int, val materials: Materials, val castable: Boolean, val repeatable: Boolean) {
    fun getCommand(): String {
        return "CAST $id"
    }

    fun getDoubleCommand(): String {
        return "CAST $id 2"
    }
}

data class Learn(val id: Int, val tomeIndex: Int, val taxCount: Int, val repeatable: Boolean) {
    fun canAfford(materials: Materials): Boolean {
        System.err.println("DEBUG: ${materials.t0 - tomeIndex}")
        return materials.t0 - tomeIndex >= 0
    }

    fun getCommand(): String {
        return "LEARN $id"
    }
}

data class Materials(var t0: Int, var t1: Int, var t2: Int, var t3: Int) {
    constructor() : this(0, 0, 0, 0)

    fun canAfford(other: Materials, numberOfCasts: Int): Boolean {
        return t0 + other.t0 * numberOfCasts >= 0
                && t1 + other.t1 * numberOfCasts >= 0
                && t2 + other.t2 * numberOfCasts >= 0
                && t3 + other.t3 * numberOfCasts >= 0
    }

    fun canHold(other: Materials, numberOfCasts: Int): Boolean {
        return sum() + other.sum() * numberOfCasts <= 10
    }

    fun sum(): Int {
        return t0 + t1 + t2 + t3
    }

    override fun toString(): String {
        return "[$t0, $t1,$t2,$t3]"
    }
}