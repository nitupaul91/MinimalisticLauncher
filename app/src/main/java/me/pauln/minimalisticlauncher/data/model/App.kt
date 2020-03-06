package me.pauln.minimalisticlauncher.data.model

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class App(
    @PrimaryKey
    var appPackage: String,
    var name: String,
    @Ignore
    var icon: Drawable?,
    @Ignore
    var launchIntent: Intent?,
    var isSelected: Boolean
) {
    constructor() : this("", "", null, null, false)
}