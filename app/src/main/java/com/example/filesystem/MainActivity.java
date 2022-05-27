package com.example.filesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME="example.txt";
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.edittext1);
        password=findViewById(R.id.edittext2);
        Button submit=findViewById(R.id.login);
        Button load=findViewById(R.id.load);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    loadData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    saveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void saveData() throws IOException {
        String name=username.getText().toString();
        String pass=password.getText().toString();

        FileOutputStream fos=null;
        fos=openFileOutput(FILE_NAME,MODE_PRIVATE);
        fos.write(name.getBytes());
        fos.write(pass.getBytes());
        Toast.makeText(this,"Saved"+getFilesDir()+"/"+FILE_NAME,Toast.LENGTH_LONG).show();

        if(fos!=null){
            fos.close();
        }
    }

    public void loadData() throws IOException {
        FileInputStream fis=null;
        fis=openFileInput(FILE_NAME);
        InputStreamReader isr=new InputStreamReader(fis);
        BufferedReader br=new BufferedReader(isr);
        String[] sb=new String[2];
        String line;
        int i=0;
        while((line=br.readLine())!=null)
        {
            sb[i]=line;
            i++;
        }

        username.setText(sb[0]);
        password.setText(sb[1]);
        if(fis!=null){
            fis.close();
        }


    }
}