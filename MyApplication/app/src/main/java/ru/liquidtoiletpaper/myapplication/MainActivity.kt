package ru.liquidtoiletpaper.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.drawable.VectorDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.liquidtoiletpaper.myapplication.ui.theme.GreetingPageBackground
import ru.liquidtoiletpaper.myapplication.ui.theme.MyApplicationTheme
import ru.liquidtoiletpaper.myapplication.ui.theme.*
import java.util.*

class MainActivity : ComponentActivity() {

    /// Заблокировать поворот экрана на устройстве
    @Composable
    fun LockScreenOrientation(orientation: Int) {
        val context = LocalContext.current
        DisposableEffect(Unit) {
            val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
            val originalOrientation = requestedOrientation
            requestedOrientation = orientation
            onDispose {
                requestedOrientation = originalOrientation
            }
        }
    }

    fun Context.findActivity(): Activity? = when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme() {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "startPage") {
                    composable("startPage") { StartPage(navController) }
                }
                LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            }
        }
    }
}

@Composable
fun StartPage(navController: NavController){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(GreetingPageBackground),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            ) {
            }
            // Колонна с заголовком
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "GENSHIN IMPACT",
                        color = PrimaryText,
                        style = MaterialTheme.typography.h1,
                        fontSize = 30.sp,
                        fontFamily = SemiBoldFont,
                        textAlign = TextAlign.Center
                    )

                }

            }
            // Колонна с изображением
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_imagecut),
                    contentDescription = "",

                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)) {
                
            }
            // Колонна с кнопками
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Welcome to the club",
                    color = PrimaryText,
                    style = MaterialTheme.typography.h1,
                    fontSize = 25.sp,
                    fontFamily = SemiBoldFont,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris lacinia eros risus.",
                    maxLines = 2,
                    color = SecondaryText,
                    style = MaterialTheme.typography.body1,
                    fontSize = 12.sp,
                    fontFamily = NormalFont,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 5.dp)
                )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = PrimaryButton,
                        contentColor = PrimaryText,
                        disabledBackgroundColor = SecondaryButton,
                        disabledContentColor = PrimaryText
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = {

                    },
                ){
                    Text(
                        text = "Register",
                        color = PrimaryText,
                        style = MaterialTheme.typography.h1,
                        fontSize = 15.sp,
                        fontFamily = SemiBoldFont,
                        textAlign = TextAlign.Center,
                    )
                }
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = SecondaryButton,
                        contentColor = PrimaryText,
                        disabledBackgroundColor = SecondaryButton,
                        disabledContentColor = PrimaryText
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = {
                        context.startActivity(Intent(context, LoginActivity::class.java))
                    },
                ){
                    Text(
                        text = "Login",
                        color = PrimaryText,
                        style = MaterialTheme.typography.h1,
                        fontSize = 15.sp,
                        fontFamily = SemiBoldFont,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}