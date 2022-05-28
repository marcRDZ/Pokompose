package es.marcrdz.domain.domain

sealed class DomainError {
    object Unknown : DomainError()
    object Network : DomainError()
    object NoData : DomainError()
    object Server : DomainError()
    data class Exception(val code: String, val msg: String) : DomainError()
}

sealed class DomainReference(val id: Int, val name: String) {
    class Pokemon(id: Int, name: String) : DomainReference(id, name)
    class Berry(id: Int, name: String) : DomainReference(id, name)
}

data class DomainReferencePage<T: DomainReference>(
    override val count: Int,
    override val next: String?,
    override val previous: String?,
    override val results: List<T>
) : ReferencePage<T>

interface ReferencePage<out T> {
    val count: Int
    val next: String?
    val previous: String?
    val results: List<T>
}