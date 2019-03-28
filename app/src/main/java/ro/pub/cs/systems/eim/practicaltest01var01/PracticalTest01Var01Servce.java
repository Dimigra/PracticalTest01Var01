package ro.pub.cs.systems.eim.practicaltest01var01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var01Servce extends Service {
    SlaveThread processingThread = null;

    public PracticalTest01Var01Servce() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String value = intent.getStringExtra("data");
        processingThread = new SlaveThread(this, value);
        processingThread.start();
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
    }
}
