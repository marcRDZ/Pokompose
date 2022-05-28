package es.marcrdz.datasource.mappers

import arrow.core.Either
import es.marcrdz.data.domain.DataError
import es.marcrdz.data.domain.DataReference
import es.marcrdz.data.domain.DataReferencePage
import me.sargunvohra.lib.pokekotlin.model.*

fun PokeApiError.toDataError(): DataError = when (this) {
    is HttpError -> when (code) {
        in 400..499 -> DataError.NoData
        in 500..599 -> DataError.Server
        else -> DataError.Exception(code = "$code", msg = body)
    }
    is NetworkError -> DataError.Network
    is UnknownApiError -> DataError.Unknown
}

fun <T : DataReference> Either<PokeApiError, NamedApiResourceList>.toData(
    transform: (NamedApiResource) -> T
): Either<DataError, DataReferencePage<T>> = this.bimap(
    { it.toDataError() }, { it.toReference(transform) }
)

fun <T : DataReference> NamedApiResourceList.toReference(transform: (NamedApiResource) -> T) = DataReferencePage(
    count = this.count,
    next = this.next,
    previous = this.previous,
    results = this.results.map { transform(it) }
)