package com.example.admin.she;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class EmergencyWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for(int appWidgetId:appWidgetIds){

            Intent intent = new Intent(context,EmergencyWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);

            RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.widget_emergency);
            views.setOnClickPendingIntent(R.id.widget_emergency_button,pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId,views);

        }

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if(intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)){
            Toast.makeText(context,"Sending Emergency Request",Toast.LENGTH_SHORT).show();
        }
    }
}
