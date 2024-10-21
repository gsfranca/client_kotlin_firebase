package com.example.appfirestore

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appfirestore.ui.theme.AppFirestoreTheme
import com.example.appfirestore.ui.theme.background
import com.example.appfirestore.ui.theme.dark_black
import com.example.appfirestore.ui.theme.green
import com.example.appfirestore.ui.theme.light_black
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppFirestoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    App(db)
                }
            }
        }
    }
}

@Composable
fun App(db: FirebaseFirestore)
{
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxWidth()
            .background(background)
    )
    {
        Row(
            Modifier
                .background(color = dark_black)
                .padding(0.dp, 40.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = "CLIENT MANAGMENT",
                fontSize = 25.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
                )

        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {}

        Column(
            Modifier
                .fillMaxWidth()
                .padding(30.dp)
        )
        {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 10.dp)
            )
            {
                Text(
                    text = "NAME:",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(Modifier.fillMaxWidth())
            {
                TextField(
                    value = nome,
                    onValueChange = { nome = it },
                    placeholder = { Text(text = "Your full name") },
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = light_black,
                        unfocusedContainerColor = light_black,
                        focusedIndicatorColor = Color.Transparent,
                    )
                )
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ){}

            Column(
                Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 10.dp)
            )
            {
                Text(
                    text = "PHONE:",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold


                )
            }
            Column(Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = light_black,
                    shape = RoundedCornerShape(7.dp)
                )
            )
            {
                TextField(
                    value = telefone,
                    onValueChange = { telefone = it },
                    placeholder = { Text(text = "Your phonenumber") },
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp
                    ),

                    modifier = Modifier
                        .fillMaxWidth(),

                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = light_black,
                        unfocusedContainerColor = light_black,
                        focusedIndicatorColor = Color.Transparent,
                    )
                )


            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )
            {}
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            )
            {
                Button(
                    onClick =
                    {

                        val pessoas = hashMapOf("nome" to nome, "telefone" to telefone)
                        db.collection("client")
                            .add(pessoas)
                            .addOnSuccessListener { documentReference -> Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}") }
                            .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }
                    },
                    modifier = Modifier
                        .fillMaxWidth(),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = green
                    ),
                    shape = RoundedCornerShape(7.dp)
                )
                {
                    Text(
                        text = "SAVE",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            Row(Modifier.fillMaxWidth().padding(20.dp)) {}

            Column(Modifier.border(1.dp, Color.White, RoundedCornerShape(10.dp)))
            {
                Row(Modifier.fillMaxWidth())
                {
                    Column(
                        Modifier
                            .fillMaxWidth(0.5f)
                            .background(
                                color = light_black,
                                shape = RoundedCornerShape(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                    )
                    {
                        Text(
                            text = "Name:",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .background(
                                color = light_black,
                                shape = RoundedCornerShape(0.dp, 10.dp, 0.dp, 0.dp)
                            )
                    )
                    {
                        Text(
                            text = "Phone:",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }

                Row(Modifier.fillMaxWidth())
                {
                    val clientes = remember { mutableStateListOf<HashMap<String, String>>() }

                    LaunchedEffect(Unit)
                    {
                        db.collection("client").get().addOnSuccessListener { documents ->
                            for (document in documents)
                            {
                                val lista = hashMapOf(
                                    "nome" to "${document.getString("nome") ?: "--"}",
                                    "telefone" to "${document.data["telefone"] ?: "--"}"
                                )
                                clientes.add(lista)
                            }
                        }.addOnFailureListener { exception ->
                            Log.w(TAG, "Error getting documents: ", exception)
                        }
                    }

                    Column(modifier = Modifier.fillMaxWidth())
                    {
                        LazyColumn(modifier = Modifier.fillMaxWidth())
                        {
                            items(clientes) { cliente ->
                                Row(Modifier.fillMaxWidth())
                                {
                                    Column(Modifier.weight(0.5f))
                                    {
                                        Text(
                                            text = cliente["nome"] ?: "--",
                                            fontSize = 20.sp,
                                            color = Color.White,
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .align(Alignment.CenterHorizontally)
                                        )
                                    }
                                    Column(Modifier.weight(0.5f))
                                    {
                                        Text(
                                            text = cliente["telefone"] ?: "--",
                                            fontSize = 20.sp,
                                            color = Color.White,
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .align(Alignment.CenterHorizontally)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }


    }
}