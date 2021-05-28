package edu.itesm.pokedex.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.itesm.pokedex.model.PokemonResponse
import edu.itesm.pokedex.model.PokemonResult
import edu.itesm.pokedex.service.PokemonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokedexViewModel() : ViewModel() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PokemonService = retrofit.create(PokemonService::class.java)

    val pokemonList = MutableLiveData<List<PokemonResult>>()

    fun getPokemonList(){
        val call = service.getListaPokemons(100,0)

        call.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                response.body()?.results?.let { list ->
                    pokemonList.postValue(list)
                }

            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                call.cancel()
            }

        })
    }
}