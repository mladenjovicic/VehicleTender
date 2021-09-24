package com.mladenjovicic.vehicletender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mladenjovicic.vehicletender.ui.winBid.WinBidFragment

class WinBidActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win_bid)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WinBidFragment.newInstance())
                .commitNow()
        }
    }
}