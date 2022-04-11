package edu.co.icesi.facebook_app

import android.net.Uri

data class Post(
    var pic : Uri,
    var user : String,
    var caption : String,
    var location : String,
    //var date : String
)