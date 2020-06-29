package com.coding.smkcoding_project_2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_tutorial")
data class TutorialModel(
    var judul : String,
    var deskripsi : String,
    var namaUpload : String,
    @PrimaryKey var key: String
) {

    constructor() : this("","","","")
}

