package edu.itesm.pokedex.view.lista

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import edu.itesm.pokedex.databinding.FragmentPokedexBinding
import edu.itesm.pokedex.view.detalle.PokemonActivity
import edu.itesm.pokedex.view.detalle.PokemonFragment
import edu.itesm.pokedex.viewmodel.PokedexViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [PokedexFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokedexFragment : Fragment() {

    private lateinit var binding: FragmentPokedexBinding
    private lateinit var viewModel: PokedexViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentPokedexBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PokedexViewModel::class.java)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        creaUI()
    }
    private fun creaUI() {

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        val navController = Navigation.findNavController(requireView()!!)
        binding.recyclerView.adapter = PokedexAdapter{
            val action = PokedexFragmentDirections.actionPokedexFragmentToPokemonFragment()
            action.id = it
            navController.navigate(action)

        }
        viewModel.getPokemonList()

        viewModel.pokemonList.observe(requireActivity()!!, Observer { list ->
            (binding.recyclerView.adapter as PokedexAdapter).setData(list)
        })

    }

}