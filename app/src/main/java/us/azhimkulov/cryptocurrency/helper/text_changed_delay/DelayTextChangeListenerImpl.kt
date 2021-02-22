package us.azhimkulov.cryptocurrency.helper.text_changed_delay

import io.reactivex.observers.DisposableObserver
import us.azhimkulov.domain.interactor.timer.TimerOptions
import us.azhimkulov.domain.interactor.timer.TimerUseCase
import us.azhimkulov.domain.interactor.timer.TimerUseCaseFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by azamat  on 2/22/21.
 */
class DelayTextChangeListenerImpl @Inject constructor(private val timerUseCaseFactory: TimerUseCaseFactory): DelayTextChangeListener {

    private var timerUseCase: TimerUseCase? = null
    private var afterDelay: (() -> Unit)? = null

    private val timerOptions = TimerOptions(TIMER_DELAY_IN_SECONDS, TIMER_UPDATE_IN_SECONDS, TimeUnit.MILLISECONDS)
    private var isTimerRunning = false
    private var actualTimeInSeconds = 0L

    companion object {
        private const val TIMER_DELAY_IN_SECONDS = 0L
        private const val TIMER_UPDATE_IN_SECONDS = 1L
        private const val TIME_OUT_IN_SECONDS = 700L
    }

    init {
        createTimer()
    }

    override fun textChanged(afterDelay: () -> Unit) {
        this.afterDelay = afterDelay
        disposeTimerUseCaseIfExists()
        createTimer()
        startTimer()
    }

    override fun interruptTimer() {
        stopTimer()
        afterDelay = null
    }

    private fun startTimer() {
        isTimerRunning = true
        timerUseCase?.execute(TimerSubscriber(), timerOptions)
    }

    private fun stopTimer() {
        isTimerRunning = false
        timerUseCase?.dispose()
    }

    private fun updateTime(value: Long) {
        if (!isTimerRunning) return

        actualTimeInSeconds = value
    }

    private fun disposeTimerUseCaseIfExists() {
        timerUseCase?.dispose()
    }

    private fun disposeAndInvokeIfTimeoutOccurred() {
        if (actualTimeInSeconds >= TIME_OUT_IN_SECONDS) {
            disposeTimerUseCaseIfExists()
            afterDelay?.invoke()
        }
    }

    private fun createTimer() {
        this.timerUseCase = timerUseCaseFactory.create()
    }

    private inner class TimerSubscriber: DisposableObserver<Long>() {
        override fun onNext(t: Long) {
            updateTime(t)
            disposeAndInvokeIfTimeoutOccurred()
        }

        override fun onError(e: Throwable) {

        }

        override fun onComplete() {

        }
    }
}