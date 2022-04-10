package edu.co.icesi.facebook_app

import android.graphics.Bitmap

class Post {
    var pic : Bitmap
    var user : String
    var caption : String
    var location : String
    var date : String

    constructor(pic : Bitmap, user : String, caption : String, location : String, date : String){
        this.pic = pic
        this.user = user
        this.caption = caption
        this.location = location
        this.date= date
    }
}