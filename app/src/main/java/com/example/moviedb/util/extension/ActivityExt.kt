package com.example.moviedb.util.extension

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.moviedb.util.AnimateType

fun AppCompatActivity.replaceFragmentInActivity(
    @IdRes containerId: Int, fragment: Fragment,
    addToBackStack: Boolean = true, tag: String = fragment::class.java.simpleName,
    animateType: AnimateType = AnimateType.FADE
) {
    supportFragmentManager.transact({
        if (addToBackStack) {
            addToBackStack(tag)
        }
        replace(containerId, fragment, tag)
    }, animateType = animateType)
}

fun AppCompatActivity.addFragmentToActivity(
    @IdRes containerId: Int, fragment: Fragment,
    addToBackStack: Boolean = true, tag: String = fragment::class.java.simpleName,
    animateType: AnimateType = AnimateType.FADE
) {
    supportFragmentManager.transact({
        if (addToBackStack) {
            addToBackStack(tag)
        }
        add(containerId, fragment, tag)
    }, animateType = animateType)
}

fun AppCompatActivity.goBackFragment(): Boolean {
    val isShowPreviousPage = supportFragmentManager.backStackEntryCount > 1
    if (isShowPreviousPage) {
        supportFragmentManager.popBackStackImmediate()
    }
    return isShowPreviousPage
}

fun AppCompatActivity.isExistFragment(fragment: Fragment): Boolean {
    return supportFragmentManager.findFragmentByTag(fragment::class.java.simpleName) != null
}

fun AppCompatActivity.clearAllFragment() {
    for (i in 0 until supportFragmentManager.backStackEntryCount) {
        supportFragmentManager.popBackStack()
    }
}