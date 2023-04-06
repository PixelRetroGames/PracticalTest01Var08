package ro.pub.cs.eim.systems.eim.practicaltest01var08;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {
    private class ButtonClickListener implements View.OnClickListener {
        private final EditText riddleText;
        private final EditText answerText;
        ButtonClickListener(EditText riddleText, EditText answerText) {
            this.riddleText = riddleText;
            this.answerText = answerText;
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            String riddle = riddleText.getText().toString();
            String answer = answerText.getText().toString();

            if (riddle.length() == 0 || answer.length() == 0) {
                return;
            }

            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02PlayActivity.class);
            intent.putExtra("riddle_text", riddle);
            intent.putExtra("answer_text", answer);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        EditText riddleText = findViewById(R.id.riddle_text);
        EditText answerText = findViewById(R.id.answer_text);

        Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(new ButtonClickListener(riddleText, answerText));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        EditText riddleText = findViewById(R.id.riddle_text);
        EditText answerText = findViewById(R.id.answer_text);
        savedInstanceState.putString("riddle_text", riddleText.getText().toString());
        savedInstanceState.putString("answer_text", answerText.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        EditText riddleText = findViewById(R.id.riddle_text);
        EditText answerText = findViewById(R.id.answer_text);
        if (savedInstanceState.containsKey("riddle_text")) {
            riddleText.setText(savedInstanceState.getString("riddle_text"));
        } else {
            riddleText.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey("answer_text")) {
            answerText.setText(savedInstanceState.getString("answer_text"));
        } else {
            answerText.setText(String.valueOf(0));
        }
    }
}