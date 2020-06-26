package com.coding.smkcoding_project_2.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.smkcoding_project_2.db.TutorialDatabase
import com.coding.smkcoding_project_2.model.TutorialModel
import com.coding.smkcoding_project_2.repo.TutorialRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TutorialFragmentViewModel() : ViewModel() {

    lateinit var repository: TutorialRepository

    lateinit var allTutorials: LiveData<List<TutorialModel>>

    fun init(context: Context) {
        val myFriendDao = TutorialDatabase.getDatabase(context).tutorialDao()
        repository = TutorialRepository(myFriendDao)
        allTutorials = repository.allTutorial
    }

    fun delete(tutorial: TutorialModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(tutorial)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertAll(tutorial: List<TutorialModel>) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
        repository.insertAll(tutorial)
    }
}
