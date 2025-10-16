package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

// added imports for a simple Material3 drawer
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavigationGraph(navController: NavHostController, onBottomBarVisibilityChanged: (Boolean) -> Unit) {

    // ToDo 8: add a drawer navigation as described in drawable drawermenu.png
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val currentRoute by navController.currentBackStackEntryAsState().let { state ->

        androidx.compose.runtime.remember(state.value) { state.value?.destination?.route }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerSheet {
                NavigationDrawerItem(
                    label = { androidx.compose.material3.Text("Welcome") },
                    selected = currentRoute == BottomNavigationItems.Welcome.route,
                    onClick = { navController.navigate(BottomNavigationItems.Welcome.route) }
                )
                NavigationDrawerItem(
                    label = { androidx.compose.material3.Text("Pizza") },
                    selected = currentRoute == BottomNavigationItems.PizzaScreen.route,
                    onClick = { navController.navigate(BottomNavigationItems.PizzaScreen.route) }
                )
                NavigationDrawerItem(
                    label = { androidx.compose.material3.Text("GPA") },
                    selected = currentRoute == BottomNavigationItems.GpaAppScreen.route,
                    onClick = { navController.navigate(BottomNavigationItems.GpaAppScreen.route) }
                )
                NavigationDrawerItem(
                    label = { androidx.compose.material3.Text("Screen 3") },
                    selected = currentRoute == BottomNavigationItems.Screen3.route,
                    onClick = { navController.navigate(BottomNavigationItems.Screen3.route) }
                )
            }
        }
    ) {
        NavHost(navController, startDestination = BottomNavigationItems.Welcome.route) {
            composable(BottomNavigationItems.Welcome.route) {
                onBottomBarVisibilityChanged(false)
                SplashScreen(navController = navController)
            }
            composable(BottomNavigationItems.PizzaScreen.route) {
                onBottomBarVisibilityChanged(true)
                PizzaPartyScreen()
            }
            composable(BottomNavigationItems.GpaAppScreen.route) {
                onBottomBarVisibilityChanged(true)
                GpaAppScreen()
            }
            composable(BottomNavigationItems.Screen3.route) {
                onBottomBarVisibilityChanged(true)
                Screen3()
            }
        }
    }
}