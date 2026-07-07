package ac.pmdm.rickandmortypf.data.api

import ac.pmdm.rickandmortypf.data.model.EpisodiosApiRespuesta
import ac.pmdm.rickandmortypf.data.model.PersonajesApiRespuesta
import retrofit2.http.GET

/**
 * Servicio API para acceder a los endpoints de Rick and Morty
 * Define las peticiones GET para episodios y personajes
 */

interface ApiService {
    @GET("episode")
    suspend fun getEpisodios(): EpisodiosApiRespuesta

    @GET("character")
    suspend fun getPersonajes(): PersonajesApiRespuesta

}


