package com.mobileprojects.appmobile_____kt

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ListView
import android.widget.TextView

import java.net.URL
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView // ListView utilisée pour afficher les données
    private lateinit var pagetile: TextView // TextView utilisée pour afficher le titre de la page
    private var downloadAPIData: DownloadAPIData? = null // Instance de la classe DownloadAPIData pour télécharger les données

    private var feedURL = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=%d/xml" // URL de l'alimentation de données
    private var feedLimit = 10 // Limite du nombre d'éléments à afficher
    private var feedType = "Songs" // Type de données (Songs ou Albums)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisation des vues
        listView = findViewById(R.id.listView)
        pagetile = findViewById(R.id.titreID)
        pagetile.text = "Top 10 Songs" // Définition du titre initial

        // Création et exécution de la tâche asynchrone DownloadAPIData pour télécharger les données
        val downloadAPIData = DownloadAPIData(this, listView)
        downloadAPIData.execute(feedURL.format(feedLimit))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.floating_menu, menu)
        return true
    }

    private fun downloadData(feedURL: String) {
        // Création et exécution de la tâche asynchrone DownloadAPIData pour télécharger les données
        val downloadAPIData = DownloadAPIData(this, listView)
        downloadAPIData.execute(feedURL)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.mnuAlbums -> {
                // L'utilisateur a sélectionné l'option Albums
                feedType = "Albums"
                feedURL = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topalbums/limit=%d/xml"
            }
            R.id.mnuSongs -> {
                // L'utilisateur a sélectionné l'option Songs
                feedType = "Songs"
                feedURL = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=%d/xml"
            }
            R.id.mnu_top10 -> {
                // L'utilisateur a sélectionné l'option Top 10
                if (!item.isChecked) {
                    item.isChecked = true
                    feedLimit = 10
                }
            }
            R.id.mnu_top25 -> {
                // L'utilisateur a sélectionné l'option Top 25
                if (!item.isChecked) {
                    item.isChecked = true
                    feedLimit = 25
                }
            }
            else -> return super.onOptionsItemSelected(item)
        }

        // Mise à jour du titre de la page
        pagetile.text = "Top $feedLimit $feedType"

        // Téléchargement des nouvelles données
        downloadData(feedURL.format(feedLimit))
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        downloadAPIData?.cancel(true) // Annulation de la tâche asynchrone DownloadAPIData lors de la destruction de l'activité
    }

    companion object {
        private class DownloadAPIData(context: Context, listView: ListView) : AsyncTask<String, Void, String>() {

            var propContext: Context by Delegates.notNull()
            var propListView: ListView by Delegates.notNull()

            init {
                propContext = context
                propListView = listView
            }

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)

                // Analyse des données téléchargées
                val apiParser = APIParser()
                apiParser.parseXML(result)

                // Création de l'adaptateur pour afficher les données dans la ListView
                val apiAdapter = APIAdapter(propContext, R.layout.layout_item, apiParser.applicationInfo)
                propListView.adapter = apiAdapter
            }

            override fun doInBackground(vararg p0: String?): String {
                return URL(p0[0]).readText() // Téléchargement du contenu de l'URL spécifiée
            }
        }
    }
}