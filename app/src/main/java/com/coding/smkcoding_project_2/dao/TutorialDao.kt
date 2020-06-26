package com.coding.smkcoding_project_2.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.coding.smkcoding_project_2.model.TutorialModel

interface TutorialDao {
    @Query("SELECT * from tutorial_sehat")
    fun getAllTutorial(): LiveData<List<TutorialModel>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tutorial: TutorialModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tutorial: List<TutorialModel>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(tutorial: TutorialModel)

    @Delete()
    suspend fun delete(tutorial: TutorialModel)

    @Query("DELETE FROM tutorial_sehat")
    suspend fun deleteAll()
}