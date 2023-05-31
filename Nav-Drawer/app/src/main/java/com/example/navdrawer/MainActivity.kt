package com.example.navdrawer

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navdrawer.ui.theme.NavDrawerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavDrawerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavDrawer()
                }
            }
        }
    }
}

@Composable
fun NavDrawer() {
    val appState = rememberMyNavDrawerState()

    Scaffold(
        scaffoldState = appState.scaffoldState,
        topBar = {
            MyTopBar(
               onMenuClick = appState::onMenuClick
            )
        },
        drawerContent = {
            MyDrawerContent(
//                Function reference juga dapat membaca satu parameter
//                dan langsung memasukkannya pada fungsi yang dipanggil tanpa menuliskannya.
//                Contohnya ada pada appState::onItemSelected yang tidak perlu menuliskan
//                parameter title: String.
                onItemSelected = appState::onItemSelected,
                onBackPressed = appState::onBackPressed

            )

        },

        drawerGesturesEnabled = appState.scaffoldState.drawerState.isOpen,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(stringResource(R.string.hello_world))
        }
    }
}

data class MenuItem(val title: String, val icon: ImageVector)

@Composable
fun MyDrawerContent(
    modifier: Modifier = Modifier,
    onItemSelected: (title: String) -> Unit,
    onBackPressed: () -> Unit,
) {
    val items = listOf(
        MenuItem(title = stringResource(R.string.home), icon = Icons.Default.Home),
        MenuItem(title = stringResource(R.string.favourite), icon = Icons.Default.Favorite),
        MenuItem(title = stringResource(R.string.profile), icon = Icons.Default.AccountCircle),
    )

    Column(modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .height(190.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
        )
        for (item in items) {
            Row(modifier = Modifier
                .clickable { onItemSelected(item.title) }
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(imageVector = item.icon, contentDescription = item.title, tint = Color.DarkGray)
                Spacer(modifier = Modifier.width(32.dp))
                Text(text = item.title, style = MaterialTheme.typography.subtitle2)
            }
        }
        Divider()

    }

    BackPressHandler{
        onBackPressed()
    }

}

@Composable
fun BackPressHandler(enabled: Boolean = true, onBackPressed: () -> Unit) {
    val currentOnBackPressed by rememberUpdatedState(onBackPressed)
    val backCallback = remember {
        object : OnBackPressedCallback(enabled){
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }

    SideEffect {
        backCallback.isEnabled = enabled
    }

    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current){
        "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
    }.onBackPressedDispatcher

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, backDispatcher){
        backDispatcher.addCallback(lifecycleOwner, backCallback)
        onDispose{
            backCallback.remove()
        }
    }
}

@Preview
@Composable
fun NavDrawerPreview() {
    NavDrawerTheme {
        NavDrawer()
    }
}

@Composable
fun MyTopBar(onMenuClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                onMenuClick()
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.menu)
                )
            }
        },
        title = {
            Text(stringResource(R.string.app_name))
        },
    )
}