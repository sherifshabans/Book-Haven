package com.elsharif.bookhaven

import android.app.Application
import com.elsharif.bookhaven.utlis.loadAdUnits
import com.elsharif.bookhaven.utlis.loadInterstitialAdIfNull
import com.google.android.gms.ads.MobileAds
import com.google.firebase.database.FirebaseDatabase

class MyBookApp():Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this){
            loadAdUnits{
                loadInterstitialAdIfNull(this)
            }
        }
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}