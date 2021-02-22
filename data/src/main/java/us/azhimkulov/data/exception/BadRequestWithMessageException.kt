package us.azhimkulov.data.exception

import java.lang.Exception

/**
 * Created by azamat  on 2/22/21.
 */
class BadRequestWithMessageException(val errorMessage: String): Exception(errorMessage)