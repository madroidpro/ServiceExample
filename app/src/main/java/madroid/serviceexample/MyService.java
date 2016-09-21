package madroid.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by madroid on 24-08-2016.
 */
public class MyService extends Service {

    public static String result="0";
    private final  IBinder mBinder = new localBinder();
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i=1;i>0;i++){
                    try {
                        Thread.sleep(2000);
                        Log.d("info_service_data",""+i);
                        result=i+"";

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("info_service","running");
                //stopSelf();
            }
        }).start();
        return Service.START_STICKY;
    }


    public String myNumber(){
        return result;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("info_service","destroyed");
    }

    public class localBinder extends Binder{
        MyService getMyService() {
            return MyService.this;
        }
    }
}
