package ac.pmdm.rickandmortypf.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Configuración singleton de Retrofit
 * Proporciona una única instancia para toda la aplicación
 */

object RetroFitInstance {
        // URL BASE de la API de Rick and Morty
        private const val BASE_URL = "https://rickandmortyapi.com/api/"

        // Instancia  (Singleton)
        val api: ApiService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
}