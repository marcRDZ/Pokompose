package es.marcrdz.presentation.mappers

import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.presentation.base.ErrorEvent

fun ErrorDO.toErrorEvent(): ErrorEvent = when(this) {
    is ErrorDO.Exception -> ErrorEvent.Exception(type = code, msg = msg)
    ErrorDO.Network -> ErrorEvent.Network
    ErrorDO.NoData -> ErrorEvent.NoData
    ErrorDO.Server -> ErrorEvent.Server
    ErrorDO.Unknown -> ErrorEvent.Unknown
}
