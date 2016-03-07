package barqsoft.footballscores.widgets;

import android.appwidget.AppWidgetManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.FootballGameResult;
import barqsoft.footballscores.R;
import barqsoft.footballscores.ScoresAdapter;
import barqsoft.footballscores.Utilies;

/**
 * Created by kimsuh on 2/14/16.
 */
public class FootballWidgetService extends RemoteViewsService {
    private static final String TAG = FootballWidgetService.class.getSimpleName();

    private Context mContext;
    private ContentResolver mContentResolver;
    private int mAppWidgetId;
    private Cursor cursor = null;

    private static final int MATCH_ID = 0;
    private static final int HOME_COL = 3;
    private static final int AWAY_COL = 4;
    private static final int HOME_GOALS_COL = 5;
    private static final int AWAY_GOALS_COL = 6;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new FootballWidgetRemoteViewsFactory(getApplicationContext(), intent);
    }

    public class FootballWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        public FootballWidgetRemoteViewsFactory(Context context, Intent intent) {
            mContext = context;
            mContentResolver = mContext.getContentResolver();
            mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        @Override
        public void onCreate() {
        }

        @Override
        public void onDataSetChanged() {
            if (cursor != null) {
                cursor.close();
                Log.d(TAG, "cursor is not null in dataset changed");
            }

            final long identityToken = Binder.clearCallingIdentity();
            cursor = mContentResolver.query(
                    DatabaseContract.scores_table.buildScoreWithDate(),
                    //TODO: not sure about projection
                    null,
                    null,
                    new String[]{new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()))},
                    null);

            Log.d(TAG, "cursor is : " + cursor.toString());
            Log.d(TAG, "cursor count is " + cursor.getCount());
//            Binder.restoreCallingIdentity(identityToken);
        }

        @Override
        public void onDestroy() {
            if (cursor != null) {
                cursor.close();
            }
        }

        @Override
        public int getCount() {
            Log.d(TAG, "getcount of cursor is : " + cursor.getCount());
            return cursor == null ? 0 : cursor.getCount();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);

            assert cursor != null;

            if (cursor.moveToPosition(position)) {

                remoteViews.setTextViewText(R.id.home_name, cursor.getString(ScoresAdapter.COL_HOME));

                if (cursor.getString(ScoresAdapter.COL_HOME_GOALS).equals("-1")) {
                    remoteViews.setTextViewText(R.id.home_score_textview, "N/A");
                } else {
                    remoteViews.setTextViewText(R.id.home_score_textview, cursor.getString(ScoresAdapter.COL_HOME_GOALS));
                }

                if (cursor.getString(ScoresAdapter.COL_AWAY_GOALS).equals("-1")) {
                    remoteViews.setTextViewText(R.id.away_score_textview, "");
                } else {
                    remoteViews.setTextViewText(R.id.away_score_textview, cursor.getString(ScoresAdapter.COL_AWAY_GOALS));
                }

                remoteViews.setTextViewText(R.id.away_name, cursor.getString(ScoresAdapter.COL_AWAY));
            }
            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
