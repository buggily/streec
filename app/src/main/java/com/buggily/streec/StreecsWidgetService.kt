package com.buggily.streec

import android.content.Intent
import android.widget.RemoteViewsService
import com.buggily.streec.domain.streec.GetStreecs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StreecsWidgetService : RemoteViewsService() {

    @Inject
    lateinit var getStreecs: GetStreecs

    override fun onGetViewFactory(
        intent: Intent?,
    ): RemoteViewsFactory = StreecsRemoteViewsFactory(
        context = applicationContext,
        getStreecs = getStreecs,
        intent = intent,
    )
}
