package us.azhimkulov.domain.executor

import io.reactivex.Scheduler

/**
 * Created by azamat  on 2/21/21.
 */
interface PostExecutionThread {
    fun getScheduler(): Scheduler
}