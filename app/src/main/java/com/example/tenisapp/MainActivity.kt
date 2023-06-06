package com.example.tenisapp

import com.example.tenisapp.data.converters.Converter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.tenisapp.data.AppDataContainer
import com.example.tenisapp.data.TenisDatabase
import com.example.tenisapp.ui.theme.TenisAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
/*         val itemsRepository: ItemsRepository by lazy {
            TournamentsRepository(TenisDatabase.getDatabase(context).itemDao())
        } */

        val tenisDatabase: TenisDatabase = Room
            .databaseBuilder(this, TenisDatabase::class.java, "tenisDatabase2")
            .addTypeConverter(Converter())
            .build()

        val repositoryProvider = AppDataContainer(tenisDatabase)

        val viewModelProvider = TenisViewModelProvider(repositoryProvider)

        super.onCreate(savedInstanceState)
        setContent {
            TenisAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TenisApp(viewModelProvider)
                }
            }
        }
    }
}