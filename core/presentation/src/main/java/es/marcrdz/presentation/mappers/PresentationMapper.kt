package es.marcrdz.presentation.mappers

import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.presentation.base.FailState

fun ErrorDO.toFailState(): FailState = when(this) {
    is ErrorDO.Exception -> FailState.Exception(type = code, msg = msg)
    ErrorDO.Network -> FailState.Network
    ErrorDO.NoData -> FailState.NoData
    ErrorDO.Server -> FailState.Server
    ErrorDO.Unknown -> FailState.Unknown
}
