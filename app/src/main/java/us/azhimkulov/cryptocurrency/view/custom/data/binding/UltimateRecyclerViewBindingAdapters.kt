package us.azhimkulov.cryptocurrency.view.custom.data.binding

import androidx.databinding.BindingAdapter
import us.azhimkulov.cryptocurrency.view.adapter.UltimateAdapter
import us.azhimkulov.cryptocurrency.view.custom.UltimateRecyclerView

/**
 * Created by azamat  on 2/21/21.
 */

@BindingAdapter("ultimateRV:adapter")
fun setAdapter(ultimateRecyclerView: UltimateRecyclerView, ultimateAdapter: UltimateAdapter) {
    ultimateRecyclerView.setAdapter(ultimateAdapter)
}