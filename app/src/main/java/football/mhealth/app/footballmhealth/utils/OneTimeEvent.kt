package football.mhealth.app.footballmhealth.utils

interface EventHandler<out T> {

    fun handleEvent(action: (T) -> Unit)
}

data class OneTimeEvent<out T>(
    private var event: T?
) : EventHandler<T> {

    override fun handleEvent(action: (T) -> Unit) = event?.let(action).also { event = null } ?: Unit
}
