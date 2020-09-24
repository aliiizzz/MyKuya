package ir.aliiz.base

sealed class Loadable<out T> {
    object Loading: Loadable<Nothing>()
    data class Loaded<out T>(val data: T): Loadable<T>()
    data class Failed(val throwable: Throwable): Loadable<Nothing>()
}