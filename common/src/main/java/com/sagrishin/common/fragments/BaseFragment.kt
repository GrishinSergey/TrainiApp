package com.sagrishin.common.fragments

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sagrishin.common.navutils.addOnBackPressedCallback
import com.sagrishin.common.utils.toDp
import com.sagrishin.common.utils.toPx
import com.sagrishin.common.utils.toSp
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment constructor(
    @LayoutRes contentLayoutId: Int = 0,
    private val delegateBackPressedToNavigator: Boolean = true
) : Fragment(contentLayoutId), HasAndroidInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
        internal set
    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>
        internal set

    @CallSuper
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (delegateBackPressedToNavigator) activity?.addOnBackPressedCallback(this) { onBackPressed() }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return injector
    }

    fun getFont(@FontRes fontId: Int): Typeface {
        return com.sagrishin.common.utils.getFont(requireContext(), fontId)
    }

    fun getDrawable(@DrawableRes resId: Int): Drawable {
        return com.sagrishin.common.utils.getDrawable(requireContext(), resId)
    }

    fun getColor(@ColorRes resId: Int): Int {
        return com.sagrishin.common.utils.getColor(requireContext(), resId)
    }

    fun getPluralBy(@PluralsRes resId: Int, count: Int): String {
        return com.sagrishin.common.utils.getPlural(requireContext(), resId, count)
    }

    fun Int.toPx(): Int {
        return requireContext().toPx(this)
    }

    fun Int.toDp(): Int {
        return requireContext().toDp(this)
    }

    fun Int.toSp(): Int {
        return requireContext().toSp(this)
    }

    fun Float.toPx(): Float {
        return requireContext().toPx(this)
    }

    fun Float.toDp(): Float {
        return requireContext().toDp(this)
    }

    fun Float.toSp(): Float {
        return requireContext().toSp(this)
    }

    protected open fun onBackPressed() {
        (activity as AppCompatActivity).onSupportNavigateUp()
    }

    private fun setupView(view: View) {
        if (delegateBackPressedToNavigator && view is Toolbar) {
            view.setNavigationOnClickListener { onBackPressed() }
        }

        if (view is ViewGroup) view.children.forEach { setupView(it) }
    }

}
