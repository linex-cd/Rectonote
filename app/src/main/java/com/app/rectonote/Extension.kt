package com.app.rectonote

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlin.math.sqrt


suspend fun <A, B> Iterable<A>.pmap(f: suspend (A) -> B): List<B> = coroutineScope {
    map { async { f(it) } }.awaitAll()
}

//first time extension function
fun String.containsIllegalCharacters(): Boolean {
    val regex = "[!$%^&*\\\\+|@~=`{}\\[\\]:\";'<>?,\\/]".toRegex()
    return regex.containsMatchIn(this)
}

fun correlationCoefficient(X: DoubleArray, Y: Array<Int>): Double {
    val size =
        if (X.size == Y.size) X.size else throw IllegalArgumentException("Array size is not Equal")
    var sumX = 0.00
    var sumY = 0.00
    var sumXY = 0.00
    var sqsumX = 0.00
    var sqsumY = 0.00
    for (i in 0 until size) {
        sumX += X[i]
        sumY += Y[i]
        sumXY += X[i] * Y[i]
        sqsumX += X[i] * X[i]
        sqsumY += Y[i] * Y[i]
    }
    return (size * sumXY - sumX * sumY) / sqrt((size * sqsumX - sumX * sumX) * (size * sqsumY - sumY * sumY))
}

fun <T> Array<T>.leftShift(d: Int): Array<T> {
    val newList = this.copyOf()
    var shift = d
    if (shift > size) shift %= size
    forEachIndexed { index, value ->
        val newIndex = (index + (size - shift)) % size
        newList[newIndex] = value
    }
    return newList
}


