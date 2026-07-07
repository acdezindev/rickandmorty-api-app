package ac.pmdm.rickandmortypf.data.model

/**
 * Modelo de datos para un personaje de Rick and Morty
 *
 * @property image URL de la imagen del personaje
 */
data class Personaje(
    val image: String,   // ""https://rickandmortyapi.com/api/character/avatar/1.jpeg"
)

data class PersonajesApiRespuesta(
    val results: List<Personaje>
)
