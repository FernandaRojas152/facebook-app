package edu.co.icesi.facebook_app

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var user: TextView = itemView.findViewById(R.id.user)
    var location: TextView = itemView.findViewById(R.id.location)
    var imagePost: ImageView = itemView.findViewById(R.id.imagePost)
    var date: TextView = itemView.findViewById(R.id.date)
    var description: TextView = itemView.findViewById(R.id.description)

    init {
    }
}