package dev.vaidilya.zobo

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.vaidilya.zobo.dao.articleDao
import dev.vaidilya.zobo.models.articleEntity


@Database(
    entities = [articleEntity::class],
    version = 1,
)
abstract class savedArticlesDB: RoomDatabase(){

    abstract val dao: articleDao

}