package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab5.Database.DBHandler;

public class MainActivity extends AppCompatActivity {
    Button addBtn;
    DBHandler dbHandler;
    EditText un,pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new DBHandler(this);

        addBtn =findViewById(R.id.AddBtn);
        un = findViewById(R.id.editTextName);
        pw= findViewById(R.id.editTextPassword);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = un.getText().toString();
                String password = pw.getText().toString();

                long a = dbHandler.addInfo(username,password);

                if(a == -1){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Added successfully" +a, Toast.LENGTH_SHORT).show();
                    un.setText("");
                    pw.setText("");
                }
            }
        });


    }
}
