package me.pauln.minimalisticlauncher.data

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import me.pauln.minimalisticlauncher.data.model.App

@Dao
interface AppsSelectionDao {

    @Query("SELECT * FROM App")
    fun getApps(): Single<List<App>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveApps(apps: List<App>): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateApps(apps: List<App>)

    @Delete
    fun remove(app: App)
}