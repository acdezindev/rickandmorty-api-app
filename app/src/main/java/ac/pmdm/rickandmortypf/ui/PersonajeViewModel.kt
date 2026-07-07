package ac.pmdm.rickandmortypf.ui

import ac.pmdm.rickandmortypf.data.api.RetroFitInstance
import ac.pmdm.rickandmortypf.data.model.Personaje
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// VIEWMODEL de los personajes ( para las imagenes de la api )
class PersonajeViewModel : ViewModel() {
    private val _personajes = MutableLiveData<List<Personaje>>()
    val personajes: LiveData<List<Personaje>> = _personajes

    fun loadPersonajes() {
        viewModelScope.launch {
            try {
                _personajes.value = RetroFitInstance.api.getPersonajes().results
            } catch (e: Exception) {
                _personajes.value = emptyList()
            }
        }
    }
}