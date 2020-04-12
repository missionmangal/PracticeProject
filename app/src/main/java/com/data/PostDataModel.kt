package com.data

data class PostDataModel(
    var body: String,
    var id: Int,
    var title: String,
    var userId: Int,
    var comments:List<CommentsDataModel>

)