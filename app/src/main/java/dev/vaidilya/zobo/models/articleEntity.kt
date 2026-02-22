package dev.vaidilya.zobo.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class articleEntity(
    @PrimaryKey val id: Int,
    val title: String?,
    val lastName: String?,
    val cover_image: String?,
    val username: String?,
    val name: String?,
    val profile_image: String?,
)