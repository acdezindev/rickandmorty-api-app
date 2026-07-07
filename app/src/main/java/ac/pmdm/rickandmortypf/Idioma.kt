package ac.pmdm.rickandmortypf

import android.content.Context
import androidx.preference.PreferenceManager
import java.util.Locale

/**
 * Gestiona el cambio de idioma de la aplicación
 * Guarda y aplica la preferencia de idioma del usuario
 *!!!!! Esto ya no se hace,  google quiere hacerlo desde los propios ajustes del telefono!!!
 */

/**
 * Aplica el idioma seleccionado por el usuario en los ajustes
 * @param context Contexto de la aplicación para actualizar la configuración
 */
class Idioma {

    companion object {


        fun ponerIdioma(context: Context) {
            // Leer la preferencia de idioma guardada en SharedPreferences
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val quiereIngles = prefs.getBoolean("pref_idioma", false)

            // Seleccionar el idioma según la preferencia del usuario
            val idioma = if
                                 (quiereIngles) "en"
                         else "es"

            // Aplicar el idioma a la configuración de la aplicación (deprecated)
            val locale = Locale(idioma)
            Locale.setDefault(locale)

            val config = context.resources.configuration
            config.setLocale(locale)
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
        }
    }
}