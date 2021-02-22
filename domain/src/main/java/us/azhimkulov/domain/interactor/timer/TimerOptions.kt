package us.azhimkulov.domain.interactor.timer

import java.util.concurrent.TimeUnit

/**
 * Created by azamat  on 2/22/21.
 */
class TimerOptions(val delay: Long,
                   val updatePeriod: Long,
                   val timeUnit: TimeUnit
)