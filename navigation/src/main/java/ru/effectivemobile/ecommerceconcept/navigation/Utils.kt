package ru.effectivemobile.ecommerceconcept.navigation

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import java.io.Serializable
import kotlin.reflect.KClass

const val DATA_KEY = "Data"

/**
 * Main extension-method for navigation
 *
 * @param actionId - id of action from navigation graph
 * @param hostId - id of navigation container
 * @param data - Any data
 */
fun Fragment.navigate(actionId: Int, hostId: Int? = null, data: Parcelable? = null) {
    val navController = getNavController(hostId)

    val bundle = Bundle().apply { putParcelable(DATA_KEY, data) }

    navController.navigate(actionId, bundle)
}

fun Fragment.navigateWithInfo(navInfo: NavigationInfo, data: Parcelable? = null) = this.navigate(
    actionId = navInfo.actionId,
    hostId = navInfo.hostId,
    data
)

/**
 * Method for finding a navigation - controller
 */
private fun Fragment.getNavController(hostId: Int?) = if (hostId == null) {
    findNavController()
} else {
    Navigation.findNavController(requireActivity(), hostId)
}


/**
 * Method for going back in fragments
 */
fun Fragment.popUp(hostId: Int? = null) {
    val controller = getNavController(hostId)

    controller.popBackStack()
}

/**
 * Method for getting navigation data
 */
inline fun <reified T : Parcelable> Fragment.getNavigationData(): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arguments?.getParcelable(DATA_KEY, T::class.java)
    } else {
        @Suppress("DEPRECATION") arguments?.getParcelable(DATA_KEY) as? T
    }
}