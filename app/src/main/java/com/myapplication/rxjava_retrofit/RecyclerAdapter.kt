package com.myapplication.rxjava_retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.data.PostDataModel
import com.myapplication.R
import java.lang.String


class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    private var posts: List<PostDataModel> = ArrayList()
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_post, null, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: MyViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setPosts(posts: List<PostDataModel>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    fun updatePost(post: PostDataModel) {
        var no =posts.indexOf(post)
        (posts as ArrayList)[no] = post;
        notifyItemChanged(posts.indexOf(post))
    }

    fun getPosts(): List<PostDataModel> {
        return posts
    }

    inner class MyViewHolder(@NonNull itemView: View) : ViewHolder(itemView) {
        var title: TextView
        var numComments: TextView
        var progressBar: ProgressBar
        fun bind(post: PostDataModel) {
            title.setText(post.title)
            if (post.comments == null) {
                showProgressBar(true)
                numComments.text = ""
            } else {
                showProgressBar(false)
                numComments.setText(String.valueOf(post.comments.size))
            }
        }

        private fun showProgressBar(showProgressBar: Boolean) {
            if (showProgressBar) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }

        init {
            title = itemView.findViewById(R.id.title)
            numComments = itemView.findViewById(R.id.count_comments)
            progressBar = itemView.findViewById(R.id.prg_circular)
        }
    }

    companion object {
        private const val TAG = "RecyclerAdapter"
    }
}