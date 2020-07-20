package lamb.rebecca.data.network.model

interface Entity<T> {
    fun toDomain(): T
}