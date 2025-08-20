package com.example.pokeapp

data class PokemonResponse(
    val name: String,
    val sprites: Sprites
)

data class Sprites(
    val front_default: String?
)
