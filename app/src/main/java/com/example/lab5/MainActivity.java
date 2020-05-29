package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab5.Database.DBHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button addBtn, selectAll, signIn, deleteBtn ,update;
    DBHandler dbHandler;
    EditText un,pw;
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new DBHandler(this);

        addBtn =findViewById(R.id.AddBtn);
        selectAll =findViewById(R.id.selectAllBtn);
        signIn =findViewById(R.id.signinBtn);
        un = findViewById(R.id.editTextName);
        pw= findViewById(R.id.editTextPassword);
        deleteBtn =findViewById(R.id.deleteBtn);
        update =findViewById(R.id.updateBtn);

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

        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List a =dbHandler.ReadAllInfo();
                Log.i(TAG, "List of users : " + a);
                Toast.makeText(MainActivity.this, "List printed on the log", Toast.LENGTH_SHORT).show();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = un.getText().toString();
                String password = pw.getText().toString();

                boolean a = dbHandler.readInfo(username, password);

                if(a){
                    Toast.makeText(MainActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                    un.setText("");
                    pw.setText("");
                }else{
                    Toast.makeText(MainActivity.this, "User is not existing", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = un.getText().toString();
                String password = pw.getText().toString();

                int b = dbHandler.deleteInfo(username, password);
                if(b==-1){
                    Toast.makeText(MainActivity.this, "Fields are not matching", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
                    un.setText("");
                    pw.setText("");
                }
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = un.getText().toString();
                String password = pw.getText().toString();
                dbHandler.updateUser(username, password);
                Toast.makeText(MainActivity.this, "Updated successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
