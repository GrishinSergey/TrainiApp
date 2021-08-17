package com.sagrishin.common.navutils

import android.os.Parcelable
import java.util.concurrent.atomic.AtomicBoolean

abstract class BackStackEvent : Parcelable {
    
    val wasDelivered = AtomicBoolean(false)
    
}
