package football.mhealth.app.footballmhealth.presentation.view.activities

import dagger.hilt.android.AndroidEntryPoint
import football.mhealth.app.footballmhealth.databinding.ActivityMainBinding
import football.mhealth.app.footballmhealth.utils.view_binding.Activity

@AndroidEntryPoint
class MainActivity :
    Activity<ActivityMainBinding>(bindViewBy = { ActivityMainBinding.inflate(it) })
