package com.sagrishin.common.activities

import androidx.annotation.LayoutRes
import androidx.navigation.NavController

abstract class NavControllerActivity(@LayoutRes contentLayoutId: Int) : BaseActivity(contentLayoutId) {

    var activityNavController: NavController? = null
        protected set

    override fun onSupportNavigateUp(): Boolean {
        return activityNavController?.navigateUp() ?: false
    }

}
