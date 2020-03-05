package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ScorerActivity extends AppCompatActivity {

    private EditText nameScorer;
    private Button btnScorer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);

        nameScorer = findViewById(R.id.editText);
        btnScorer = findViewById(R.id.button);

        btnScorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String score = nameScorer.getText().toString();

                Intent intent = new Intent(ScorerActivity.this, MatchActivity.class);
                intent.putExtra("keyName", score);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

//    public void okHandler(View view) {
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String stringToPassBack = editText.getText().toString();
//
//        Intent intent = new Intent();
//        intent.putExtra("keyName", stringToPassBack);
//        setResult(RESULT_OK, intent);
//        finish();
//    }
}
