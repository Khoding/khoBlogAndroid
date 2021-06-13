package xyz.khodok.khoblog.util

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment

fun Fragment.navigate(navDirections: NavDirections) {
    NavHostFragment.findNavController(this).navigate(navDirections)
}

fun Fragment.popBackStack(@IdRes idFragmentRes: Int) {
    NavHostFragment.findNavController(this).popBackStack(idFragmentRes, true)
}

fun Fragment.popBackStack() {
    NavHostFragment.findNavController(this).popBackStack()
}
