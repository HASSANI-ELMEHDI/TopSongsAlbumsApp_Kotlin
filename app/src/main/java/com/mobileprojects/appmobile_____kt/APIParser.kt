package com.mobileprojects.appmobile_____kt

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class APIParser {

    // Liste des songs,albums
    val applicationInfo = ArrayList<Model>()

    // Parse xml et rempli la liste avec les infos de songs,albums
    fun parseXML(xmlData: String): ArrayList<Model> {

        val factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true
        val parser = factory.newPullParser()
        parser.setInput(xmlData.reader())
        var event = parser.eventType
        var textValue = ""
        var currentAPI = Model()

        while (event != XmlPullParser.END_DOCUMENT) {
            val tagText = parser.name?.toLowerCase()

            when (event) {
                XmlPullParser.START_TAG -> {
                    if (tagText == "entry") {
                        currentAPI = Model()
                    }
                }
                XmlPullParser.TEXT -> textValue = parser.text
                XmlPullParser.END_TAG -> {
                    when (tagText) {
                        // Ajoute l'objet currentAPI à la liste applicationInfo lorsque la balise <entry> se termine
                        "entry" -> applicationInfo.add(currentAPI)
                        // Récupère le nom de la chanson à partir de la balise <title> et le stocke dans la propriété song_name de currentAPI
                        "title" -> currentAPI.song_name = textValue.split("-")[0]
                        // Récupère l'URL de l'image à partir de la balise <image> et la stocke dans la propriété urlImg de currentAPI
                        "image" -> currentAPI.urlImg = textValue
                        // Récupère le nom de l'artiste à partir de la balise <artist> et le stocke dans la propriété singer_name de currentAPI
                        "artist" -> currentAPI.singer_name = textValue
                    }
                }
            }
            event = parser.next()
        }
        return applicationInfo // Retourne la liste des objets Model (applicationInfo) contenant les données extraites du XML
    }
}

