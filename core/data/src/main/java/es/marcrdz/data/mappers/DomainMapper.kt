package es.marcrdz.data.mappers

import arrow.core.Either
import es.marcrdz.data.domain.DataError
import es.marcrdz.data.domain.DataReference
import es.marcrdz.data.domain.DataReferencePage
import es.marcrdz.domain.domain.DomainError
import es.marcrdz.domain.domain.DomainReference
import es.marcrdz.domain.domain.DomainReferencePage

fun DataError.toDomainError(): DomainError = when (this) {
    DataError.Network -> DomainError.Network
    DataError.NoData -> DomainError.NoData
    DataError.Server -> DomainError.Server
    DataError.Unknown -> DomainError.Unknown
    is DataError.Exception -> DomainError.Exception(code, msg)
}

fun <T : DataReference, S: DomainReference> Either<DataError, DataReferencePage<T>>.toDomain(
    transform: (T) -> S
): Either<DomainError, DomainReferencePage<S>> = this.bimap(
    { it.toDomainError() }, { it.toReference(transform) }
)

fun <T : DataReference, S: DomainReference> DataReferencePage<T>.toReference(transform: (T) -> S) = DomainReferencePage(
    count = this.count,
    next = this.next,
    previous = this.previous,
    results = this.results.map { transform(it) }
)