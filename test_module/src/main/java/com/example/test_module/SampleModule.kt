package com.example.test_module

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.applicaster.analytics.BaseAnalyticsAgent
import com.google.firebase.analytics.FirebaseAnalytics
import java.util.*


/**
 *
 * Created by Lucas Farias on 6/8/20.
 * Copyright © 2019 CME. All rights reserved.
 *
 */
class SampleModule : BaseAnalyticsAgent() {
    lateinit var firebaseClient: FirebaseAnalytics
    override fun logEvent(eventName: String?) {
        super.logEvent(eventName)
        firebaseClient.logEvent(eventName!!, null)
    }

    override fun logEvent(eventName: String?, params: TreeMap<String, String>?) {
        super.logEvent(eventName, params)
        params?.let { it ->
            val bundle = Bundle()
            for ((key, value) in it.entries) {
                bundle.putString(
                    key,
                    value
                )
            }
            eventName?.let { it ->
                firebaseClient?.logEvent(it, bundle)
            }
        }
    }

    override fun setScreenView(activity: Activity?, screenView: String?) {
        super.setScreenView(activity, screenView)
        firebaseClient.setCurrentScreen(activity!!, screenView, null )
    }

    override fun initializeAnalyticsAgent(context: Context?) {
        super.initializeAnalyticsAgent(context)
        firebaseClient = FirebaseAnalytics.getInstance(context!!)
    }

    override fun logStopEvent(currentPosition: Long) {
        super.logStopEvent(currentPosition)
    }


}