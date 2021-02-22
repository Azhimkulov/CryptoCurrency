package us.azhimkulov.cryptocurrency.helper.text_changed_delay.factory

import us.azhimkulov.cryptocurrency.helper.text_changed_delay.DelayTextChangeListener
import us.azhimkulov.cryptocurrency.helper.text_changed_delay.DelayTextChangeListenerImpl
import us.azhimkulov.domain.interactor.timer.TimerUseCaseFactory
import javax.inject.Inject

/**
 * Created by azamat  on 2/22/21.
 */
class DelayTextChangeListenerFactory @Inject constructor
    (private val timerUseCaseFactory: TimerUseCaseFactory) {

    fun create(): DelayTextChangeListener {
        return DelayTextChangeListenerImpl(timerUseCaseFactory)
    }
}