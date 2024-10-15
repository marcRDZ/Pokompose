/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.ui.domain


import kotlinx.serialization.Serializable


@Serializable
sealed class Destiny(
    val path: String
) {
    @Serializable
    data object PokeRefs: Destiny("poke_refs")
    @Serializable
    data class Pokemon(
        val id: Int,
        val name: String
    ): Destiny("pokemon")
}