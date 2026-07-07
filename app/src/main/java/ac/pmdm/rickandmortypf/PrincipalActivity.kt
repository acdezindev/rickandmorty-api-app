package ac.pmdm.rickandmortypf

import ac.pmdm.rickandmortypf.databinding.ActivityPrincipalBinding
import ac.pmdm.rickandmortypf.ui.EpisodioViewModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController

/**
 * Activity principal de la aplicación
 * Contiene el NavigationDrawer y el contenedor de fragmentos
 */
class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding

    // ViewModel para gestionar los episodios
    private val EpisodiosViewModel: EpisodioViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aplicar el idioma guardado en preferencias
        Idioma.ponerIdioma(this)

        // Inflar el layout con ViewBinding
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Activar la toolbar como ActionBar
        setSupportActionBar(binding.toolbar)

        // Obtener el NavHostFragment del layout
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment // busca el fragmento que declaramos en activity_main.xml

        // Obtener el NavController para gestionar la navegación
        val navController =
            navHostFragment.navController // gestionamos la navegacion entre fragmentos

        // Conectar el menú lateral con el NavController
        binding.navigationView.setupWithNavController(navController)

        // Abrir el menú lateral al pulsar el icono de la hamburguesa
        binding.toolbar.setNavigationOnClickListener {

            binding.menuLateral.openDrawer(GravityCompat.START)
        }


    }
}