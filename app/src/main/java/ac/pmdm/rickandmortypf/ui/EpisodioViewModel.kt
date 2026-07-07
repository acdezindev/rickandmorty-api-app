package ac.pmdm.rickandmortypf.ui

import ac.pmdm.rickandmortypf.data.api.RetroFitInstance
import ac.pmdm.rickandmortypf.data.model.Episodio
import ac.pmdm.rickandmortypf.data.model.Estadisticas
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

// ES EL CEREBRO
// OBTENEMOS LOS DATOS DE LA API
// Contiene la logica para obtener y exponer los datos a la UI
// Usaremos ViewModel es la que realmente carga y muestra los datos y se pone en la Activity (onCreate) o Fragmento (onViewCreated) donde queramos usarlo, es decir donde necesitemos los datos en pantalla.
/* LO USARIAMOS ASI DONDE LO NECESITEMOS.>>  // LO HACE LINDSAY EN SU EJEMPLO DE LA VIDEOCLASE 4 , min 43 aprox
     charactersViewModel.nombre.observe(this){list -> ( lambda)
          if (list.isNotEmpty())
            binding.name.text =list[0].name
            binding.img.load (list[0].image)
       }
      }
       charactersViewModel.loadEpisodios()

----------------  FIRESTORE -----------------
Aqui realizamos las operaciones basicas para usar la bbdd
Leer, Escribir, Actualizar, Borrar
Creamos funciones que luego usamos en el Fragmento donde necesitemos los datos en este caso ListaEpisodiosFragment
Esas funciones van al fragmento o activity donde las vamos a usar y de ahi se envian a Adaptador
*/
class EpisodioViewModel: ViewModel() {

    // 1 CREAR VARIABLES QUE VAYA A USAR EN LAS FUNCIONES

    //************ CODIGO 1  PARA FIRESTORE BBDD  funiones  guardarEpisodios() y episodiosVistos()*************************
        // Referencia a Firestore y Authentication ( pag 86 tema 3)
        private val db = FirebaseFirestore.getInstance()
        private val auth = FirebaseAuth.getInstance()
    //************ FIN CODIGO 1   *************************


    //********* CODIGO 2 PARA CARGAR EPISODIOS  **************
        private val _nombre = MutableLiveData<List<Episodio>>() // aqui estan todos los episodios
        val nombre: LiveData<List<Episodio>> = _nombre
        // Variable para guardar el total de episodios
        private var totalEpisodios = 0
    //********* FIN  CODIGO 2  PARA CARGAR EPISODIOS  **************

    //********* CODIGO 3 ESTADISTICAS   **************
        // 2. LiveData para estadísticas
        private val _estadisticas = MutableLiveData<Estadisticas>()
        val estadisticas: LiveData<Estadisticas> = _estadisticas



    //********* CODIGO 2 PARA CARGAR EPISODIOS  **************
        // 2 CREAMOS LAS FUNCIONES USANDO LAS VARIABLES..

        // funcion para cargar los episodios
        fun loadEpisodios() { // funcion para hacer la llamada.
            viewModelScope.launch {

                try {
                    _nombre.value = RetroFitInstance.api.getEpisodios().results
                } catch (e: Exception) {
                    //  Toast.makeText(, "", Toast.LENGTH_SHORT).show
                    // PODEMOS PONER UNA LISTA VACIA TB SI HAY UNA EXCEPCION
                    _nombre.value = emptyList()
                }
            }
        }
    //********* FIN  CODIGO 2  PARA CARGAR EPISODIOS  **************





    // ????????????????????????????????????????????
    //************ CODIGO 1  PARA FIRESTORE BBDD  funiones  guardarEpisodios() y episodiosVistos()*************************
    fun guardarEpisodioVisto(episodio: Episodio) {
        //0Verificar que hay usuario logueado
        val userId = auth.currentUser?.uid
        if (userId == null) return

        // 1 Crear el mapa con los datos
        val episodioMapa = hashMapOf<String, Any>(
            "name" to episodio.name,
            "episode" to episodio.episode,
            "air_date" to episodio.air_date,
            "characters" to episodio.personajes,
            "viewed" to true  // Siempre true porque lo estamos marcando como visto
        )

        // 2 Guardar en Firestore
        db.collection("usuarios")
            .document(userId)
            .collection("episodiosVistos")
            .document(episodio.id.toString())  // ID del episodio como nombre de documento
            .set(episodioMapa)
            .addOnSuccessListener {
                // log para debug
            }
            .addOnFailureListener { e ->
                // log para debug
            }
    }
    //************ FIN CODIGO 1  *************************


    //***************** FIRESTORE *******************************
    //************ CODIGO 1  PARA FIRESTORE BBDD  funiones  guardarEpisodios() y episodiosVistos()*************************
    // leer de firestore
    fun cargarEpisodiosVistos() {
        viewModelScope.launch {
            try {
                // verificar usuario
                val userId = auth.currentUser?.uid ?: return@launch // si es no null se sale.

                //1 Leer de Firestore
                val leer = db.collection("usuarios")
                    .document(userId)
                    .collection("episodiosVistos")
                    .get()
                    .await()  // Esperar a que termine

                //2 Convertir documentos
                val listaVistos = leer.documents.map { document ->
                    Episodio(
                        id = document.id.toInt(),
                        name = document.getString("name") ?: "",
                        air_date = document.getString("air_date") ?: "",
                        episode = document.getString("episode") ?: "",
                        visto = true
                    )
                }

                //3Mostrar
                _nombre.value = listaVistos

            } catch (e: Exception) {
                _nombre.value = emptyList()
            }
        }
    }
    //************ FIN CODIGO 1  *************************

    // 5. NUEVA función para calcular estadísticas
    fun calcularEstadisticas() {
        viewModelScope.launch {
            try {
                // 1. Total episodios = los que YA están cargados
                val totalEpisodios = _nombre.value?.size ?: 0

                // 2. Episodios vistos = leer de Firestore
                val userId = auth.currentUser?.uid ?: return@launch
                val snapshot = db.collection("usuarios")
                    .document(userId)
                    .collection("episodiosVistos")
                    .get()
                    .await()

                val episodiosVistos = snapshot.documents.size

                // 3. Calcular porcentaje
                val porcentaje = if (totalEpisodios > 0) {
                    (episodiosVistos * 100f) / totalEpisodios
                } else 0f

                // 4. Guardar
                _estadisticas.value = Estadisticas(totalEpisodios, episodiosVistos, porcentaje)

            } catch (e: Exception) {
                // Si hay error, mostrar 0 en todo
                _estadisticas.value = Estadisticas(0, 0, 0f)
            }
        }
    }


    }
