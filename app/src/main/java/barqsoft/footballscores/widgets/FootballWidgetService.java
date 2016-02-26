package barqsoft.footballscores.widgets;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

/**
 * Created by kimsuh on 2/14/16.
 */
public class FootballWidgetService extends RemoteViewsService {
    private static final String TAG = FootballWidgetService.class.getSimpleName();

    private Context mContext;
    private ContentResolver mContentResolver;
    private int mAppWidgetId;
    private ArrayList<FixtureAndTeam> mFixtureAndTeams;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return null;
    }

    public class FootballWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        public FootballWidgetRemoteViewsFactory(Context context, Intent intent) {
            mContext = context;
            mContentResolver = mContext.getContentResolver();
            mAppWidgetId = intent
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            return null;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
