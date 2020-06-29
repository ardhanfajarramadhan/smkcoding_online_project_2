package com.coding.smkcoding_project_2.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.smkcoding_project_2.db.TutorialDatabase
import com.coding.smkcoding_project_2.model.TutorialModel
import com.coding.smkcoding_project_2.repository.TutorialRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TutorialViewModel() : ViewModel() {

    lateinit var repository: TutorialRepository

    fun init(context: Context) {
        val tutorialDao = TutorialDatabase.getDatabase(context).tutorDao()
        repository = TutorialRepository(tutorialDao)
    }

    fun addData(tutorialX: TutorialModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(tutorialX)
    }

}
