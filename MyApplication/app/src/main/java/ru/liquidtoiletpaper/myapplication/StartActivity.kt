package ru.liquidtoiletpaper.myapplication

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.liquidtoiletpaper.myapplication.ui.theme.PrimaryPageBackground
import ru.liquidtoiletpaper.myapplication.ui.theme.MyApplicationTheme
import ru.liquidtoiletpaper.myapplication.ui.theme.*

class StartActivity : ComponentActivity() {

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
                Surface(color = PrimaryPageBackground){
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "startPage") {
                        composable("startPage") { EnterAnimation { StartPage(navController) } }
                        composable("loginPage") { EnterAnimation { LoginPage(navController) } }
                        composable("registerPage1") { EnterAnimation { RegisterPage1(navController) } }
                        composable("registerPage2") { EnterAnimation { RegisterPage2(navController) } }
                        composable("registerPage3") { EnterAnimation { RegisterPage3(navController) } }
                    }
                    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    var visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(
            animationSpec = tween(durationMillis = 200),
            initialOffsetX = { 200 }
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutHorizontally(
            animationSpec = spring(stiffness = Spring.StiffnessHigh),
            targetOffsetX = { 200 },
        ) + fadeOut(),
        content = content,
        initiallyVisible = true //true - без анимации, false - с анимацией
    )
}

@Composable
fun StartPage(navController: NavController){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(PrimaryPageBackground),
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
                        color = PrimaryWhite,
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
                    .weight(4f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_knight_banner),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(LowerPrimaryText)
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
                    color = PrimaryWhite,
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
                        contentColor = PrimaryWhite,
                        disabledBackgroundColor = SecondaryButton,
                        disabledContentColor = PrimaryWhite
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = {
                        //context.startActivity(Intent(context, RegisterActivity::class.java))
                        navController.navigate("registerPage1")
                    },
                ){
                    Text(
                        text = "Register",
                        color = PrimaryWhite,
                        style = MaterialTheme.typography.h1,
                        fontSize = 15.sp,
                        fontFamily = SemiBoldFont,
                        textAlign = TextAlign.Center,
                    )
                }
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = SecondaryButton,
                        contentColor = PrimaryWhite,
                        disabledBackgroundColor = SecondaryButton,
                        disabledContentColor = PrimaryWhite
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = {
                        //context.startActivity(Intent(context, LoginActivity::class.java))
                        navController.navigate("loginPage")
                    },
                ){
                    Text(
                        text = "Login",
                        color = PrimaryWhite,
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