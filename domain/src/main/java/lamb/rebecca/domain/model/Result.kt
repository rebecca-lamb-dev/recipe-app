package lamb.rebecca.domain.model

sealed class Result<out Type : Any> {

    inline fun onSuccess(action: (Type) -> Unit): Result<Type> {
        if (this is Success) action(result)
        return this
    }

    inline fun onFailure(action: (error: Error) -> Unit): Result<Type> {
        if (this is Failure) action(error)
        return this
    }

}

data class Success<out Type : Any>(val result: Type) : Result<Type>()

data class Failure(val error: Error) : Result<Nothing>()
