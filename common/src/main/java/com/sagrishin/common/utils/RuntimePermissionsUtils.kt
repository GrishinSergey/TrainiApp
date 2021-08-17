package com.sagrishin.common.utils

import androidx.fragment.app.FragmentActivity
import com.kotlinpermissions.KotlinPermissions

fun FragmentActivity.askFor(
    permissions: Array<String>,
    onGranted: (List<String>) -> Unit,
    onDenied: (List<String>) -> Unit
) {
    KotlinPermissions.with(this).permissions(*permissions)
        .onAccepted { accepted ->
            onGranted(accepted)
        }.onDenied { denied ->
            onDenied(denied)
        }.ask()
}

fun FragmentActivity.doIfGranted(
    permissions: Array<String>,
    onGranted: () -> Unit
) {
    askFor(permissions, {
        onGranted()
    }, {
        if (it.isEmpty()) onGranted()
    })
}
