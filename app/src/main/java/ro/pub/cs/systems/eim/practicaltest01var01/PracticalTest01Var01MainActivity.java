package ro.pub.cs.systems.eim.practicaltest01var01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var01MainActivity extends AppCompatActivity {

    EditText text1 = null;
    EditText text2 = null;

    Button button1 = null;
    Button button2 = null;
    Button button3 = null;
    Button button4 = null;
    Button button5 = null;

    int contor = 0;
    int secondActivityCode = 1;

    boolean serviceRunning = false;

    private IntentFilter startedServiceIntentFilter;
    private StartedServiceBroadcastReceiver startedServiceBroadcastReceiver;
    private class StartedServiceBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            text2.setText("yes");
            text2.setText(intent.getStringExtra("message"));
        }
    }

    private my_ButtonClickListener my_buttonClickListener = new my_ButtonClickListener();
    private class my_ButtonClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            String old_value;
            switch (view.getId()) {
                case R.id.button1:
                    old_value = text1.getText().toString();
                    old_value += ",NORTH";
                    text1.setText(old_value);
                    contor++;
                    break;
                case R.id.button2:
                    old_value = text1.getText().toString();
                    old_value += ",EAST";
                    text1.setText(old_value);
                    contor++;
                    break;
                case R.id.button3:
                    old_value = text1.getText().toString();
                    old_value += ",WEST";
                    text1.setText(old_value);
                    contor++;
                    break;
                case R.id.button4:
                    old_value = text1.getText().toString();
                    old_value += ",SOUTH";
                    text1.setText(old_value);
                    contor++;
                    break;
                case R.id.button5:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var01SecondaryActivity.class);
                    intent.putExtra("val", text1.getText().toString());
                    startActivityForResult(intent, secondActivityCode);
                    contor = 0;
                    text1.setText("");
                    break;
            }
            if (contor >= 4 && serviceRunning == false)
            {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var01Servce.class);
                intent.putExtra("data", text1.getText().toString());
                getApplicationContext().startService(intent);
                //text2.setText("waiting");
                serviceRunning = true;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var01_main);

        text1 = (EditText)findViewById(R.id.text1);
        text2 = (EditText)findViewById(R.id.text2);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);

        /* [B1] Button Listener */
        button1.setOnClickListener(my_buttonClickListener);
        button2.setOnClickListener(my_buttonClickListener);
        button3.setOnClickListener(my_buttonClickListener);
        button4.setOnClickListener(my_buttonClickListener);
        button5.setOnClickListener(my_buttonClickListener);

        startedServiceBroadcastReceiver = new StartedServiceBroadcastReceiver();
        startedServiceIntentFilter = new IntentFilter();
        startedServiceIntentFilter.addAction(String.valueOf(0));
    }

    /* [B2] Save text */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("contor", contor);
    }

    /* [B2] Restore text */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey("contor")) {
            contor = savedInstanceState.getInt("contor", -1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == secondActivityCode) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    /* [D1] Close service */
    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var01Servce.class);
        stopService(intent);
        super.onDestroy();
    }

    /* [D2] */
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter);
    }

    /* [D2] */
    @Override
    protected void onPause() {
        unregisterReceiver(startedServiceBroadcastReceiver);
        super.onPause();
    }
}
