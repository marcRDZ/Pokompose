package es.marcrdz.domain

import arrow.core.Either
import es.marcrdz.domain.domain.DomainError
import es.marcrdz.domain.domain.DomainReference
import es.marcrdz.domain.domain.DomainReferencePage

interface DomainContract {

    interface PokemonRepository {
        suspend fun fetchPokemonReferences(offset: Int = 0, limit: Int = 0): Either<DomainError, DomainReferencePage<DomainReference.Pokemon>>
    }
}