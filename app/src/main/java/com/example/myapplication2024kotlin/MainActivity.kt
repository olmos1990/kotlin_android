package com.example.myapplication2024kotlin

import android.R
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication2024kotlin.databinding.ActivityMainBinding
import com.example.myapplication2024kotlin.ui.sqlite.DBHandler
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document


///
//import com.mongodb.ConnectionString
//import com.mongodb.MongoClientSettings
//import com.mongodb.ServerApi
//import com.mongodb.ServerApiVersion
//import kotlinx.coroutines.runBlocking
object MongoDBConnection {
    //private const val CONNECTION_STRING = "mongodb+srv://admin:PotatoePie24@cluster0.vc6lp.mongodb.net/?retryWrites=true&w=majority"
    private const val CONNECTION_STRING ="mongodb://192.168.0.134:27017/"
    fun getMongoClient(): MongoClient {
        return MongoClients.create(CONNECTION_STRING)
    }
}
object MongoDBExample {
    fun insertDocument() {
        val mongoClient = MongoDBConnection.getMongoClient()
//        val database1 = mongoClient.getDatabase("admin")
//        database1.runCommand(Document("ping", 1))
//        println("Pinged your deployment. You successfully connected to MongoDB!")
        val database: MongoDatabase = mongoClient.getDatabase("todo")

        val collection: MongoCollection<Document> = database.getCollection("Item")

        val document = Document("name", "John Doe")
            .append("email", "johndoe@example.com")
            .append("age", 30)
        println("created")

        collection.insertOne(document)
        println("inserted!!!")

        mongoClient.close()
    }
}

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            MongoDBExample.insertDocument()
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}