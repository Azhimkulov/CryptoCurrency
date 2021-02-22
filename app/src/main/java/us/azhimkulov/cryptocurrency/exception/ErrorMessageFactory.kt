package us.azhimkulov.cryptocurrency.exception

import us.azhimkulov.data.exception.BadRequestWithMessageException
import java.net.UnknownHostException

/**
 * Created by azamat  on 2/22/21.
 */
class ErrorMessageFactory {

    companion object {
        fun create(throwable: Throwable): String? {
            if (throwable is BadRequestWithMessageException) {
                return throwable.errorMessage
            }

            return null
        }
    }
}