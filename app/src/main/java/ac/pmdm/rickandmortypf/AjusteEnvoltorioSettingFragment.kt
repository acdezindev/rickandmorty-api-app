package ac.pmdm.rickandmortypf

import android.content.Intent // para redirigir al login despues de cerrar sesión.
import android.os.Bundle // necesario para onCreatePreferences y onViewCreated.
import android.view.View //  porque onViewCreated recibe un View.
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference// para manejar la preferencia de “cerrar sesion”.
import androidx.preference.PreferenceFragmentCompat// con esto cargamos el xml setting.xml no se usa binding ni onCreateView usamos onCreatePreferences
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


/**
 * Fragmento de ajustes de la aplicación
 * Gestiona preferencias como idioma, tema y cierre de sesión
 */

class AjusteEnvoltorioSettingFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // Cargar el archivo XML de preferencias
        setPreferencesFromResource(R.xml.setting, rootKey)

        // Configurar la acción del botón "Cerrar sesión"
        val cerrarSesionPref = findPreference<Preference>("pref_logout")
        cerrarSesionPref?.setOnPreferenceClickListener {
            cerrarSesionFirebase()
            true
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ajustar el padding superior para que no se superponga con la barra de estado
        val paddingTopDp = 40
        val paddingTopPx = (paddingTopDp * resources.displayMetrics.density).toInt()

        // Aplicar padding al contenedor principal
        view.setPadding(
            view.paddingLeft,
            paddingTopPx,
            view.paddingRight,
            view.paddingBottom
        )

        // Aplicar padding al RecyclerView interno de preferencias
        listView?.setPadding(
            listView?.paddingLeft ?: 0,
            paddingTopPx,
            listView?.paddingRight ?: 0,
            listView?.paddingBottom ?: 0
        )

        // Establecer el título de la toolbar
        (requireActivity() as AppCompatActivity)
            .supportActionBar
            ?.title =
            getString(R.string.ajustes_tool)

    }

    /**
     * Cierra la sesión del usuario en Firebase
     * Redirige a la pantalla de login
     */
    private fun cerrarSesionFirebase() {
        // Cerrar sesión en Firebase Authentication
        Firebase.auth.signOut()
        // Mostrar mensaje de confirmación
        Toast.makeText(requireContext(), "Sesion cerrada, ajustes guardados.", Toast.LENGTH_SHORT)
            .show()

        // Redirigir a la pantalla de login y limpiar el historial de navegación
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }


}