package us.azhimkulov.domain.interactor.timer

import us.azhimkulov.domain.executor.PostExecutionThread
import us.azhimkulov.domain.executor.ThreadExecutor
import javax.inject.Inject

/**
 * Created by azamat  on 2/22/21.
 */
class TimerUseCaseFactory @Inject constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    fun create(): TimerUseCase {
        return TimerUseCase(threadExecutor, postExecutionThread)
    }
}