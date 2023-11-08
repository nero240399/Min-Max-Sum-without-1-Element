fun main() {
    val numList: List<Int> = try {
        readln().trim().split(Regex("\\s+")).map {
            it.toInt().also { num -> if (num < 0) throw NegativeNumberException }
        }.also { if (it.size == 1) throw OnlyOneElementException }
    } catch (e: Exception) {
        when (e) {
            is OnlyOneElementException -> e.message
            is NumberFormatException, NegativeNumberException -> "The input must contain only positive integer numbers."
            else -> "The input is invalid."
        }.let(::println)
        return
    }

    var (min, max, sum) = List(3) { 0L }
    val (oddList, evenList) = List(2) { mutableListOf<Int>() }

    for (i in numList) {
        if (i < min) min = i.toLong()
        if (i > max) max = i.toLong()
        if (i % 2 == 0) evenList.add(i) else oddList.add(i)
        sum += i
    }

    println("${sum - max} ${sum - min}").also { println() }
    println("BONUS")
    println("total of array: $sum")
    println("min in array: $min")
    println("max in array: $max")
    println("even elements in array: $evenList")
    println("odd elements in array: $oddList")
}

private object OnlyOneElementException : Exception("The input must contain more than 1 number.") {
    private fun readResolve(): Any = OnlyOneElementException
}

private object NegativeNumberException : Exception() {
    private fun readResolve(): Any = OnlyOneElementException
}