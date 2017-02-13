package com.example.kylu.wordpalindrome;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    private Button btCheck;
    private EditText word;
    private ListView list;
    //private TextView result;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btCheck = (Button)findViewById(R.id.btCheck);
        word = (EditText)findViewById(R.id.word);
        //result = (TextView)findViewById(R.id.result);
        list = (ListView)findViewById(R.id.list);
        db = new Database(this);

        ArrayList<String> wordList = new ArrayList<>();
        Cursor data = db.getListContents();

            while(data.moveToNext())
            {
                wordList.add("ID: " + data.getString(0) + "\n" + "Word: " +  data.getString(1)+ "\n" + "Result: " + data.getString(2));
                ArrayAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, wordList);
                list.setAdapter(listAdapter);
            }

        btCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ArrayList<String> wordList = new ArrayList<>();
                Cursor data = db.getListContents();

                if(word.getText().toString().trim().length()<=0)
                {
                    Toast.makeText(MainActivity.this,"Field can not be empty", Toast.LENGTH_LONG).show();
                }
                else
                {
                    String s = word.getText().toString();

                    if (isPalindrome(s)) {
                        Toast.makeText(MainActivity.this, "This is palindrome", Toast.LENGTH_LONG).show();
                        boolean isInserted = db.instertData(s, "True");
                        if (isInserted != true) {
                            Toast.makeText(MainActivity.this, "Data not added", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "This is not palindrome", Toast.LENGTH_LONG).show();
                        boolean isInserted = db.instertData(s, "False");

                        if (isInserted != true) {
                            Toast.makeText(MainActivity.this, "Data not added", Toast.LENGTH_LONG).show();
                        }
                    }

                    while (data.moveToNext()) {
                        wordList.add("ID: " + data.getString(0) + "\n" + "Word: " + data.getString(1) + "\n" + "Result: " + data.getString(2));
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
        check=check.toLowerCase();
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