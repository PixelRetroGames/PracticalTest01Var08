package ro.pub.cs.eim.systems.eim.practicaltest01var08;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;


public class MyThread extends Thread {

    private Context context = null;
    String text;

    private final Random random = new Random();

    public MyThread(Context context, String text) {
        this.context = context;
        this.text = text;
    }

    @Override
    public void run() {
       while (true) {
            sendMessage();
            sleep();
        }
    }

    private void sendMessage() {

        char[] chars = new char[text.length()];
        Arrays.fill(chars, '*');
        String message = new String(chars);
        int pos = random.nextInt() % text.length();
        message = message.substring(0,pos)+text.charAt(pos)+message.substring(pos + 1);

        Intent intent = new Intent();
        intent.setAction("message");
        intent.putExtra("text",
                message);
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
