package ac.pmdm.rickandmortypf

import ac.pmdm.rickandmortypf.data.model.Episodio
import ac.pmdm.rickandmortypf.databinding.FragmentListaEpisodiosBinding
import ac.pmdm.rickandmortypf.ui.EpisodioViewModel
import ac.pmdm.rickandmortypf.ui.PersonajeViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlin.getValue


/**
 * Fragmento principal que muestra la lista de episodios
 * Gestiona la visualización y filtrado de episodios
 */

class ListaEpisodiosFragment : Fragment() {

    // ViewModel para gestionar los episodios (compartido con otros fragments)
    private lateinit var binding: FragmentListaEpisodiosBinding

    // Adaptador para el RecyclerView
    private val EpisodiosViewModel: EpisodioViewModel by activityViewModels() // activityViewModels() en vez de viewModels() pq compartimos datos entre fragmentos

    // ViewModel para los personajes
    private lateinit var adapter: EpisodioAdapter

    private val personajeViewModel: PersonajeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout con ViewBinding
        binding = FragmentListaEpisodiosBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar el Adaptador con los callbacks
        adapter = EpisodioAdapter(
            listaEpisodios = emptyList(),
            onClick = { episodioSeleccionado ->
                navegarADetalles(episodioSeleccionado)
            },
            onVistocambio = { episodio, isChecked ->
                // Guardar en Firestore cuando se marca/desmarca un episodio
                EpisodiosViewModel.guardarEpisodioVisto(episodio)
            }
        )

        // Configurar el RecyclerView
        binding.recyclerViewEpisodes.adapter = adapter
        binding.recyclerViewEpisodes.layoutManager = LinearLayoutManager(requireContext())

        // Observar los cambios en la lista de episodios
        EpisodiosViewModel.nombre.observe(viewLifecycleOwner) { listaEpisodios ->
            // Actualizar el adaptador con la nueva lista
            EpisodiosViewModel.nombre.observe(viewLifecycleOwner) { listaEpisodios ->
                adapter = EpisodioAdapter(
                    listaEpisodios = listaEpisodios,
                    onClick = { episodio ->
                        navegarADetalles(episodio)
                    },
                    onVistocambio = { episodio, isChecked ->
                        EpisodiosViewModel.guardarEpisodioVisto(episodio)
                    }
                )
                binding.recyclerViewEpisodes.adapter = adapter
            }
            binding.recyclerViewEpisodes.adapter = adapter
        }

        // Cargar los episodios desde la API
        EpisodiosViewModel.loadEpisodios()

        // Botón para filtrar episodios vistos
        binding.btnVistos.setOnClickListener {

            EpisodiosViewModel.cargarEpisodiosVistos()

            (requireActivity() as AppCompatActivity)
                .supportActionBar
                ?.title = getString(R.string.lista_tool_firestore)
        }

        // Botón para mostrar todos los episodios
        binding.btnTodos.setOnClickListener {

            EpisodiosViewModel.loadEpisodios()
        }
        (requireActivity() as AppCompatActivity)
            .supportActionBar
            ?.title = getString(R.string.lista_tool)

    }


    /**
     * Navega al fragmento de detalles del episodio seleccionado
     * @param episodio Episodio seleccionado
     */
    private fun navegarADetalles(episodio: Episodio) {

        val bundle = Bundle().apply {
            putString("nombre", episodio.name)
            putString("codigo", episodio.episode)
        }


        findNavController().navigate(
            R.id.action_listaEpisodiosFragment_to_detallesFragment,
            bundle
        )
    }
}

