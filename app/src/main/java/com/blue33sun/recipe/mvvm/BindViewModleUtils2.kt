package com.blue33sun.recipe.mvvm

import android.databinding.BindingAdapter
import com.blue33sun.recipe.utils.ImageUtils
import com.facebook.drawee.view.SimpleDraweeView

class BindViewModleUtils{
    @BindingAdapter("bind:imageUrl")
    fun bindImageUrl(simpleDraweeView: SimpleDraweeView,imgUrl:String){
        val imgUri = ImageUtils.getImgUriFromUrl(imgUrl)
        simpleDraweeView.setImageURI(imgUri)
        simpleDraweeView.setController(ImageUtils.getFrscoImgControler(simpleDraweeView, imgUri))
    }

}
