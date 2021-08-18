package com.sagrishin.common.activities

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.NavigationRes
import androidx.navigation.fragment.NavHostFragment
import com.sagrishin.common.R
import com.sagrishin.common.utils.bundleArgs
import kotlinx.parcelize.Parcelize

open class NavHostActivity(
    @LayoutRes contentLayoutId: Int? = null
) : NavControllerActivity(contentLayoutId ?: R.layout.activity_navigation) {

    protected open val contentLayoutId: Int
        get() = R.id.container
    /// TODO: permit nullable params and handle setupNavGraphWithDestination
    protected open val params by bundleArgs<Params>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavGraphWithDestination()
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (activityNavController?.currentDestination?.id == activityNavController?.graph?.startDestination) {
            finish()
            true
        } else {
            super.onSupportNavigateUp()
        }
    }

    protected fun setupNavGraphWithDestination() {
        (supportFragmentManager.findFragmentById(contentLayoutId) as NavHostFragment).apply {
            val graph = navController.navInflater.inflate(params.graphId).apply {
                params.startDestinationId?.also { startDestination = it }
            }
            navController.setGraph(graph, params.startParams)
            activityNavController = navController
        }
    }


    @Parcelize
    data class Params constructor(
        @NavigationRes val graphId: Int,
        @IdRes val startDestinationId: Int? = null,
        val startParams: Bundle? = null
    ) : Parcelable

}
