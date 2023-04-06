package ro.pub.cs.eim.systems.eim.practicaltest01var08;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var02PlayActivity extends AppCompatActivity {

    private class ButtonClickListener implements View.OnClickListener {
        private final EditText answerText;

        String answer;
        Activity activity;

        Intent service;
        ButtonClickListener(String answer, EditText answerText, Activity activity) {
            this.answer = answer;
            this.answerText = answerText;
            this.activity = activity;
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            String userAnswer = answerText.getText().toString();

            switch(view.getId()) {
                case R.id.check_button:
                    if (userAnswer.equals(answer) && userAnswer.length() + answer.length() > 0) {
                        Toast.makeText(activity, "Correct answer", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(activity, "Wrong answer", Toast.LENGTH_LONG).show();
                    }
                    service = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
                    service.putExtra("text", answer);
                    getApplicationContext().startService(service);
                    break;
                default:
                    stopService(service);
                    activity.finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_play);

        String riddle, answer = "";

        EditText riddleText = findViewById(R.id.riddle_non_edit);
        EditText answerText = findViewById(R.id.answer2);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().containsKey("riddle_text")) {
            riddle = intent.getStringExtra("riddle_text");
            riddleText.setText(String.valueOf(riddle));
        }

        if (intent != null && intent.getExtras() != null && intent.getExtras().containsKey("riddle_text")) {
            answer = intent.getStringExtra("answer_text");
        }

        ButtonClickListener listener = new ButtonClickListener(answer, answerText, this);

        Button checkButton = findViewById(R.id.check_button);
        checkButton.setOnClickListener(listener);

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(listener);
    }
}