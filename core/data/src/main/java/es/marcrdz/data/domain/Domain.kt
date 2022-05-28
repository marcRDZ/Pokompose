package es.marcrdz.data.domain

import es.marcrdz.domain.domain.ReferencePage

sealed class DataError {
    object Unknown : DataError()
    object Network : DataError()
    object NoData : DataError()
    object Server : DataError()
    data class Exception(val code: String, val msg: String) : DataError()
}

sealed class DataReference(val id: Int, val name: String) {
    class Pokemon(id: Int, name: String) : DataReference(id, name)
    class Berry(id: Int, name: String) : DataReference(id, name)
}

data class DataReferencePage<T: DataReference>(
    override val count: Int,
    override val next: String?,
    override val previous: String?,
    override val results: List<T>
) : ReferencePage<T>