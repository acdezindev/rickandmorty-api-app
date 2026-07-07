package ac.pmdm.rickandmortypf

import ac.pmdm.rickandmortypf.data.model.Episodio
import ac.pmdm.rickandmortypf.databinding.ItemEpisodioBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
/**
 * Recibe una lista de datos (como parametro en su constructor o con un meetodo) y se encarga de pintar cada elemento en una fila del RecyclerView.
 * Adaptador para el RecyclerView de episodios
 * Gestiona la creación y vinculación de las vistas para cada episodio
 *
 * @param listaEpisodios Lista de episodios a mostrar
 * @param onClick Callback al hacer clic en un episodio (navega a detalles)
 * @param onVistocambio Callback al marcar/desmarcar un episodio como visto
*/


class EpisodioAdapter(
    private val listaEpisodios: List<Episodio>,
    private val onClick: (Episodio) -> Unit,
    private val onVistocambio: (Episodio, Boolean) -> Unit  //  (callback para el CheckBox)
) : RecyclerView.Adapter<EpisodioAdapter.EpisodioViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodioViewHolder {
        val binding = ItemEpisodioBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EpisodioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodioViewHolder, position: Int) {
        val episodio = listaEpisodios[position]
        holder.bind(episodio)  // **LLAMA AL METODO  bind() DE LA INNER CLASS**
    }

    override fun getItemCount(): Int = listaEpisodios.size

    /**
     * ViewHolder interno que mantiene la referencia a las vistas de cada elemento
     * Configura los datos y los listeners del episodio
     */
    inner class EpisodioViewHolder(private val binding: ItemEpisodioBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Vincula los datos del episodio a las vistas del ViewHolder
         * @param episodio Episodio a mostrar en esta fila
         */

        // Mostrar los datos del episodio
        fun bind(episodio: Episodio) {

            binding.tvCodigo.text = episodio.episode
            binding.tvFecha.text = episodio.air_date


            // ********** firestore ********
            // Configurar el CheckBox para marcar como visto
                binding.checkVisto.setOnCheckedChangeListener { checkBox, isChecked ->
                    episodio.visto = isChecked
                    onVistocambio(episodio, isChecked)  // Llama al callback
                }
            // ********** firestore ********

                binding.root.setOnClickListener {
                    onClick(episodio)  //
                }


            }
        }
    }

