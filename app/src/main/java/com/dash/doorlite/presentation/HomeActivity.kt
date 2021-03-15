package com.dash.doorlite.presentation

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dash.doorlite.R
import com.dash.doorlite.core.presentation.ui.DoorliteBaseActivity
import com.dash.doorlite.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : DoorliteBaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
    }


}