package com.example.signup;

import android.os.Bundle;
import android.util.Xml;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;

public class NewActivity extends AppCompatActivity {

    private EditText new_et_name;
    private EditText new_et_pwd;
    private EditText new_et_age;

    private String name;
    private String pwd;
    private String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);

        new_et_name=(EditText)findViewById(R.id.new_et_name);
        new_et_age=(EditText)findViewById(R.id.new_et_age);
        new_et_pwd=(EditText)findViewById(R.id.new_et_pwd);

        getInfo();
    }


    private void getInfo(){

        XmlPullParser pullParser= Xml.newPullParser();

        try {
            File file=new File(getFilesDir(),"info.xml");

            FileInputStream fis=new FileInputStream(file);

            pullParser.setInput(fis,"utf-8");

            int eventType = pullParser.getEventType();

            while (eventType!=pullParser.END_DOCUMENT){

                if(eventType==pullParser.START_TAG){

                    if(pullParser.getName().equalsIgnoreCase("name")){
                        name=pullParser.nextText();
                    }
                    if(pullParser.getName().equalsIgnoreCase("pwd")){
                        pwd=pullParser.nextText();
                    }
                    if(pullParser.getName().equalsIgnoreCase("age")){
                        age=pullParser.nextText();
                    }
                }
                System.out.println("eventType=="+eventType);

                eventType=pullParser.next();
            }

            new_et_name.setText(name);
            new_et_pwd.setText(pwd);
            new_et_age.setText(age);

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}