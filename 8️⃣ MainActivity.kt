package com.example.pokeapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var pokemonImageView: ImageView
    private lateinit var loadPokemonButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonImageView = findViewById(R.id.duckImageView)
        loadPokemonButton = findViewById(R.id.loadDuckButton)

        loadPokemonButton.setOnClickListener {
            val randomId = Random.nextInt(1, 1026)
            carregarPokemon(randomId)
        }
    }

    private fun carregarPokemon(id: Int) {
        ApiCliente.instance.getPokemon(id).enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                if (response.isSuccessful) {
                    val pokemon = response.body()
                    val imageUrl = pokemon?.sprites?.front_default
                    if (!imageUrl.isNullOrEmpty()) {
                        Picasso.get().load(imageUrl).into(pokemonImageView)
                    } else {
                        Toast.makeText(this@MainActivity, "Imagem não disponível", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Erro ao carregar Pokémon", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Falha: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
