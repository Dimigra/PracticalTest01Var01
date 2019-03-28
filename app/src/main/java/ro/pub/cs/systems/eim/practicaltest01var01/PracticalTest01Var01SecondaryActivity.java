package ro.pub.cs.systems.eim.practicaltest01var01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var01SecondaryActivity extends AppCompatActivity {

    EditText text3 = null;

    Button button6 = null;
    Button button7 = null;

    private my_ButtonClickListener my_buttonClickListener = new my_ButtonClickListener();
    private class my_ButtonClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button6:
                    setResult(RESULT_OK, null);
                    finish();
                    break;
                case R.id.button7:
                    setResult(RESULT_CANCELED, null);
                    finish();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var01_secondary);

        text3 = (EditText)findViewById(R.id.text3);

        button6 = (Button)findViewById(R.id.button6);
        button7 = (Button)findViewById(R.id.button7);

        button6.setOnClickListener(my_buttonClickListener);
        button7.setOnClickListener(my_buttonClickListener);

        Intent intent = getIntent();
        String data = intent.getStringExtra("val");
        text3.setText(data);
    }
}
