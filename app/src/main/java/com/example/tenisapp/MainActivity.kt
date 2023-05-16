package com.example.tenisapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.tenisapp.ui.theme.TenisAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
/*         val itemsRepository: ItemsRepository by lazy {
            TournamentsRepository(TenisDatabase.getDatabase(context).itemDao())
        } */

        super.onCreate(savedInstanceState)
        setContent {
            TenisAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TenisApp()
                }
            }
        }
    }
}