package com.elsharif.bookhaven.utlis

import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.squareup.picasso.Picasso
import java.net.URL
import kotlin.time.Duration

fun ImageView.loadOnline(imageUrl: String) {
    Glide.with(this.context)
        .load(imageUrl)
        .transition(withCrossFade())
        .thumbnail(0.5f)
        .into(this)

}
fun ImageView.loadOnline2(imageUrl: String) {
    Picasso.get()
        .load(imageUrl)
        .into(this)
}
fun View.fadeView(
    duration: Long=1000,
    from :Float=0f,
    to :Float=1f
){

    val anim=AlphaAnimation(from,to)
    anim.duration=duration
    startAnimation(anim)
}
fun View.removeWithAnim(){
    fadeView(from = 1f,to =0f, duration = 500)
    visibility=View.GONE
}
fun View.showWithAnim(){
    fadeView( duration = 500)
    visibility=View.VISIBLE
}