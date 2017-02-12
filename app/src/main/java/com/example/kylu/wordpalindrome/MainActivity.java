package com.example.kylu.wordpalindrome;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
////
    private Button btCheck;
    private EditText word;
    private ListView list;
    private TextView result;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        btCheck = (Button)findViewById(R.id.btCheck);
        word = (EditText)findViewById(R.id.word);
        result = (TextView)findViewById(R.id.result);
        list = (ListView)findViewById(R.id.list);
        db = new Database(this);

        ArrayList<String> wordList = new ArrayList<>();
        Cursor data = db.getListContents();

        if(data.getCount()==0)
        {

        }
        else
        {
            while(data.moveToNext())
            {
                wordList.add(data.getString(0) + " || " +  data.getString(1) + " || " + data.getString(2));
                ArrayAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, wordList);
                list.setAdapter(listAdapter);
            }
        }



        btCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ArrayList<String> wordList = new ArrayList<>();
                Cursor data = db.getListContents();

                String s = word.getText().toString();
                if (isPalindrome(s))
                {
                    result.setTextColor(Color.GREEN);
                    result.setText("This is palindrome");
                    boolean isInserted = db.instertData(s,"True");
                    if(isInserted == true)
                    {
                        Toast.makeText(MainActivity.this,"Data added", Toast.LENGTH_LONG).show();

                    }
                    else
                        Toast.makeText(MainActivity.this,"Data not added", Toast.LENGTH_LONG).show();

                }
                else
                {
                    result.setTextColor(Color.RED);
                    result.setText("This is not palindrome");
                    boolean isInserted =db.instertData(s,"False");

                    if(isInserted == true)
                    {
                        Toast.makeText(MainActivity.this,"Data added", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(MainActivity.this,"Data not added", Toast.LENGTH_LONG).show();

                }

                if(data.getCount()==0)
                {

                }
                else
                {
                    while(data.moveToNext())
                    {
                        wordList.add(data.getString(0) + " || " +  data.getString(1) + " || " + data.getString(2));
                        ArrayAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, wordList);
                        list.setAdapter(listAdapter);
                    }
                }
            }
        });
    }


    public boolean isPalindrome(String check)
    {
        check=check.replaceAll("\\s+",""); //remove all whitespaces
        int n = check.length();
        for(int i=0;i<(n/2);i++)
        {
            if (check.charAt(i) != check.charAt(n - i - 1))
            {
                return false;
            }
        }
        return true;
    }


}
