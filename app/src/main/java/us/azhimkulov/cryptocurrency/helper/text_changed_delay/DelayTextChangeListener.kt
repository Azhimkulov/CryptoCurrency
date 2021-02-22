package us.azhimkulov.cryptocurrency.helper.text_changed_delay

/**
 * Created by azamat  on 2/22/21.
 */
interface DelayTextChangeListener {
    fun textChanged(afterDelay:() -> Unit)
    fun interruptTimer()
}