package us.azhimkulov.cryptocurrency

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import us.azhimkulov.domain.executor.PostExecutionThread
import javax.inject.Inject

/**
 * Created by azamat  on 2/21/21.
 */
class UIThread @Inject constructor() : PostExecutionThread {
    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}