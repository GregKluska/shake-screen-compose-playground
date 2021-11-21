package com.gregkluska.shakeanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import com.gregkluska.shakeanimation.ui.theme.ShakeAnimationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShakeAnimationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        val coroutineScope = rememberCoroutineScope()


                        val offset = remember { Animatable(Offset(0F, 0F)) }

                        Image(
                            modifier = Modifier.graphicsLayer {
                                this.translationX = offset.value.x
                                this.translationY = offset.value.y
                            },
                            painter = painterResource(id = R.drawable.monalisa),
                            contentDescription = null
                        )
                        Button(onClick = {
                            coroutineScope.launch {
                                offset.animateTo(
                                    targetValue = Offset(0F, 0F),
                                    animationSpec = shakeKeyframes
                                )
                            }

                        }) {
                            Text("Shake")
                        }
                    }
                }
            }
        }
    }
}

