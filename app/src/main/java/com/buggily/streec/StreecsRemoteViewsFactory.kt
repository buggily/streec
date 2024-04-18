package com.buggily.streec

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.buggily.streec.domain.streec.GetStreecs
import com.buggily.streec.domain.streec.StreecUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

class StreecsRemoteViewsFactory(
    private val context: Context,
    private val getStreecs: GetStreecs,
    intent: Intent?,
) : RemoteViewsService.RemoteViewsFactory {

    private var streecs: List<StreecUi> = emptyList()

    override fun onCreate() {
        onDataSetChanged()
    }

    override fun onDestroy() {
        streecs = emptyList()
    }

    override fun onDataSetChanged() = runBlocking(Dispatchers.IO) {
        streecs = getStreecs().firstOrNull().orEmpty()
    }

    override fun getViewAt(position: Int): RemoteViews = RemoteViews(
        context.packageName,
        R.layout.widget_streecs_streec,
    ).apply {
        val streec: StreecUi? = streecs.getOrNull(position)

        setTextViewText(
            R.id.widget_streecs_streec_name_text_view,
            streec?.nameText.orEmpty(),
        )

        setTextViewText(
            R.id.widget_streecs_streec_streec_text_view,
            streec?.streecText.orEmpty()
        )
    }

    override fun getLoadingView(): RemoteViews = RemoteViews(
        context.packageName,
        R.layout.widget_streecs_streec_loading,
    )

    override fun getItemId(position: Int): Long = streecs[position].id
    override fun hasStableIds(): Boolean = true

    override fun getCount(): Int = streecs.size
    override fun getViewTypeCount(): Int = 1
}
