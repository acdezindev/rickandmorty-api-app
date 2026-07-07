package ac.pmdm.rickandmortypf
import ac.pmdm.rickandmortypf.databinding.FragmentAcercaDeBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController

/**
 * Fragmento que muestra información sobre la aplicación
 */
class AcercaDeFragment : Fragment() {


    // ViewBinding para acceder a las vistas del layout
    private lateinit var binding: FragmentAcercaDeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflar el layout con ViewBinding
        binding = FragmentAcercaDeBinding.inflate(layoutInflater)

        // Botón para volver a la lista de episodios
        binding.btnVolverAcerca.setOnClickListener {
            findNavController().navigate(R.id.listaEpisodiosFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Establecer el título de la toolbar
        (requireActivity() as AppCompatActivity)
            .supportActionBar
            ?.title = getString(R.string.acerca_tool)
    }


}