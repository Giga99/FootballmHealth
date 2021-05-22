package football.mhealth.app.footballmhealth

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import football.mhealth.app.footballmhealth.databinding.ActivityMainBinding
import football.mhealth.app.footballmhealth.utils.view_binding.Activity

@AndroidEntryPoint
class MainActivity :
    Activity<ActivityMainBinding>(bindViewBy = { ActivityMainBinding.inflate(it) }) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}