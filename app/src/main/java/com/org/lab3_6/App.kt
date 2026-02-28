package com.org.lab3_6

import android.content.Context
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.org.lab.ui.description_screen.PreferenceManager
import com.org.mysportapp.ui.description_screen.DescriptionScreen
import kotlinx.serialization.Serializable


@Serializable
object DescriptionScreenTop
@Serializable
object ECommerce
@Serializable
object SearchResults

@Composable
fun App(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) {
    val preferenceManager = remember { PreferenceManager(context) }

    val startRoute = if (preferenceManager.isOnboardingCompleted()) ECommerce else DescriptionScreenTop

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startRoute,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable<DescriptionScreenTop> {
                DescriptionScreen(
                    onFinished = {
                        preferenceManager.setOnboardingCompleted()
                        navController.navigate(route = ECommerce) {
                            popUpTo<DescriptionScreenTop> { inclusive = true }
                        }
                    }
                )
            }

            composable<ECommerce> {

            }
            composable<SearchResults> {
            }
        }
    }
}