package es.marcrdz.presentation.base

sealed class ViewState<out T : Report>

class BackgroundState(val report: BackgroundReport) : ViewState<Nothing>()
class FailState(val report: ErrorReport) : ViewState<Nothing>()
class StateChange<out T : Report>(val report: T) : ViewState<T>()

open class Report

sealed class BackgroundReport : Report() {
    object Idle : BackgroundReport()
    object Loading : BackgroundReport()
}

sealed class ErrorReport : Report() {
    object Unknown : ErrorReport()
    object NoData : ErrorReport()
    object Network : ErrorReport()
    object Server : ErrorReport()
    class Exception(type: String, msg: String) : ErrorReport()
}