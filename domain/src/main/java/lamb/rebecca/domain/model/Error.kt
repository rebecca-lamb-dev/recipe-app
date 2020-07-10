package lamb.rebecca.domain.model

sealed class Error

data class HttpError(val code: Int, val message: String) : Error()

object InvalidDataError : Error()