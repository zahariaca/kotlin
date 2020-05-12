fun main() {
    val root = TreeNode(1)
    val left = TreeNode(2)
    val right = TreeNode(3)
    root.left = left
    root.right = right
    val left2 = TreeNode(4)
    val right2 = TreeNode(5)
    left.left = left2
    right.right = right2

    val solution = Solution()
    println(solution.isCousins(root, 4, 5))
}

class Solution {
    private var xParent: TreeNode? = null
    private var yParent: TreeNode? = null
    private var xDepth = -1
    private var yDepth = -1

    fun isCousins(root: TreeNode?, x: Int, y: Int): Boolean {
        getDepthAndParent(root, x, y, 0, null)
        return xDepth == yDepth && xParent != null && yParent != null && xParent != yParent
    }

    private fun getDepthAndParent(root: TreeNode?, x: Int, y: Int, depth: Int, parent: TreeNode?) {
        if (root == null) {
            return
        }

        when (root.`val`) {
            x -> { xParent = parent; xDepth = depth }
            y -> { yParent = parent; yDepth = depth }
            else -> {
                getDepthAndParent(root.left, x, y, depth + 1, root)
                getDepthAndParent(root.right, x, y, depth + 1, root)
            }
        }
    }
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
    override fun toString(): String {
        return "TreeNode(`val`=$`val`, left=$left, right=$right)"
    }
}