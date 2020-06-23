package com.positivemind.news.utils.rx

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import java.text.SimpleDateFormat


/**
 * Created by Rajeev Tomar on 22,June,2020
 */
class Utils {


    companion object {
        const val YMD_FORMAT = "yyyy-MM-dd"
        const val YMDMT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

        @JvmStatic
        fun getFormattedDate(
            fromPattern: String?,
            toPattern: String?,
            dateStr: String?
        ): String? {
            try {
                val fromFormat = SimpleDateFormat(fromPattern)
                val toFormat = SimpleDateFormat(toPattern)
                val date = fromFormat.parse(dateStr)
                return toFormat.format(date)
            } catch (pse: Exception) {
            }
            return null
        }

        @JvmStatic
        fun isNetworkAvailable(context: Context?): Boolean {
            if (context == null) return false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val capabilities =
                        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    if (capabilities != null) {
                        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                            return true
                        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                            return true
                        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                            return true
                        }
                    }
                } else {
                    try {
                        val activeNetworkInfo = connectivityManager.activeNetworkInfo
                        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                            Log.i("update_statut", "Network is available : true")
                            return true
                        }
                    } catch (e: java.lang.Exception) {
                        Log.i("update_statut", "" + e.message)
                    }
                }
            }
            Log.i("update_statut", "Network is available : FALSE ")
            return false
        }
    }


}