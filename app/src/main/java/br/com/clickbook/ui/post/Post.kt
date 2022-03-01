package br.com.clickbook.ui.post

class Post(
    var authorImageSrc: String?,
    var authorName: String,
    var postDate: String,
    var categoryName: String,
    var postImageSrc: String?,
    var likeNumber: String,
    var postText: String = "teste"
)