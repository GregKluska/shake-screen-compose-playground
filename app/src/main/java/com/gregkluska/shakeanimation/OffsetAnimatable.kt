package com.gregkluska.shakeanimation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.ui.geometry.Offset

private val offsetToVector: TwoWayConverter<Offset, AnimationVector2D> =
    TwoWayConverter({ AnimationVector2D(it.x, it.y) },
        { Offset(it.v1, it.v2) })

fun Animatable(initialValue: Offset): Animatable<Offset, AnimationVector2D> =
    Animatable(initialValue, offsetToVector)