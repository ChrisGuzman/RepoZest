package com.chris_guzman.repozest.utils

import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.chris_guzman.repozest.R
import com.chris_guzman.repozest.utils.extension.getParentActivity
import com.squareup.picasso.Picasso

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { view.visibility = it ?: View.VISIBLE })
    }
}

@BindingAdapter("imageUrl")
fun setMutableImageUrl(view: ImageView, url: MutableLiveData<String>?) {
    Picasso.get().load(url?.value)
        .error(R.drawable.ic_github)
        .resize(200, 200)
        .into(view)
}