package us.azhimkulov.cryptocurrency.view.custom.data.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import us.azhimkulov.cryptocurrency.R

/**
 * Created by azamat  on 2/21/21.
 */
@BindingAdapter("app:url")
fun loadUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_cryptocurrencies)
        .into(imageView)
}