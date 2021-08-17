package com.sagrishin.uikit.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.view.View
import android.view.animation.*
import android.view.animation.AnimationUtils.loadAnimation
import android.widget.ProgressBar
import androidx.core.animation.doOnEnd
import com.sagrishin.uikit.R

fun View.crossFadeTo(outView: View) {
    this.fadeOut()
    outView.fadeIn()
}

fun List<View>.crossFadeTo(outView: View) {
    if (this.any { (View.GONE == it.visibility) || (View.INVISIBLE == it.visibility) }) return

    forEach(View::fadeOut)
    outView.fadeIn()
}

fun View.crossFadeTo(outViews: List<View>) {
    if ((View.GONE == this.visibility) || (View.INVISIBLE == this.visibility)) return

    this.fadeOut()
    outViews.forEach(View::fadeIn)
}

fun View.fadeOut() {
    this.animate().setDuration(200L).alpha(0F).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) { visibility = View.GONE }
    })
}

fun View.fadeIn() {
    this.visibility = View.VISIBLE
    this.alpha = 0F

    this.animate().setDuration(200L).alpha(1F).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) { visibility = View.VISIBLE }
    })
}

fun Context.animateNotFilledRequiredFields(views: List<View>) {
    animateNotFilledRequiredFields(*views.toTypedArray())
}

fun Context.animateNotFilledRequiredFields(vararg views: View) {
    val anim = loadAnimation(this, R.anim.drag_empty_textview)
    views.forEach { view -> view.startAnimation(anim) }
}

fun View.animateAlpha(durationInMillis: Long, visibility: Int, callback: (() -> Unit)? = null) {
    val animation = (if (visibility == View.VISIBLE) AlphaAnimation(0F, 1F) else AlphaAnimation(1F, 0F)).apply {
        duration = durationInMillis
        fillAfter = true

        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                isClickable = false
            }

            override fun onAnimationEnd(animation: Animation) {
                isClickable = true
                callback?.invoke()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }
    this.visibility = visibility

    startAnimation(animation)
}

fun View.rotateUpToDown(animate: Boolean = true) {
    if (animate) {
        rotate(180F, 0F, 300)
    } else {
        rotate(180F, 0F, 0)
    }
}

fun View.rotateDownToUp(animate: Boolean = true) {
    if (animate) {
        rotate(0F, 180F, 300)
    } else {
        rotate(0F, 180F, 0)
    }
}

@JvmOverloads
fun View.rotate(
    from: Float,
    to: Float,
    durationInMillis: Long,
    callback: (() -> Unit)? = null,
    clickLock: Boolean = true
) {
    RotateAnimation(from, to, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F).apply {
        duration = durationInMillis
        fillAfter = true

        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                if (clickLock) isClickable = false
            }

            override fun onAnimationEnd(animation: Animation) {
                if (clickLock) isClickable = true
                callback?.invoke()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        startAnimation(this)
    }
}

fun ProgressBar.animateProgress(from: Double, to: Double, callback: (() -> Unit)? = null) {
    val progressAnimator = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            progress = (from + (to - from) * interpolatedTime).toInt()
        }
    }

    progressAnimator.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {
            isClickable = false
        }

        override fun onAnimationEnd(animation: Animation) {
            isClickable = true
            callback?.invoke()
        }

        override fun onAnimationRepeat(animation: Animation) {

        }
    })

    startAnimation(progressAnimator)
}

fun View.resize(newHeight: Int, isSquired: Boolean = false, duration: Long = 250, callback: (() -> Unit)? = null) {
    val newHeightInPx = (newHeight * resources.displayMetrics.density).toInt()
    ValueAnimator.ofInt(layoutParams.height, newHeightInPx).let { valueAnimator ->
        valueAnimator.duration = duration

        if (isSquired) {
            valueAnimator.addUpdateListener { animation ->
                layoutParams.height = animation.animatedValue as Int
                layoutParams.width = animation.animatedValue as Int
                requestLayout()
            }
        } else {
            valueAnimator.addUpdateListener { animation ->
                layoutParams.height = animation.animatedValue as Int
                requestLayout()
            }
        }

        valueAnimator.doOnEnd { callback?.invoke() }

        AnimatorSet().apply {
            interpolator = AccelerateDecelerateInterpolator()
            play(valueAnimator)
            start()
        }
    }
}


enum class ClickSize {
    SMALL, MIDDLE, LARGE
}

@JvmOverloads
fun View.animateClick(duration: Long = 150, size: ClickSize = ClickSize.SMALL, callback: (() -> Unit)? = null) {
    val from = 1F
    val to = when (size) {
        ClickSize.SMALL -> 0.85F
        ClickSize.MIDDLE -> 0.89F
        ClickSize.LARGE -> 0.93F
    }

    ScaleAnimation(from, to, from, to, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F).apply {
        this.interpolator = CycleInterpolator(1F)
        this.duration = duration

        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                isClickable = false
            }

            override fun onAnimationEnd(animation: Animation) {
                isClickable = true
                callback?.invoke()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })

        startAnimation(this)
    }
}
