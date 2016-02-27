package barqsoft.footballscores;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by yehya khaled on 2/25/2015.
 */
public class DatabaseContract {
    public static final String SCORES_TABLE = "scores_table";

    public static final class scores_table implements BaseColumns {
        //Table data
        public static final String MATCH_ID = "match_id";
        public static final String DATE_COL = "date";
        public static final String TIME_COL = "time";
        public static final String HOME_COL = "home";
        public static final String AWAY_COL = "away";
        public static final String HOME_GOALS_COL = "home_goals";
        public static final String AWAY_GOALS_COL = "away_goals";
        public static final String LEAGUE_COL = "league";
        public static final String MATCH_DAY = "match_day";

        //Projection
        public static final String[] PROJECTION = new String[]{
                MATCH_ID,
                DATE_COL,
                TIME_COL,
                HOME_COL,
                AWAY_COL,
                HOME_GOALS_COL,
                AWAY_GOALS_COL,
                LEAGUE_COL,
                MATCH_DAY,
        };

        public static Uri SCORES_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH)
        .build();
        public static final Uri TEAMS_URI = DatabaseContract.BASE_CONTENT_URI.buildUpon().appendPath("teams").build();
        public static final Uri FIXTURES_AND_TEAMS_URI = DatabaseContract.BASE_CONTENT_URI.buildUpon().appendPath("fixtures_teams").build();

        //Types
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;

        public static Uri buildScoreWithLeague() {
            return BASE_CONTENT_URI.buildUpon().appendPath("league").build();
        }

        public static Uri buildScoreWithId() {
            return BASE_CONTENT_URI.buildUpon().appendPath("id").build();
        }

        public static Uri buildScoreWithDate() {
            return BASE_CONTENT_URI.buildUpon().appendPath("date").build();
        }
    }

    //URI data
    public static final String CONTENT_AUTHORITY = "barqsoft.footballscores";
    public static final String PATH = "scores";
    public static Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
}
