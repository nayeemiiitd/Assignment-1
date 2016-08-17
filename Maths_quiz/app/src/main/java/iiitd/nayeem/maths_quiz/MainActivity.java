package iiitd.nayeem.maths_quiz;

import android.content.Context;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private Button true_button,false_button,next_button;
    private TextView question_number,question_text;
    private int number,question_counter=1;
    private Boolean result;
    private int button_check=0;
//    private final String TAG = "Quiz";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.d(TAG,"OnCreate called");

        true_button =(Button) findViewById(R.id.correct);
        false_button = (Button) findViewById(R.id.incorrect);
        next_button = (Button) findViewById(R.id.next);
        question_number = (TextView) findViewById(R.id.number);
        question_text = (TextView) findViewById(R.id.question);

        if(savedInstanceState!=null)       //getting question number and question on screen rotation
        {
            question_counter = (int) savedInstanceState.get("counter");
            set_question_number(question_counter);
            question_counter++;
            number = (int) savedInstanceState.get("question");
            set_question(number);

        }
        else
        {
            number = generateNumber();
            set_question_number(question_counter);
            question_counter++;
            set_question(number);
        }

        true_button.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);  //for incorrect answer phone will vibrate
                        result = check(number);
                        button_check++;
                        if(result)
                        {
                            Toast.makeText(MainActivity.this, "Correct Answer",Toast.LENGTH_SHORT).show();
                            question_text.setTextColor(Color.GREEN);
                            false_button.setEnabled(false);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Incorrect Answer",Toast.LENGTH_SHORT).show();
                            question_text.setTextColor(Color.RED);
                            false_button.setEnabled(false);
                            vib.vibrate(500);
                        }
                    }
                }
        );

        false_button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    result = check(number);
                    button_check++;
                    if(result)
                    {
                        Toast.makeText(MainActivity.this, "Incorrect Answer",Toast.LENGTH_SHORT).show();
                        question_text.setTextColor(Color.RED);
                        false_button.setEnabled(false);
                        vib.vibrate(500);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Correct Answer",Toast.LENGTH_SHORT).show();
                        question_text.setTextColor(Color.GREEN);
                        false_button.setEnabled(false);
                    }
                }
            }
        );

        next_button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(button_check>0)
                    {
                        true_button.setEnabled(true);
                        false_button.setEnabled(true);
                        question_text.setTextColor(Color.BLACK);
                        number = generateNumber();
                        set_question_number(question_counter);
                        question_counter++;
                        set_question(number);
                        button_check=0;
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "First Select an option",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        );

    }

    private int generateNumber()
    {
        return ((int)(Math.random()*1000));
    } // generating random number

    private void set_question_number(int question_counter)  //setting the question number
    {
        String Q_no = Integer.toString(question_counter);
        question_number.setText(Q_no);
//        question_counter = question_counter+1;
    }

    private void set_question(int number)   //setting the question
    {
        String Question = "Is"+" "+number+" "+"a prime number ?";
        question_text.setText(Question);
    }

    private Boolean check(int number)   //checking whether a number is prime or not
    {
        if(number==2)
            return true;
        if(number==3)
            return true;
        if(number%2==0)
            return false;
        if(number%3==0)
            return false;
        int i=5,w=2;
        while(i*i<=number)
        {
            if((number%i)==0)
                return false;
            i+=w;
            w=6-w;
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putInt("counter",question_counter-1);
        savedInstanceState.putInt("question", number);
//        Log.d(TAG,"OnSaveInstanceState called");
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onStart()
    {
//        Log.d(TAG,"OnStart called");
        super.onStart();
    }

    @Override
    public void onPause()
    {
//        Log.d(TAG,"OnPause called");
        super.onPause();
    }

    @Override
    public void onResume()
    {
//        Log.d(TAG,"OnResume called");
        super.onResume();
    }

    @Override
    public void onStop()
    {
//        Log.d(TAG,"OnStop called");
        super.onStop();
    }

    @Override
    public void onDestroy()
    {
//        Log.d(TAG,"OnDestroy called");
        super.onDestroy();
    }

}
