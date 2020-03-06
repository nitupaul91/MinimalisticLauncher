package me.pauln.minimalisticlauncher.data

import androidx.room.*
import io.reactivex.Single
import me.pauln.minimalisticlauncher.data.model.App

@Dao
interface AppsSelectionDao {

    @Query("SELECT * FROM App")
    fun getApps(): Single<List<App>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(app: App)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg app: App)

    @Delete
    fun remove(vararg app: App)
}