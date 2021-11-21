package com.gregkluska.shakeanimation

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.ui.geometry.Offset

val shakeKeyframes: AnimationSpec<Offset> = keyframes {
    durationMillis = 820
    val multiplier = 2f
    val easing = CubicBezierEasing(0.36f, 0.07f, 0.19f, 0.97f)
    Offset(1F * multiplier, 1F * multiplier) at durationMillis / 10 * 1 with easing
    Offset(-1F * multiplier, -2F * multiplier) at durationMillis / 10 * 2 with easing
    Offset(-3F * multiplier, 0F * multiplier) at durationMillis / 10 * 3 with easing
    Offset(3F * multiplier, 2F * multiplier) at durationMillis / 10 * 4 with easing
    Offset(1F * multiplier, -1F * multiplier) at durationMillis / 10 * 5 with easing
    Offset(-1F * multiplier, 2F * multiplier) at durationMillis / 10 * 6 with easing
    Offset(-3F * multiplier, 1F * multiplier) at durationMillis / 10 * 7 with easing
}