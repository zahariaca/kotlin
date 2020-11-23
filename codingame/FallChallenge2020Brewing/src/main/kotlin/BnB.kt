import java.util.*

fun main() {
    val W = 10
    val arr = arrayOf(
        Item(2.toFloat(), 40),
        Item(3.14.toFloat(), 50),
        Item(1.98.toFloat(), 100),
        Item(5.toFloat(), 95),
        Item(3.toFloat(), 30)
    )

    val n = arr.size
    println(n)

    println("Maximum possible profit: ${knapsack(W, arr, n)}")
}

data class Item(var weight: Float, var value: Int) {}
data class Node(var level: Int, var profit: Int, var bound: Int, var weight: Float) {}

fun cmp(a: Item, b: Item): Boolean {
    val r1: Double = a.value.toDouble() / a.weight.toDouble()
    val r2: Double = b.value.toDouble() / b.weight.toDouble()
    return r1 > r2;
}

fun bound(u: Node, n: Int, W: Int, arr: Array<Item>): Int {
    if (u.weight >= W) {
        return 0
    }

    var profitBound: Int = u.profit

    var j: Int = u.level + 1
    var totWeight: Int = u.weight.toInt()

    while ((j < n) && (totWeight + arr[j].weight <= W)) {
        totWeight += arr[j].weight.toInt()
        profitBound += arr[j].value
        j++
    }

    if (j < n) {
        profitBound += (W - totWeight) * arr[j].value / arr[j].weight.toInt()
    }

    return profitBound
}

fun knapsack(W: Int, arr: Array<Item>, n: Int): Int {

    // sort(arr, arr + n, cmp);
    arr.sortBy { it.value.toDouble() / it.weight.toDouble() }

    val Q: Queue<Node> = LinkedList<Node>()
    var u: Node = Node(-1, 0, 0, 0.toFloat())
    var v: Node = Node(-1, 0, 0, 0.toFloat())

    Q.add(u)
    var maxProfit = 0;

    while (Q.isNotEmpty()) {
        u = Q.remove()

        if (u.level == -1) {
            v.level = 0
        }

        if (u.level == n - 1) {
            continue
        }

        v.level = u.level + 1

        v.weight = u.weight + arr[v.level].weight
        v.profit = u.profit + arr[v.level].value

        if (v.weight <= W && v.profit > maxProfit) {
            maxProfit = v.profit
        }

        v.bound = bound(v, n, W, arr)

        if (v.bound > maxProfit) {
            Q.add(v)
        }

        v.weight = u.weight
        v.profit = u.profit
        v.bound = bound(v, n, W, arr)
        if (v.bound > maxProfit) {
            Q.add(v)
        }
    }

    return maxProfit
}