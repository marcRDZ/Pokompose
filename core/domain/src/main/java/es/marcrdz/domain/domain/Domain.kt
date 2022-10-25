package es.marcrdz.domain.domain

sealed class ErrorDO {
    object Unknown : ErrorDO()
    object Network : ErrorDO()
    object NoData : ErrorDO()
    object Server : ErrorDO()
    data class Exception(val code: String, val msg: String) : ErrorDO()
}

sealed class ReferenceDO(val id: Int, val name: String) {
    class Pokemon(id: Int, name: String) : ReferenceDO(id, name)
    class Berry(id: Int, name: String) : ReferenceDO(id, name)
}

data class ReferencePageDO<T: ReferenceDO>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>
)