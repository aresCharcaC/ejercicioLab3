package com.example.exploracion

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exploracion.ui.theme.ExploracionTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExploracionTheme {
                Scaffold(
                    topBar = { TopAppBar(title = { Text("Anime World") }) },
                    bottomBar = { BottomNavigationBar() },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { /* Action */ }) {
                            Icon(Icons.Filled.Add, contentDescription = "Add")
                        }
                    },
                    content = { AnimeContent() }
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    BottomAppBar {
        IconButton(onClick = { /* Action */ }) {
            Icon(Icons.Filled.Home, contentDescription = "Home")
        }
        IconButton(onClick = { /* Action */ }) {
            Icon(Icons.Filled.Search, contentDescription = "Search")
        }
        IconButton(onClick = { /* Action */ }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favorites")
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnimeContent() {
    var showDialog by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }
    var switchState by remember { mutableStateOf(false) }
    var selectedRadio by remember { mutableStateOf("Option 1") }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Anime Dialog") },
            text = { Text("This is an anime-themed dialog.") },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }

    if (showSnackbar) {
        Snackbar(
            action = {
                Button(onClick = { showSnackbar = false }) {
                    Text("Dismiss")
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("This is an anime-themed snackbar!")
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Welcome to Anime World!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Switch:")
            Switch(
                checked = switchState,
                onCheckedChange = { switchState = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(10) { index ->
                Card(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.mahoraga),
                            contentDescription = "Anime Image",
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Anime Item #$index")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(10) { index ->
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.mahoraga),
                            contentDescription = "Anime Image",
                            modifier = Modifier.size(100.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Grid Item #$index")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))



        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            RadioButton(
                selected = selectedRadio == "Option 1",
                onClick = { selectedRadio = "Option 1" }
            )
            Text("Option 1")

            RadioButton(
                selected = selectedRadio == "Option 2",
                onClick = { selectedRadio = "Option 2" }
            )
            Text("Option 2")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { showDialog = true }) {
            Text("Show Dialog")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { showSnackbar = true }) {
            Text("Show Snackbar")
        }
    }
}

fun items(i: Int, any: Any) {

}

@Preview(showBackground = true)
@Composable
fun AnimeContentPreview() {
    ExploracionTheme {
        AnimeContent()
    }
}
