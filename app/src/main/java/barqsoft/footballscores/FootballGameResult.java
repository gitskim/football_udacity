package barqsoft.footballscores;

import android.database.Cursor;

/**
 * Created by kimsuh on 2/25/16.
 */
public class FootballGameResult {
    //Match data
    String League = null;
    String mDate = null;
    String mTime = null;
    public String HomeName = null;
    public String AwayName = null;
    String AwayID = null;
    String HomeID = null;
    String Home_goals = null;
    String Away_goals = null;
    String match_id = null;
    String match_day = null;

    public FootballGameResult() {

    }

    public String getHome_goals() {
        return Home_goals == null ? "0" : Home_goals;
    }

    public String getAway_goals() {
        return Away_goals == null ? "0" : Away_goals;
    }

    public static FootballGameResult fromCursor(Cursor cursor) {
        FootballGameResult footballGameResult = new FootballGameResult();

        footballGameResult.match_id = cursor.getString(1);
        footballGameResult.mTime = cursor.getString(2);
        footballGameResult.HomeID = cursor.getString(3);
        footballGameResult.HomeName = cursor.getString(4);
        footballGameResult.Home_goals = cursor.getString(6);
        footballGameResult.AwayID = cursor.getString(7);
        footballGameResult.AwayName = cursor.getString(8);
        footballGameResult.Away_goals = cursor.getString(10);
        footballGameResult.match_day = cursor.getString(12);

        return footballGameResult;
    }
}
