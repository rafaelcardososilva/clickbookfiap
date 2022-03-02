package br.com.clickbook.ui.feed

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.clickbook.ui.base.auth.BaseAuthFragment
import br.com.clickbook.ui.post.Post
import br.com.clickbook.ui.post.PostAdapter


class FeedFragment : BaseAuthFragment(), PostAdapter.PostLikedCallback {
    override val layout = br.com.clickbook.R.layout.fragment_feed
    private val feedViewModel: FeedViewModel by viewModels()

    private var posts: ArrayList<Post> = ArrayList()
    private var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView(view)
        registerObserver()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpView(view: View) {
        recyclerView = view.findViewById(br.com.clickbook.R.id.post_list) as RecyclerView

        val adapter = PostAdapter(posts, this)
        val mLinearLayoutManager = LinearLayoutManager(view.context)

        recyclerView!!.layoutManager = mLinearLayoutManager
        recyclerView!!.adapter = adapter

        feedViewModel.fetchPosts()
        registerObserver()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun registerObserver() {
        feedViewModel.postListData.observe(viewLifecycleOwner, {
            updateReceiptsList(it)
        })
    }

    override fun liked(pos: Int) {
        feedViewModel.updateLike(pos)
        println("@@@@ liked post by: " + posts[pos].authorName)
        println("@@@@ total likes: " + posts[pos].likeNumber)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateReceiptsList(postsUpdatedList: List<Post>) {
        posts.clear()
        posts.addAll(postsUpdatedList)
        recyclerView?.adapter?.notifyDataSetChanged()
    }
}