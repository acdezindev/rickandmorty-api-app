package ac.pmdm.rickandmortypf

import ac.pmdm.rickandmortypf.databinding.FragmentDetallesBinding
import ac.pmdm.rickandmortypf.ui.PersonajeViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

/**
 * Fragmento que muestra los detalles completos de un episodio seleccionado
 * Incluye el nombre, código del episodio y la lista de personajes que aparecen
 */
class DetallesFragment : Fragment() {

    // ViewBinding para acceder a las vistas del layout
    private lateinit var binding: FragmentDetallesBinding

    // ViewModel para gestionar los datos de los personajes
    private val viewModel: PersonajeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflamos el layout con ViewBinding
        binding = FragmentDetallesBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Configurar el título de la toolbar
        (requireActivity() as AppCompatActivity)
            .supportActionBar
            ?.title = getString(R.string.detalles_tool)
        // Mostrar los datos del episodio recibidos por Bundle
        mostrarDatosDelEpisodio()
        // Configurar el RecyclerView en formato grid de 3 columnas para los personajes
        binding.recyclerViewPersonajes.layoutManager = GridLayoutManager(requireContext(), 3)
        // Observar los cambios en la lista de personajes y actualizar el RecyclerView
        viewModel.personajes.observe(viewLifecycleOwner) { listaPersonajes ->
            if (listaPersonajes.isNotEmpty()) {

                val personajesParaMostrar = listaPersonajes

                val adapter = PersonajeAdapter(personajesParaMostrar) { personaje ->
                }
                binding.recyclerViewPersonajes.adapter = adapter
            }
        }

        viewModel.loadPersonajes()

        binding.btnVolverDetalles.setOnClickListener {
            findNavController().navigate(R.id.listaEpisodiosFragment)
        }

    }

    /**
     * Extrae los datos del episodio del Bundle y los muestra en la interfaz
     */
    private fun mostrarDatosDelEpisodio() {

        val bundleRecibido = arguments

        if (bundleRecibido != null) {

            val nombre = bundleRecibido.getString("nombre") ?: "Sin nombre"
            val codigo = bundleRecibido.getString("codigo") ?: "Sin código"
            binding.detalleNombreContesta.text = nombre
            binding.detalleCodigoContestacion.text = codigo

        } else {
            binding.detalleNombreContesta.text = "No hay episodio seleccionado"
            binding.detalleCodigoContestacion.text = "---"
        }
    }
}