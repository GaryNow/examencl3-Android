package pe.com.examencl3.ProyectoREST

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.com.examencl3.R

class CustomAdapterVideo(private var mList: List<Video>, private val onItemClick: (String) -> Unit): RecyclerView.Adapter<CustomAdapterVideo.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView){
        val titutloVideo: TextView = ItemView.findViewById(R.id.lbl_title);
        val subtituloVideo: TextView = ItemView.findViewById(R.id.lbl_subtitle);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_preg1, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Si deseo alguna conexión podría ser aquí(BD, Servicios web, etc)
        val itemViewModel = mList[position]

        holder.titutloVideo.text = itemViewModel.title
        holder.subtituloVideo.text = itemViewModel.subtitle
        holder.itemView.setOnClickListener {
            val videoUrl = mList[position].sources.firstOrNull()
            if (videoUrl != null) {
                onItemClick(videoUrl)
            }
        }
    }


}