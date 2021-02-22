package us.azhimkulov.domain.interactor.timer

import io.reactivex.Observable
import us.azhimkulov.domain.executor.PostExecutionThread
import us.azhimkulov.domain.executor.ThreadExecutor
import us.azhimkulov.domain.interactor.UseCase

/**
 * Created by azamat  on 2/22/21.
 */
class TimerUseCase (
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<Long, TimerOptions>(threadExecutor, postExecutionThread) {


    override fun buildUseCaseObservable(params: TimerOptions?): Observable<Long> {
        return Observable.interval(
            params!!.delay,
            params.updatePeriod,
            params.timeUnit
        )
    }
}