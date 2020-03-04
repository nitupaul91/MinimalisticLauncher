package me.pauln.minimalisticlauncher.data.model

import android.content.Intent
import android.graphics.drawable.Drawable

data class App(
    val appPackage: String,
    val name: String,
    val icon: Drawable,
    val launchIntent: Intent?,
    var isSelected: Boolean
)