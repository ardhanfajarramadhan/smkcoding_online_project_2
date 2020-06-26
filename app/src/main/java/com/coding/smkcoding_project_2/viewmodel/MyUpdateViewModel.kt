package com.coding.smkcoding_project_2.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.smkcoding_project_2.db.TutorialDatabase
import com.coding.smkcoding_project_2.model.TutorialModel
import com.coding.smkcoding_project_2.repo.TutorialRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyUpdateViewModel() : ViewModel() {

    lateinit var repository: TutorialRepository

    fun init(context: Context) {
        val myFriendDao = TutorialDatabase.getDatabase(context).tutorialDao()
        repository = TutorialRepository(myFriendDao)
    }

    fun updateData(myFriend: TutorialModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(myFriend)
    }
}