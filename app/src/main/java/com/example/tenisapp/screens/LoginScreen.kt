@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.tenisapp.screens

import android.util.Log
import com.example.tenisapp.TenisViewModelProvider
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

import com.example.tenisapp.R
import com.example.tenisapp.components.PrimaryButton
import com.example.tenisapp.components.TertiaryButton
import com.example.tenisapp.data.model.User

import com.example.tenisapp.viewModel.LoginViewModel
import kotlinx.coroutines.launch

//import com.example.tenisapp.AppViewModelProvider

@Composable
fun LoginScreen(onNavigateToTournaments: () -> Unit, viewModelProvider : TenisViewModelProvider) {
    val viewModel: LoginViewModel = viewModelProvider.loginViewModel;

    Box(
        Modifier.fillMaxSize()
    ) {
        Login(Modifier.align(Alignment.Center), viewModel, onNavigateToTournaments)
    }
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel, onNavigateToTournaments: () -> Unit) {
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()){
            HeaderImage()
            Spacer(modifier = Modifier.padding(16.dp))
            LoginForm(viewModel, onNavigateToTournaments)
        }
    }
}

@Composable
fun LoginForm(viewModel: LoginViewModel, onNavigateToTournaments: () -> Unit){
    val username: String by viewModel.username.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.padding(16.dp))
        UsernameField(username) { viewModel.onLoginChanged(it, password) }
        Spacer(modifier = Modifier.padding(4.dp))
        PasswordField(password) { viewModel.onLoginChanged(username
            , it)
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Spacer(modifier = Modifier.padding(16.dp))
        /*LoginButton(loginEnable) {
            coroutineScope.launch {
                viewModel.onLoginSelected()
            }
            onNavigateToTournaments()
        }*/
        PrimaryButton(
            text = "Login",
            enabled = loginEnable,
            onClick = {
                coroutineScope.launch {
                    if(viewModel.findUsername(username,password) != null){
                        Log.d("LoginScreen", "Recibi el usuario")
                    }
                    /*if(viewModel.findUsername(username,password).userId != 0){
                        onNavigateToTournaments()
                    }*/
                }
            }
        )
        
        Spacer(modifier = Modifier.padding(16.dp))

        TertiaryButton(
            text = "Ingresar como arbitro",
            onClick = { onNavigateToTournaments() },
            enabled = true
        )

        TertiaryButton(
            text = "Nuevo usuario",
            onClick = {
                coroutineScope.launch {
                    //viewModel.create(User(username="usuario", password = "123456"))
                }
            },
            enabled = true
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = password, onValueChange = { onTextFieldChanged(it) },
        placeholder = { Text("Contraseña") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.colorScheme.onPrimary,
            placeholderColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun UsernameField(username: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = username, onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Username") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.colorScheme.onPrimary,
            placeholderColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun HeaderImage() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .paint(
                painter = painterResource(R.drawable.welcome),
                contentScale = ContentScale.FillWidth
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Bienvenido",
            style = MaterialTheme.typography.displayLarge
        )
    }
}