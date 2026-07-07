package ac.pmdm.rickandmortypf.data.model

/**
 * Modelo de datos para las estadísticas de visualización del usuario
 *
 * @property totalEpisodios Número total de episodios disponibles
 * @property episodiosVistos Número de episodios marcados como vistos
 * @property porcentaje Porcentaje de progreso (episodiosVistos / totalEpisodios * 100)
 */
data class Estadisticas(
    val totalEpisodios: Int = 0,
    val episodiosVistos: Int = 0,
    val porcentaje: Float = 0f
)