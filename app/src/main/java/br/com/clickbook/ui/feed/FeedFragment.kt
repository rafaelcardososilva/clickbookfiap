package br.com.clickbook.ui.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import br.com.clickbook.R
import br.com.clickbook.ui.base.auth.BaseAuthFragment
import br.com.clickbook.ui.post.Post
import br.com.clickbook.ui.post.PostAdapter

class FeedFragment : BaseAuthFragment() {
    override val layout = R.layout.fragment_feed
    private val feedViewModel: FeedViewModel by viewModels()

    private var posts: ArrayList<Post> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView(view)
        registerObserver()
    }

    private fun setUpView(view: View) {
        //mockando os posts
        posts = createMock()

        val recyclerView = view.findViewById<RecyclerView>(R.id.post_list)
        recyclerView.adapter = PostAdapter(posts)
    }


    private fun registerObserver() {
        //configurações do ViewModel
        //trazer lista de posts com seus dados
        //interagir com o botão de like (enviar nova soma do posts[position] curtido), resposta do clickListener do Adapter
    }

    private fun createMock(): ArrayList<Post> {
        val postsMock = ArrayList<Post>()
        postsMock.add(
            Post(
                null, "Ricardo Ribeiro", "01/01/2022",
                "culinária", null, "12k", "teste 1"
            )
        )
        postsMock.add(
            Post(
                null, "Rafael Cardoso", "02/01/2022",
                "automobilismo", null, "60k", "teste 2"
            )
        )
        return postsMock
    }

}