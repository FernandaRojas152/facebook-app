package edu.co.icesi.facebook_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PostAdapter : RecyclerView.Adapter<ViewHolder>(){
    private val posts = ArrayList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.postrow, parent, false)
        val postHolder = ViewHolder(view)
        return postHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]

        holder.user.text= post.user
        holder.location.text= post.location
        holder.date.text= post.date
        holder.description.text= post.caption
        holder.imagePost.setImageBitmap(post.pic)
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}