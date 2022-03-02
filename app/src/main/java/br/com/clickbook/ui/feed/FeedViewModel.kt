package br.com.clickbook.ui.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.clickbook.ui.post.Post

class FeedViewModel : ViewModel() {

    public val postListData = MutableLiveData<ArrayList<Post>>()

    fun fetchPosts(){
        //chamada de API que busca e retorna a lista de posts
        val postList = ArrayList<Post>()
        postList.add(
            Post(
                null, "Ricardo Ribeiro", "01/01/2022",
                "culinária", null, "12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
            )
        )
        postList.add(
            Post(
                null, "Rafael Cardoso", "02/01/2022",
                "automobilismo", null, "60", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
            )
        )
        postListData.value = postList
    }

    fun updateLike(pos: Int){
        //chamada de API que atualiza o likeNumber do MutableLiveData

        var likes = postListData.value!![pos].likeNumber.toInt()
        likes += 1
        postListData.value!![pos].likeNumber = likes.toString()
    }

    fun loadImages(){
        //método para carregar imagens de perfil e do post.
    }
}