package com.example.pokeapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {
    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: Int): Call<PokemonResponse>
}
