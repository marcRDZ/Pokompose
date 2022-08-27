package es.marcrdz.presentation.mappers

import arrow.core.Either
import es.marcrdz.domain.domain.DomainError
import es.marcrdz.domain.domain.DomainReference
import es.marcrdz.domain.domain.DomainReferencePage
import es.marcrdz.presentation.base.ErrorReport
import es.marcrdz.presentation.domain.PresentationReference
import es.marcrdz.presentation.domain.PresentationReferencePage

fun DomainError.toErrorReport(): ErrorReport = when(this) {
    is DomainError.Exception -> ErrorReport.Exception(type = code, msg = msg)
    DomainError.Network -> ErrorReport.Network
    DomainError.NoData -> ErrorReport.NoData
    DomainError.Server -> ErrorReport.Server
    DomainError.Unknown -> ErrorReport.Unknown
}

fun <T : DomainReference, S: PresentationReference> Either<DomainError, DomainReferencePage<T>>.toPresentation(
    transform: (T) -> S
): Either<ErrorReport, PresentationReferencePage<S>> = this.bimap(
    { it.toErrorReport() }, { it.toReference(transform) }
)

fun <T : DomainReference, S: PresentationReference> DomainReferencePage<T>.toReference(transform: (T) -> S) = PresentationReferencePage(
    count = this.count,
    next = this.next,
    previous = this.previous,
    results = this.results.map { transform(it) }
)