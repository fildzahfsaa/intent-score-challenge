package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {

    private static final String RESULT_KEY = "result";
    public static final int REQUEST_CODE_HOME = 1;
    public static final int REQUEST_CODE_AWAY = 2;
    private TextView homeText;
    private TextView awayText;
    private ImageView homeImage;
    private ImageView awayImage;
    private TextView score_Home;
    private TextView score_Away;
    int scoreHome = 0;
    int scoreAway = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);
        score_Home = findViewById(R.id.score_home);
        score_Away = findViewById(R.id.score_away);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String home = extras.getString(MainActivity.HOMETEAM_KEY);
            homeText.setText(home);
            String away = extras.getString(MainActivity.AWAYTEAM_KEY);
            awayText.setText(away);

            Bitmap bm1 = (Bitmap) extras.get("home_logo");
            homeImage.setImageBitmap(bm1);

            Bitmap bm2 = (Bitmap) extras.get("away_logo");
            awayImage.setImageBitmap(bm2);
        }

        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
        //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
        //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",
    }

    public void addHome_handler(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, REQUEST_CODE_HOME);
    }

    public void addAway_handler(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, REQUEST_CODE_AWAY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_HOME) {
            String returnString = data.getStringExtra("keyName");
            TextView TVhome = (TextView) findViewById(R.id.textView4);
            String ScorerHome = "";
            ScorerHome += " "+returnString;
            TVhome.setText(ScorerHome);
            scoreHome++;
            score_Home.setText(String.valueOf(scoreHome));

        } else if (requestCode == REQUEST_CODE_AWAY) {
            TextView TVhome = (TextView) findViewById(R.id.textView5);
            String returnString = data.getStringExtra("keyName");
            String ScorerAway = "";
            ScorerAway += " "+returnString;
            TVhome.setText(ScorerAway);
            scoreAway++;
            score_Away.setText(String.valueOf(scoreAway));
        }
    }

    public void hasilHandler(View view) {
        String result = null;
        if (scoreHome==scoreAway){
            result = "DRAW";
        }else if (scoreHome>scoreAway){
            result = homeText.getText().toString();
        }else if (scoreAway>scoreHome){
            result = awayText.getText().toString();
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(RESULT_KEY, result);
        startActivity(intent);
    }
}
