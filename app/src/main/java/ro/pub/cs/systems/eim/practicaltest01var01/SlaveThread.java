package ro.pub.cs.systems.eim.practicaltest01var01;

import android.content.Context;
import android.content.Intent;

import java.util.Date;

public class SlaveThread extends Thread {

    private Context context;
    private String value;

    public SlaveThread(Context context, String val) {
        this.context = context;
        value = val;
    }

    @Override
    public void run() {
    sleep();
        sendMessage();
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(String.valueOf(0));
        intent.putExtra("message",new Date(System.currentTimeMillis()) + value);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

}
