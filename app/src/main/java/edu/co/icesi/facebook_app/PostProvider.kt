package edu.co.icesi.facebook_app

import android.net.Uri

class PostProvider {
    companion object{
        val postList = listOf<Post>(
            Post(Uri.parse("./drawable/xiaopfp.jpg"),"Xiao", "dis me","Cali", "14/04/2022"),
            Post(Uri.parse("./drawable/mona.jpg"), "Fernanda", "Mona <3", "Bogota", "17/04/2022")

        )
    }
}