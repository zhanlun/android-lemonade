package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeContainer(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
) {
    var step by remember {
        mutableStateOf(0)
    }
    var numberOfTimeNeededToSqueezeLemon by remember {
        mutableStateOf(2)
    }

    val mainTextList =
        listOf(R.string.lemon_tap1, R.string.lemon_tap2, R.string.lemon_tap3, R.string.lemon_tap4)
    val imageDescriptionList =
        listOf(R.string.lemon, R.string.lemon, R.string.lemon_glass, R.string.empty_glass)
    val mainImageList = listOf(
        R.drawable.lemon_tree,
        R.drawable.lemon_squeeze,
        R.drawable.lemon_drink,
        R.drawable.lemon_restart
    )

    val mainText = mainTextList[step]
    val imageDescription = imageDescriptionList[step]
    val mainImage = mainImageList[step]
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(id = mainText), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = mainImage),
            contentDescription = stringResource(id = imageDescription),
            modifier = Modifier
                .clickable(onClick = {
                    if (step == 1 && numberOfTimeNeededToSqueezeLemon > 1) {
                        --numberOfTimeNeededToSqueezeLemon
                        return@clickable
                    }
                    step = (step + 1) % mainTextList.size
                    numberOfTimeNeededToSqueezeLemon = (2..4).random()
                })
                .border(
                    border = BorderStroke(1.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                ).padding(PaddingValues(16.dp)),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeContainer()
}