package cz.maio.femo

import java.util.concurrent.atomic.AtomicReference

fun <T> AtomicReference<T>.swap(fn: T.() -> T) {
    updateAndGet(fn)
}

fun <T, R> AtomicReference<T>.use(fn: T.() -> R): R {
    return get().let(fn)
}
