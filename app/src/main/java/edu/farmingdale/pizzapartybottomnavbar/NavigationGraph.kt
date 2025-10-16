package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

// minimal Material3 drawer imports
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavigationGraph(navController: NavHostController, onBottomBarVisibilityChanged: (Boolean) -> Unit) {
    // ToDo 8: add a drawer navigation as described in drawable drawermenu.png
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    // FIX: keep the State delegate separate, then read .route from it
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerItem(
                    label = { Text("Welcome") },
                    selected = currentRoute == BottomNavigationItems.Welcome.route,
                    onClick = { navController.navigate(BottomNavigationItems.Welcome.route) }
                )
                NavigationDrawerItem(
                    label = { Text("Pizza") },
                    selected = currentRoute == BottomNavigationItems.PizzaScreen.route,
                    onClick = { navController.navigate(BottomNavigationItems.PizzaScreen.route) }
                )
                NavigationDrawerItem(
                    label = { Text("GPA") },
                    selected = currentRoute == BottomNavigationItems.GpaAppScreen.route,
                    onClick = { navController.navigate(BottomNavigationItems.GpaAppScreen.route) }
                )
                NavigationDrawerItem(
                    label = { Text("Screen 3") },
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