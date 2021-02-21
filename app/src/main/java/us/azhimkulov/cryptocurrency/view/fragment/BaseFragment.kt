package us.azhimkulov.cryptocurrency.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import us.azhimkulov.cryptocurrency.internal.di.HasComponent
import us.azhimkulov.cryptocurrency.model.ToastDuration
import us.azhimkulov.cryptocurrency.view.viewmodel.LoadingViewModel

/**
 * Created by azamat  on 2/21/21.
 */
abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoadingViewModel()
    }

    protected fun <C> getComponent(componentType: Class<C>): C {
        return componentType.cast((activity as HasComponent<C>).getComponent())!!
    }

    private fun setupLoadingViewModel() {
        provideLoadingViewModel()?.let {
            it.onToastShow = { message, duration -> showToast(message, duration) }
            it.onAlertDialogShow = { alertDialog -> showAlertDialog(alertDialog) }
            it.context = { context }
        }
    }

    private fun showToast(message: String, duration: ToastDuration) {
        context?.let {
            Toast.makeText(it, message, duration.value).show()
        }
    }

    private fun showAlertDialog(alertDialog: AlertDialog) {
        alertDialog.show()
    }

    abstract fun provideLoadingViewModel(): LoadingViewModel?
}