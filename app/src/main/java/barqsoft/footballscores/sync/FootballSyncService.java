package barqsoft.footballscores.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by kimsuh on 2/14/16.
 */
public class FootballSyncService extends Service {
    private static final String TAG = FootballSyncService.class.getSimpleName();

    private static final Object sSyncAdapterLock = new Object();
    private static FootballSyncAdapter sSyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d(TAG, "FootballSyncService " + "onCreate");
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = new FootballSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return sSyncAdapter.getSyncAdapterBinder();
    }
}
