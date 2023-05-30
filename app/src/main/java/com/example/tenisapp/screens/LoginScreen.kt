@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.tenisapp.screens

import TenisViewModelProvider
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.tenisapp.R
import com.example.tenisapp.components.PrimaryButton
import com.example.tenisapp.components.SecondaryButton
import com.example.tenisapp.components.TertiaryButton

import com.example.tenisapp.viewModel.LoginViewModel
//import com.example.tenisapp.AppViewModelProvider
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(onNavigateToTournaments: () -> Unit, viewModelProvider : TenisViewModelProvider) {
    val viewModel: LoginViewModel = viewModelProvider.getLoginViewModel() as LoginViewModel

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
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.padding(16.dp))
        EmailField(email) { viewModel.onLoginChanged(it, password) }
        Spacer(modifier = Modifier.padding(4.dp))
        PasswordField(password) { viewModel.onLoginChanged(email
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
            onClick = { onNavigateToTournaments() },
            enabled = loginEnable
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
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.typography.headlineSmall.color,
            placeholderColor = MaterialTheme.typography.headlineSmall.color,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = email, onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.typography.headlineSmall.color,
            placeholderColor = MaterialTheme.typography.headlineSmall.color,
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
            text = "Bienvenido!",
            style = MaterialTheme.typography.displayLarge
        )
    }
}