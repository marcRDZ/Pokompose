package es.marcrdz.datasource.mappers

import arrow.core.Either
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.ReferenceDO
import es.marcrdz.domain.domain.ReferencePageDO
import me.sargunvohra.lib.pokekotlin.model.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

fun PokeApiError.toErrorDO(): ErrorDO = when (this) {
    is HttpError -> when (code) {
        in 400..499 -> ErrorDO.NoData
        in 500..599 -> ErrorDO.Server
        else -> ErrorDO.Exception(code = "$code", msg = body)
    }
    is NetworkError -> ErrorDO.Network
    is UnknownApiError -> ErrorDO.Unknown
}

fun <T : ReferenceDO> Either<PokeApiError, NamedApiResourceList>.toDomain(
    transform: (NamedApiResource) -> T
): Either<ErrorDO, ReferencePageDO<T>> = this.bimap(
    { it.toErrorDO() }, { it.toReferenceDO(transform) }
)

fun <T : ReferenceDO> NamedApiResourceList.toReferenceDO(
    transform: (NamedApiResource) -> T
): ReferencePageDO<T> {
    val nextPage = this.next?.toHttpUrlOrNull()
    return ReferencePageDO(
        count = this.count,
        offset = nextPage?.queryParameter("offset")?.toIntOrNull() ?: 0,
        limit = nextPage?.queryParameter("limit")?.toIntOrNull() ?: 0,
        results = this.results.map { transform(it) }
    )
}