package com.mobileprojects.appmobile_____kt


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

// Classe ViewHolder pour maintenir les références des vues de chaque élément de la liste
class ViewHolder(v: View) {
    val title: TextView = v.findViewById(R.id.songName1) // TextView pour afficher le nom de la chanson
    val singer: TextView = v.findViewById(R.id.singerName1) // TextView pour afficher le nom du chanteur
    val image: ImageView = v.findViewById(R.id.image_song1) // ImageView pour afficher l'image de la chanson
}

class APIAdapter(context: Context, private val resource: Int, private val apiData: ArrayList<Model>) :
    ArrayAdapter<Model>(context, resource, apiData) {

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            // Si convertView est null, on crée une nouvelle vue en utilisant le LayoutInflater
            view = inflater.inflate(resource, parent, false)
            viewHolder = ViewHolder(view) // Crée un nouvel objet ViewHolder et associe les vues
            view.tag = viewHolder // Associe l'objet ViewHolder à la vue en utilisant setTag()
        } else {
            // Si convertView n'est pas null, on réutilise la vue existante
            view = convertView
            viewHolder = view.tag as ViewHolder // Récupère l'objet ViewHolder associé à la vue existante
        }

        // Récupère l'objet Model correspondant à la position actuelle dans la liste
        val currentAPI = apiData[position]

        // Met à jour les vues (TextView et ImageView) avec les données de l'objet Model
        viewHolder.title.text = currentAPI.song_name // Affiche le nom de la chanson
        viewHolder.singer.text = currentAPI.singer_name // Affiche le nom du chanteur

        // Utilise Picasso pour charger et afficher l'image de la chanson dans l'ImageView
        Picasso.get()
            .load(currentAPI.urlImg)
            .resize(100, 100)
            .centerCrop()
            .into(viewHolder.image)

        return view // Retourne la vue mise à jour pour l'élément de la liste
    }
}