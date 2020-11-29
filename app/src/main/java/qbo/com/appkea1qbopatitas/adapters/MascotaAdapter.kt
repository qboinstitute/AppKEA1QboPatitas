package qbo.com.appkea1qbopatitas.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import qbo.com.appkea1qbopatitas.R
import qbo.com.appkea1qbopatitas.model.Mascota

class MascotaAdapter(private var lstmascota:List<Mascota>,
                    private val context: Context)
    : RecyclerView.Adapter<MascotaAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_mascota,
                parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemMascota = lstmascota[position]
        holder.tvnommascota.text = itemMascota.nommascota
        holder.tvfechamascota.text = itemMascota.fechaperdida
        holder.tvlugarmascota.text = itemMascota.lugar
        holder.tvcontacto.text = itemMascota.contacto
        Glide.with(context).load(itemMascota.urlimagen).into(holder.ivmascota)
    }

    override fun getItemCount(): Int {
        return lstmascota.size
    }

    class ViewHolder(itemView : View) :
            RecyclerView.ViewHolder(itemView)
    {
        val tvnommascota : TextView = itemView.findViewById(R.id.tvnommascota)
        val tvlugarmascota: TextView = itemView.findViewById(R.id.tvlugarmascota)
        val tvfechamascota: TextView = itemView.findViewById(R.id.tvfechamascota)
        val ivmascota: ImageView = itemView.findViewById(R.id.ivmascota)
        val tvcontacto: TextView = itemView.findViewById(R.id.tvcontacto)
    }

}