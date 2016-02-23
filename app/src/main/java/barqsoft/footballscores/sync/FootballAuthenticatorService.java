package barqsoft.footballscores.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by kimsuh on 2/14/16.
 * <p>
 * This will allow the framework to access our FootballAuthenticator. This is almost exactly the same as the stub code.
 */
public class FootballAuthenticatorService extends Service {
    private FootballAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        super.onCreate();
        mAuthenticator = new FootballAuthenticator(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
