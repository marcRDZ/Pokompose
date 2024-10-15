/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.datasource.mappers

import arrow.core.Either
import es.marcrdz.domain.domain.*
import me.sargunvohra.lib.pokekotlin.model.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

val errorDO: (PokeApiError) -> ErrorDO = {
    when (it) {
        is HttpError -> when (it.code) {
            in 400..499 -> ErrorDO.NoData
            in 500..599 -> ErrorDO.Server
            else -> ErrorDO.Exception(code = "${it.code}", msg = it.body)
        }
        is NetworkError -> ErrorDO.Network
        is UnknownApiError -> ErrorDO.Unknown
    }
}

val typeRefDO: (NamedApiResource) -> TypeRefDO = {
    TypeRefDO(it.id, it.name)
}

val statRefDO: (NamedApiResource) -> StatRefDO = {
    StatRefDO(it.id, it.name)
}

val itemRefDO: (NamedApiResource) -> ItemRefDO = {
    ItemRefDO(it.id, it.name)
}

val versionRefDO: (NamedApiResource) -> VersionRefDO = {
    VersionRefDO(it.id, it.name)
}

val versionGroupRefDO: (NamedApiResource) -> VersionGroupRefDO = {
    VersionGroupRefDO(it.id, it.name)
}

val moveRefDO: (NamedApiResource) -> MoveRefDO = {
    MoveRefDO(it.id, it.name)
}

val moveLearnMethodRefDO: (NamedApiResource) -> MoveLearnMethodRefDO = {
    MoveLearnMethodRefDO(it.id, it.name)
}

val pokemonSpeciesRefDO: (NamedApiResource) -> PokemonRefDO.Species = {
    PokemonRefDO.Species(it.id, it.name)
}

val pokemonAbilityRefDO: (NamedApiResource) -> PokemonRefDO.Ability = {
    PokemonRefDO.Ability(it.id, it.name)
}

val pokemonFormsRefDO: (NamedApiResource) -> PokemonRefDO.Forms = {
    PokemonRefDO.Forms(it.id, it.name)
}

val pokemonAbilityDO: (PokemonAbility) -> PokemonAbilityDO = {
    PokemonAbilityDO(
        isHidden = it.isHidden,
        slot = it.slot,
        ability = pokemonAbilityRefDO(it.ability)
    )
}

val versionGameIndexDO: (VersionGameIndex) -> VersionGameIndexDO = {
    VersionGameIndexDO(
        gameIndex = it.gameIndex,
        version = versionRefDO(it.version)
    )
}

val pokemonHeldItemVersionDO: (PokemonHeldItemVersion) -> PokemonHeldItemVersionDO = {
    PokemonHeldItemVersionDO(
        version = versionRefDO(it.version),
        rarity = it.rarity
    )
}

val pokemonHeldItemDO: (PokemonHeldItem) -> PokemonHeldItemDO = {
    PokemonHeldItemDO(
        item = itemRefDO(it.item),
        versionDetails = it.versionDetails.map(pokemonHeldItemVersionDO)
    )
}

val pokemonMoveVersionDO: (PokemonMoveVersion) -> PokemonMoveVersionDO = {
    PokemonMoveVersionDO(
        moveLearnMethod = moveLearnMethodRefDO(it.moveLearnMethod),
        versionGroup = versionGroupRefDO(it.versionGroup),
        levelLearnedAt = it.levelLearnedAt
    )
}

val pokemonMoveDO: (PokemonMove) -> PokemonMoveDO = {
    PokemonMoveDO(
        move = moveRefDO(it.move),
        versionGroupDetails = it.versionGroupDetails.map(pokemonMoveVersionDO)
    )
}

val pokemonStatDO: (PokemonStat) -> PokemonStatDO = {
    PokemonStatDO(
        stat = statRefDO(it.stat),
        effort = it.effort,
        baseStat = it.baseStat
    )
}

val pokemonTypeDO: (PokemonType) -> PokemonTypeDO = {
    PokemonTypeDO(
        slot = it.slot,
        type = typeRefDO(it.type)
    )
}

val pokemonDO: (Pokemon) -> PokemonDO = {
    PokemonDO(
        id = it.id,
        name = it.name,
        baseExperience = it.baseExperience,
        height = it.height,
        isDefault = it.isDefault,
        order = it.order,
        weight = it.weight,
        species = pokemonSpeciesRefDO(it.species),
        abilities = it.abilities.map(pokemonAbilityDO),
        forms = it.forms.map(pokemonFormsRefDO),
        gameIndices = it.gameIndices.map(versionGameIndexDO),
        heldItems = it.heldItems.map(pokemonHeldItemDO),
        moves = it.moves.map(pokemonMoveDO),
        stats = it.stats.map(pokemonStatDO),
        types = it.types.map(pokemonTypeDO)
    )
}

fun <T : ReferenceDO> Either<PokeApiError, NamedApiResourceList>.toDomain(
    transform: (NamedApiResource) -> T
): Either<ErrorDO, ReferencePageDO<T>> = this.bimap(
    { errorDO(it) }, { it.toReferenceDO(transform) }
)

fun <T : ReferenceDO> NamedApiResourceList.toReferenceDO(
    transform: (NamedApiResource) -> T
): ReferencePageDO<T> {
    val nextPage = this.next?.toHttpUrlOrNull()
    return ReferencePageDO(
        count = this.count,
        offset = nextPage?.queryParameter("offset")?.toIntOrNull() ?: 0,
        limit = nextPage?.queryParameter("limit")?.toIntOrNull() ?: 0,
        results = this.results.map { transform(it) }
    )
}