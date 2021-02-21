package us.azhimkulov.cryptocurrency.view.fragment

import androidx.fragment.app.Fragment
import us.azhimkulov.cryptocurrency.internal.di.HasComponent

/**
 * Created by azamat  on 2/21/21.
 */
abstract class BaseFragment : Fragment() {


    protected fun <C> getComponent(componentType: Class<C>): C {
        return componentType.cast((activity as HasComponent<C>).getComponent())!!
    }
}