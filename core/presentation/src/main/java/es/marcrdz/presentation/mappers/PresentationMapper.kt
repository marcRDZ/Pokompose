package es.marcrdz.presentation.mappers

import es.marcrdz.domain.domain.DomainError
import es.marcrdz.presentation.base.ErrorState

fun DomainError.toErrorState(): ErrorState = when(this) {
    is DomainError.Exception -> ErrorState.Exception(type = code, msg = msg)
    DomainError.Network -> ErrorState.Network
    DomainError.NoData -> ErrorState.NoData
    DomainError.Server -> ErrorState.Server
    DomainError.Unknown -> ErrorState.Unknown
}