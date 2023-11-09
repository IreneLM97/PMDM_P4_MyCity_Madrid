package com.example.pmdm_p4_mycity_madrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pmdm_p4_mycity_madrid.ui.ListCategoryScreen
import com.example.pmdm_p4_mycity_madrid.ui.theme.PMDM_P4_MyCity_MadridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PMDM_P4_MyCity_MadridTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyCityApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun MyCityAppPreview() {
    MyCityApp()
}