import java.time.LocalDate

class DSHH {
    private var goods: Array<Goods?> = arrayOfNulls(10)
    var size: Int = 0
        private set

    fun add(item: Goods): Boolean {
        for (i in 0 until size) {
            if (goods[i]!!.code == item.code) return false
        }
        if (size == goods.size) goods = goods.copyOf(goods.size * 2)
        goods[size++] = item
        return true
    }

    fun displayAll(today: LocalDate) {
        if (size == 0) println("Inventory is empty.")
        for (i in 0 until size) println(goods[i]!!.getInformation(today))
    }

    fun getTotalQuantity(type: String): Long {
        var total = 0L
        for (i in 0 until size) {
            val item = goods[i]!!
            if (item.type == type) total += item.quantity
        }
        return total
    }

    fun getTotalVat(type: String): Double {
        var total = 0.0
        for (i in 0 until size) {
            val item = goods[i]!!
            if (item.type == type) total += item.vatAmount
        }
        return total
    }

    fun displaySummary() {
        for (type in arrayOf("Food", "Electronics", "Crockery")) {
            println("%s | Total quantity: %d | Total VAT: %.2f".format(
                type, getTotalQuantity(type), getTotalVat(type)
            ))
        }
    }
}
