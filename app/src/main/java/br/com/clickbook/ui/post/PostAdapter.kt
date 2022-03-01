package br.com.clickbook.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.clickbook.R
import br.com.clickbook.ui.feed.FeedFragment

class PostAdapter(private val dataSet: ArrayList<Post>, postLikedCallback: PostLikedCallback) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    var mPostLikedCallback: PostLikedCallback? = postLikedCallback

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val authorImage: ImageView = view.findViewById(R.id.authorImageView)
        val authorName: TextView = view.findViewById(R.id.authorName)
        val postDate: TextView = view.findViewById(R.id.postDate)
        val categoryName: TextView = view.findViewById(R.id.categoryName)
        val postImage: ImageView = view.findViewById(R.id.postImage)
        val likeButton: ImageView = view.findViewById(R.id.likeButton)
        val likeNumber: TextView = view.findViewById(R.id.likeNumber)
        val postText: TextView = view.findViewById(R.id.postText)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.post_cell, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        loadImages()

        viewHolder.authorName.text = dataSet[position].authorName
        viewHolder.postDate.text = dataSet[position].postDate
        viewHolder.categoryName.text = dataSet[position].categoryName
        viewHolder.postDate.text = dataSet[position].postDate

        viewHolder.likeButton.setOnClickListener {
            mPostLikedCallback?.liked(position)
        }

        viewHolder.likeNumber.text = dataSet[position].likeNumber
        viewHolder.postText.text = dataSet[position].postText
    }

    private fun loadImages() {
        //usar Glide para carregar as imagens guardadas no firebase? Criar método separado,
        //vai ter que chamar algum método do View Model através do Fragment
        //viewHolder.authorImage.setImageResource()
        //viewHolder.postImage.setImageResource()
    }

    override fun getItemCount() = dataSet.size

    interface PostLikedCallback {
        fun liked(post: Int)
    }

}
