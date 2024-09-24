/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.domain.domain

sealed class ErrorDO {
    data object Unknown : ErrorDO()
    data object Network : ErrorDO()
    data object NoData : ErrorDO()
    data object Server : ErrorDO()
    data class Exception(val code: String, val msg: String) : ErrorDO()
}

interface ReferenceDO {
    val id: Int
    val name: String
}

data class SpriteDO(val url: String)

data class ReferencePageDO<T: ReferenceDO>(
    val count: Int,
    val offset: Int,
    val limit: Int,
    val results: List<T>
)

sealed class PokemonRefDO(
    override val id: Int,
    override val name: String
) : ReferenceDO {

    class Entity(
        id: Int,
        name: String,
        val imgUri: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
    ) : PokemonRefDO(id, name)

    class Species(
        id: Int,
        name: String
    ) : PokemonRefDO(id, name)

    class Forms(
        id: Int,
        name: String
    ) : PokemonRefDO(id, name)
    class Ability(
        id: Int,
        name: String
    ) : PokemonRefDO(id, name)
}

data class BerryRefDO(
    override val id: Int,
    override val name: String
) : ReferenceDO

data class ItemRefDO(
    override val id: Int,
    override val name: String
) : ReferenceDO

data class VersionRefDO(
    override val id: Int,
    override val name: String
) : ReferenceDO

data class VersionGroupRefDO(
    override val id: Int,
    override val name: String
) : ReferenceDO

data class StatRefDO(
    override val id: Int,
    override val name: String
) : ReferenceDO

data class TypeRefDO(
    override val id: Int,
    override val name: String
) : ReferenceDO

data class MoveRefDO(
    override val id: Int,
    override val name: String
) : ReferenceDO

data class MoveLearnMethodRefDO(
    override val id: Int,
    override val name: String
) : ReferenceDO

data class PokemonHeldItemDO(
    val item: ItemRefDO,
    val versionDetails: List<PokemonHeldItemVersionDO>
)

data class PokemonHeldItemVersionDO(
    val version: VersionRefDO,
    val rarity: Int
)

data class PokemonTypeDO(
    val slot: Int,
    val type: TypeRefDO
)

data class PokemonAbilityDO(
    val isHidden: Boolean,
    val slot: Int,
    val ability: PokemonRefDO.Ability
)

data class VersionGameIndexDO(
    val gameIndex: Int,
    val version: VersionRefDO
)

data class PokemonStatDO(
    val stat: StatRefDO,
    val effort: Int,
    val baseStat: Int
)

data class PokemonMoveDO(
    val move: MoveRefDO,
    val versionGroupDetails: List<PokemonMoveVersionDO>
)

data class PokemonMoveVersionDO(
    val moveLearnMethod: MoveLearnMethodRefDO,
    val versionGroup: VersionGroupRefDO,
    val levelLearnedAt: Int
)

data class PokemonSpritesDO(
    val backDefault: SpriteDO,
    val backShiny: SpriteDO,
    val frontDefault: SpriteDO,
    val frontShiny: SpriteDO,
    val backFemale: SpriteDO,
    val backShinyFemale: SpriteDO,
    val frontFemale: SpriteDO,
    val frontShinyFemale: SpriteDO
)

data class PokemonDO(
    val id: Int,
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val isDefault: Boolean,
    val order: Int,
    val weight: Int,
    val species: PokemonRefDO.Species,
    val abilities: List<PokemonAbilityDO>,
    val forms: List<PokemonRefDO.Forms>,
    val gameIndices: List<VersionGameIndexDO>,
    val heldItems: List<PokemonHeldItemDO>,
    val moves: List<PokemonMoveDO>,
    val stats: List<PokemonStatDO>,
    val types: List<PokemonTypeDO>,
    val sprites: PokemonSpritesDO
)