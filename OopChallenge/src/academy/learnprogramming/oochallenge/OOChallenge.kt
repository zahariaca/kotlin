package academy.learnprogramming.oochallenge

fun main(args: Array<String>) {
    val bicycle = KotlinBicycle(10, 5, 20)
    val mountainBike = KotlinMountainBike(150, 20, 7, 32)
    val roadBike = KotlinRoadBike(200, 23, 5, 12)
    bicycle.printDescription()
    mountainBike.printDescription()
    roadBike.printDescription()
    println("----------------------------------")

    val bicycle2 = KotlinBicycle(10, 5)
    val mountainBike2 = KotlinMountainBike(150, 20, 7)
    val roadBike2 = KotlinRoadBike(200, 23, 5)

    bicycle2.printDescription()
    mountainBike2.printDescription()
    roadBike2.printDescription()
    println("----------------------------------")

    val mountainBike3 = KotlinMountainBike("red", 150, 20, 7)

    println("----------------------------------")

    KotlinMountainBike.availableColors.forEach {print("$it ")}

    println("----------------------------------")
}

// 1. convert Bicycle class to kotlin
open class KotlinBicycle(var cadence: Int, var gear: Int, var speed: Int = 10) {
    fun applyBrake(decrement: Int) {
        speed -= decrement
    }

    fun speedUp(increment: Int) {
        speed += increment
    }

    open fun printDescription() {
        println("Bike is in gear: $gear  with a cadence of $cadence " +
                "travelling at a speed of $speed.")
    }
}

// 2. convert the MountainBike class to Kotlin
class KotlinMountainBike(var seatHeight: Int, cadence: Int, gear: Int, speed: Int = 10) :
        KotlinBicycle(cadence, gear, speed) {

    constructor(color: String, seatHeight: Int, cadence: Int, gear: Int, speed: Int = 10) :
            this(seatHeight, cadence, gear, speed) {
        println("The color is: $color")
    }

    override fun printDescription() {
        super.printDescription()
        println("The mountain bike has a seat height of: $seatHeight inches.")
    }

    // companion object. List<String> called available colors
    // initialize list to "blue", "red", "white", "black", "green", "brown"
    companion object {
        val availableColors: List<String> = listOf("blue", "red", "white", "black", "green", "brown")
    }

}

// 3. convert the RoadBike class to Kotlin
class KotlinRoadBike(val tireWidth: Int, cadence: Int, gear: Int, speed: Int = 10) :
        KotlinBicycle(cadence, gear, speed) {
    override fun printDescription() {
        super.printDescription()
        println("The mountain bike has a seat height of: $tireWidth MM.")
    }

}