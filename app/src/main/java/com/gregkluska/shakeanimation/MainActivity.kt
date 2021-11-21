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
                                    animationSpec = keyframes {
                                        durationMillis = 820
                                        //.36,.07,.19,.97
                                        val m = 2f
                                        val easing = CubicBezierEasing(0.36f, 0.07f, 0.19f, 0.97f)
                                        Offset(
                                            1F * m,
                                            1F * m
                                        ) at durationMillis / 10 * 1 with easing
                                        Offset(
                                            -1F * m,
                                            -2F * m
                                        ) at durationMillis / 10 * 2 with easing
                                        Offset(
                                            -3F * m,
                                            0F * m
                                        ) at durationMillis / 10 * 3 with easing
                                        Offset(
                                            3F * m,
                                            2F * m
                                        ) at durationMillis / 10 * 4 with easing
                                        Offset(
                                            1F * m,
                                            -1F * m
                                        ) at durationMillis / 10 * 5 with easing
                                        Offset(
                                            -1F * m,
                                            2F * m
                                        ) at durationMillis / 10 * 6 with easing
                                        Offset(
                                            -3F * m,
                                            1F * m
                                        ) at durationMillis / 10 * 7 with easing
                                    }
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

val offsetToVector: TwoWayConverter<Offset, AnimationVector2D> =
    TwoWayConverter({ AnimationVector2D(it.x, it.y) },
        { Offset(it.v1, it.v2) })

fun Animatable(initialValue: Offset): Animatable<Offset, AnimationVector2D> =
    Animatable(initialValue, offsetToVector)