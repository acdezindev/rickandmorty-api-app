package ac.pmdm.rickandmortypf.data.model

data class Episodio(

    /**
     * Modelo de datos para un episodio de Rick and Morty
     *
     * @property id Identificador único del episodio
     * @property name Nombre del episodio
     * @property air_date Fecha de emisión
     * @property episode Código del episodio (formato SxxExx)
     * @property personajes Lista de URLs de personajes que aparecen
     * @property visto Estado de visualización (marcado por el usuario)
     * @property estaSeleccionado Estado de selección para modo múltiple
     */

    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val personajes: List<String> = emptyList(),
    var visto: Boolean = false,
    var estaSeleccionado: Boolean = false,

)

data class EpisodiosApiRespuesta(
     val results: List<Episodio>
)