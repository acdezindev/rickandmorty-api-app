package ac.pmdm.rickandmortypf

import ac.pmdm.rickandmortypf.databinding.FragmentEstadisticasBinding
import ac.pmdm.rickandmortypf.ui.EpisodioViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels

/**
 * Fragmento que muestra las estadísticas de visualización del usuario
 * Incluye total de episodios, episodios vistos y porcentaje de progreso
 */
class EstadisticasFragment : Fragment() {

    // ViewBinding para acceder a las vistas del layout
    private lateinit var binding: FragmentEstadisticasBinding

    // ViewModel compartido con otros fragmentos para acceder a los datos
    private val viewModel: EpisodioViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout con ViewBinding
        binding = FragmentEstadisticasBinding.inflate(layoutInflater)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Calcular las estadísticas de visualización
        viewModel.calcularEstadisticas()
        // Observar los cambios en las estadisticas
        viewModel.estadisticas.observe(viewLifecycleOwner) { stats ->
            // Actualizar textos con los valores recogidos
            binding.tvTotalEpisodios.text = "${getString(R.string.total_episodios)}: ${stats.totalEpisodios}"
            binding.tvEpisodiosVistos.text = "${getString(R.string.episodios_vistos)}: ${stats.episodiosVistos}"
            binding.tvPorcentaje.text = "${stats.porcentaje.toInt()}% completado"
            // Actualizar ProgressBar circular
            binding.progressBar.progress = stats.porcentaje.toInt()
        }

        // Cambiar el titulo de la toolbar
        (requireActivity() as AppCompatActivity)
            .supportActionBar
            ?.title = getString(R.string.estadisticas_tool)
    }
}




