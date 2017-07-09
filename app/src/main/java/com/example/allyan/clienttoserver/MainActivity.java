package com.example.allyan.clienttoserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.activity_main_editText_name);
        btn = (Button) findViewById(R.id.activity_main_button_send);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String message = editText.getText().toString();
        new ServerThread(message).start();
    }
}
