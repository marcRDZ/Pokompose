package es.marcrdz.presentation.mappers

import arrow.core.Either
import es.marcrdz.domain.domain.DomainError
import es.marcrdz.domain.domain.DomainReference
import es.marcrdz.domain.domain.DomainReferencePage
import es.marcrdz.presentation.base.ErrorEvent
import es.marcrdz.presentation.domain.PresentationReference
import es.marcrdz.presentation.domain.PresentationReferencePage

fun DomainError.toErrorReport(): ErrorEvent = when(this) {
    is DomainError.Exception -> ErrorEvent.Exception(type = code, msg = msg)
    DomainError.Network -> ErrorEvent.Network
    DomainError.NoData -> ErrorEvent.NoData
    DomainError.Server -> ErrorEvent.Server
    DomainError.Unknown -> ErrorEvent.Unknown
}

fun <T : DomainReference, S: PresentationReference> Either<DomainError, DomainReferencePage<T>>.toPresentation(
    transform: (T) -> S
): Either<ErrorEvent, PresentationReferencePage<S>> = this.bimap(
    { it.toErrorReport() }, { it.toReference(transform) }
)

fun <T : DomainReference, S: PresentationReference> DomainReferencePage<T>.toReference(transform: (T) -> S) = PresentationReferencePage(
    count = this.count,
    next = this.next,
    previous = this.previous,
    results = this.results.map { transform(it) }
)