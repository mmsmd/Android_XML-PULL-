package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_pwd;
    private EditText et_age;
    private CheckBox cb;

    Button bt;

    private String name;
    private String pwd;
    private String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name=(EditText)findViewById(R.id.et_name);
        et_age=(EditText)findViewById(R.id.et_age);
        et_pwd=(EditText)findViewById(R.id.et_pwd);
        cb=(CheckBox) findViewById(R.id.cb);
        bt=(Button) findViewById(R.id.bt);

    }


    public void onCacl(View view){
        Intent intent=new Intent();
        intent.setAction("com.mao.data");
        intent.addCategory("android.intent.category.DEFAULT");

        signup(view);
        startActivity(intent);
    }


    public void signup(View view){

        name=et_name.getText().toString().trim();
        pwd=et_pwd.getText().toString().trim();
        age=et_age.getText().toString().trim();

        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)||TextUtils.isEmpty(age)){
            Toast.makeText(this,"账号密码不能为空",Toast.LENGTH_LONG).show();
        }else if(cb.isChecked()){
            saveInfo(name,pwd,age);
        }else {
            Toast.makeText(this,"记住密码没有被勾选！",Toast.LENGTH_LONG).show();
        }
    }

    private void saveInfo(String name,String pwd,String age){

        XmlSerializer serializer= Xml.newSerializer();

        try {
            File file=new File(this.getFilesDir(),"info.xml");
            FileOutputStream fos=new FileOutputStream(file);

            serializer.setOutput(fos,"utf-8");

            serializer.startDocument("utf-8",true);


            serializer.startTag("","info");
//          用户名
            serializer.startTag("","name");
            serializer.text(name);
            serializer.endTag("","name");
//          密码
            serializer.startTag("","pwd");
            serializer.text(pwd);
            serializer.endTag("","pwd");
//          年龄
            serializer.startTag("","age");
            serializer.text(age);
            serializer.endTag("","age");

            serializer.endTag("","info");

            serializer.endDocument();

            Toast.makeText(this,"信息保存成功",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"信息保存失败",Toast.LENGTH_LONG).show();
        }
    }


}
