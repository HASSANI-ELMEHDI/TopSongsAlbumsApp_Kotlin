package com.mobileprojects.appmobile_____kt

class Model {

    // Propriétés de la classe Model
    var song_name: String = "" // Nom de la chanson
    var singer_name: String = "" // Nom du chanteur
    var urlImg: String = "" // URL de l'image

    override fun toString(): String {
        // Retourne une représentation sous forme de chaîne de caractères des détails de l'API
        return "APIDetails(title='$singer_name', singer='$singer_name')"
    }

}

