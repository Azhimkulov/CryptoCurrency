package us.azhimkulov.cryptocurrency.view.activity

import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import us.azhimkulov.cryptocurrency.AndroidApplication
import us.azhimkulov.cryptocurrency.internal.di.component.ApplicationComponent
import us.azhimkulov.cryptocurrency.internal.di.module.ActivityModule
import us.azhimkulov.cryptocurrency.model.FragmentAction

/**
 * Created by azamat  on 2/21/21.
 */
abstract class BaseActivity : AppCompatActivity() {

    protected fun replaceFragment(containerViewId: Int, fragment: Fragment) {
        setFragment(containerViewId, fragment, FragmentAction.REPLACE)
    }

    protected fun addFragment(containerViewId: Int, fragment: Fragment) {
        setFragment(containerViewId, fragment, FragmentAction.ADD)
    }

    private fun setFragment(
        containerViewId: Int,
        fragment: Fragment,
        fragmentAction: FragmentAction
    ) {
        Handler().post { setFragmentAux(containerViewId, fragment, fragmentAction) }
    }

    private fun setFragmentAux(
        containerViewId: Int,
        fragment: Fragment,
        fragmentAction: FragmentAction
    ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        when (fragmentAction) {
            FragmentAction.REPLACE -> fragmentTransaction.replace(
                containerViewId,
                fragment,
                fragment.javaClass.name
            )
            FragmentAction.ADD -> {
                fragmentTransaction.replace(containerViewId, fragment, fragment.javaClass.name)
                fragmentTransaction.addToBackStack(null)
            }
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    protected fun getApplicationComponent(): ApplicationComponent {
        return getAndroidApplication().applicationComponent
    }

    protected fun getActivityModule(): ActivityModule {
        return ActivityModule(this)
    }

    private fun getAndroidApplication(): AndroidApplication {
        return application as AndroidApplication
    }
}