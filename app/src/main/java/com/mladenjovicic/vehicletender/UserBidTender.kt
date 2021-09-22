package com.mladenjovicic.vehicletender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mladenjovicic.vehicletender.ui.fragmentuserbidtender.fragmentUserBidTender

class UserBidTender : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activityuser_bid_tender)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragmentUserBidTender.newInstance())
                .commitNow()
        }
    }
}