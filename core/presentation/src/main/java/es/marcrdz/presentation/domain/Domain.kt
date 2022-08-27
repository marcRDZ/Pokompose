package es.marcrdz.presentation.domain

import es.marcrdz.domain.domain.ReferencePage

sealed class PresentationReference(val id: Int, val name: String) {
    class Pokemon(id: Int, name: String) : PresentationReference(id, name)
    class Berry(id: Int, name: String) : PresentationReference(id, name)
}

data class PresentationReferencePage<T: PresentationReference>(
    override val count: Int,
    override val next: String?,
    override val previous: String?,
    override val results: List<T>
) : ReferencePage<T>
