package edu.itesm.pokedex.view.detalle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import edu.itesm.pokedex.databinding.ActivityMainBinding
import edu.itesm.pokedex.databinding.ActivityPokemonBinding
import edu.itesm.pokedex.viewmodel.PokemonViewModel

class PokemonActivity : AppCompatActivity() {

    lateinit var viewModel: PokemonViewModel
    private lateinit var binding: ActivityPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        initUI()
    }

        private fun initUI(){
            val id = intent.extras?.get("id") as Int
            viewModel.getPokemonInfo(id)

            viewModel.pokemonInfo.observe(this, Observer { pokemon ->
                binding.nombreTV.text = pokemon.name
                binding.alturaTV.text = "Altura: ${pokemon.height/10.0}m"
                binding.pesoTV.text = "Peso: ${pokemon.weight/10.0}"

                Glide.with(this)
                    .load(pokemon.sprites.front_default)
                    .circleCrop()
                    .into(binding.imageView)
            })
        }

    }