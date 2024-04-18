package com.buggily.streec

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StreecsWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        for (appWidgetId in appWidgetIds) {
            val intent = Intent(context, StreecsWidgetService::class.java).apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
            }

            val pendingIntent: PendingIntent = PendingIntent.getActivity(
                context,
                0,
                Intent(context, StreecActivity::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
            )

            val views: RemoteViews = RemoteViews(
                context.packageName,
                R.layout.widget_streecs,
            ).apply {
                setRemoteAdapter(
                    R.id.widget_streecs_list_view,
                    intent,
                )

                setEmptyView(
                    R.id.widget_streecs_list_view,
                    R.id.widget_streecs_empty_view,
                )

                setOnClickPendingIntent(
                    R.id.widget_streecs_layout,
                    pendingIntent
                )
            }

            appWidgetManager.updateAppWidget(
                appWidgetId,
                views,
            )
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }
}
