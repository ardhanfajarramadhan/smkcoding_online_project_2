package com.coding.smkcoding_project_2.model

@Entity(tableName = "tutorial_sehat")
data class TutorialModel(
    var judul : String,
    var deskripsi : String,
    var namaUpload : String,
    @PrimaryKey var key: String
) {
    constructor() : this("","","","")
}
