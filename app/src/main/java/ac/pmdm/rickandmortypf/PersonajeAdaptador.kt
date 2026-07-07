package ac.pmdm.rickandmortypf

import ac.pmdm.rickandmortypf.data.model.Personaje
import ac.pmdm.rickandmortypf.databinding.ItemPersonajeBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load


/**
 * Adaptador para el RecyclerView de personajes
 * Muestra los personajes con su imagen en un grid
 * @param listaPersonajes Lista de personajes a mostrar
 * @param onClick Callback al hacer clic en un personaje
 */

class PersonajeAdapter(
    private val listaPersonajes: List<Personaje>,
    private val onClick: (Personaje) -> Unit
) : RecyclerView.Adapter<PersonajeAdapter.PersonajeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        val binding = ItemPersonajeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PersonajeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        val personaje = listaPersonajes[position]
        holder.bind(personaje)
    }

    override fun getItemCount(): Int = listaPersonajes.size

    /**
     * ViewHolder interno que mantiene la referencia a las vistas de cada personaje
     */
    inner class PersonajeViewHolder(private val binding: ItemPersonajeBinding) :
        RecyclerView.ViewHolder(binding.root) {


        /**
         * Vincula los datos del personaje a las vistas
         * @param personaje Personaje a mostrar
         */
        fun bind(personaje: Personaje) {
            // Cargar la imagen del personaje con Coil
            binding.imageViewPersonaje.load(personaje.image) {
                crossfade(true)
                placeholder(R.drawable.imagen)
                error(R.drawable.imagen)
            }
            // Configurar clic en la tarjeta del personaje
            binding.root.setOnClickListener {
                onClick(personaje)
            }
        }
    }
}