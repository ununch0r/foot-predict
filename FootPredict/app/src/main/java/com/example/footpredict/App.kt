package com.example.footpredict

import android.app.Application
import com.instabug.library.Instabug
import com.instabug.library.invocation.InstabugInvocationEvent

class App : Application() {
    override fun onCreate() {
        super.onCreate();
        Instabug.Builder(this, "3676ca5e72c7ff40002711d7c4e7f801")
            .setInvocationEvents(
                InstabugInvocationEvent.SHAKE,
                InstabugInvocationEvent.FLOATING_BUTTON)
            .build();
    }
}