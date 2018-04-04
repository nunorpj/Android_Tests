package nunorpj.github.com.braintrainer;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button startbtn;
    TextView tvSum;
    TextView tvResult;
    TextView tvScore;

    Random rand;
    ArrayList<Integer> answers;
    int locationOfCorrectAnswer;
    int score= 0;
    int numberOfQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSum = (TextView) findViewById(R.id.tv_sum);
        tvResult = findViewById(R.id.tv_result);
        tvScore = findViewById(R.id.tv_score);
        startbtn = (Button) findViewById(R.id.btn_start);
        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);


        rand = new Random();
        answers = new ArrayList<Integer>();

        generateQuestion();


    }

    public void generateQuestion(){

        answers.clear();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        tvSum.setText(a + " + " + b);

        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswer=0;

        for(int i =0; i<4; i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }else {
                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        btn0.setText(answers.get(0).toString());
        btn1.setText(answers.get(1).toString());
        btn2.setText(answers.get(2).toString());
        btn3.setText(answers.get(3).toString());

        tvResult.setText("");
    }


    public void start(View view){
        startbtn.setVisibility(View.INVISIBLE);
    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            tvResult.setText("Correct!");
            tvResult.setTextColor(Color.GREEN);
        }else{
            tvResult.setText("Wrong!");
            tvResult.setTextColor(Color.RED);
        }
        numberOfQuestions++;
        tvScore.setText(score + "/" +numberOfQuestions);

        generateQuestion();
    }

}
