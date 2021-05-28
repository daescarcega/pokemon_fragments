package edu.itesm.pokedex.view.detalle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import edu.itesm.pokedex.R
import edu.itesm.pokedex.databinding.ActivityMainBinding
import edu.itesm.pokedex.databinding.FragmentPokedexBinding
import edu.itesm.pokedex.databinding.FragmentPokemonBinding
import edu.itesm.pokedex.viewmodel.PokemonViewModel


class PokemonFragment : Fragment() {

    lateinit var viewModel: PokemonViewModel
    private lateinit var binding: FragmentPokemonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPokemonBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }
    private fun initUI(){

        val args = navArgs<PokemonFragmentArgs>()
        val id = args.value.id
        viewModel.getPokemonInfo(id)

            viewModel.pokemonInfo.observe(this, Observer { pokemon ->
            binding.nombreTV.text = pokemon.name
            binding.alturaTV.text = "Altura: ${pokemon.height/10.0}m"
            binding.pesoTV.text = "Peso: ${pokemon.weight/10.0}"

            Glide.with(this)
                .load(pokemon.sprites.front_default)
                .into(binding.imageView)
        })
    }
}