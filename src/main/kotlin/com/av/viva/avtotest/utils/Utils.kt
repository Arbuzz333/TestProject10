@file:JvmName("Utils")
@file:JvmMultifileClass


val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
val digitsPool : List<Char> = ('0'..'9').distinct()

fun randomString(length: Int): String {
    val randomString = (1..length)
        .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
    return randomString
}

fun randomDigits(length: Int): Long {
    val randomString = (1..length)
        .map { i -> kotlin.random.Random.nextInt(0, digitsPool.size) }
        .map(digitsPool::get)
        .joinToString("")
        .toLong()
    return randomString
}

fun randomDouble(length: Int): Double {
    return randomDigits(length).toDouble().div(100)
}

fun randomDigitsStr(length: Int): String {
    return randomDigits(length).toString()
}