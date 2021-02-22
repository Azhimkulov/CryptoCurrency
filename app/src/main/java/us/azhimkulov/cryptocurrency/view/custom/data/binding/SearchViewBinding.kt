package us.azhimkulov.cryptocurrency.view.custom.data.binding

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter

/**
 * Created by azamat  on 2/22/21.
 */

@BindingAdapter("app:onQueryTextChange")
fun setOnQueryTextChange(searchView: SearchView, afterTextChanged: (String?) -> Unit) {
    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            afterTextChanged(newText)
            return false
        }
    })
}