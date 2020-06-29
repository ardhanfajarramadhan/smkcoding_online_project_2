package com.coding.smkcoding_project_2.repository

import androidx.lifecycle.LiveData
import com.coding.smkcoding_project_2.dao.TutorialDao
import com.coding.smkcoding_project_2.model.TutorialModel

class TutorialRepository(private val tutorialDao: TutorialDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allTutorial: LiveData<List<TutorialModel>> = tutorialDao.getAllTutorial()

    suspend fun insert(tutorial: TutorialModel) {
        tutorialDao.insert(tutorial)
    }

    suspend fun insertAll(tutorial: List<TutorialModel>) {
        tutorialDao.insertAll(tutorial)
    }

    suspend fun deleteAll() {
        tutorialDao.deleteAll()
    }

    suspend fun update(tutorial: TutorialModel) {
        tutorialDao.update(tutorial)
    }

    suspend fun delete(tutorial: TutorialModel) {
        tutorialDao.delete(tutorial)
    }
}
