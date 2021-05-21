package com.example.database;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends Activity { /** Called when the activity is first created. */ Button save,load;
    EditText message;
    TextView t1;
    String Message1;
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save=(Button) findViewById(R.id.button1);
        load=(Button) findViewById(R.id.button2);
        message=(EditText) findViewById(R.id.editText1); t1=(TextView) findViewById(R.id.textView1); save.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
//Get message from user store in message1 variable Message1 =message.getText().toString();
            try{
//Create a new folder called MyDirectory in SDCard
               File sdcard=Environment.getExternalStorageDirectory();
               File directory=new File(sdcard.getAbsolutePath()+"/MyDirectory");
                directory.mkdirs();
//Create a new file name textfile.txt inside MyDirectory
 File file=new File(directory,"textfile.txt"); //Create File Outputstream to read the file
                 FileOutputStream fou=new FileOutputStream(file); OutputStreamWriter osw=new OutputStreamWriter(fou); try{
//write a user data to file
                osw.append(Message1);
                osw.flush();
                osw.close();
                Toast.makeText(getBaseContext(),"DatSaved",Toast.LENGTH_LONG).show();
            }catch(IOException e){
                e.printStackTrace();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        }
    });
load.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
        try{
            File sdcard=Environment.getExternalStorageDirectory(); File directory=new File(sdcard.getAbsolutePath()+"/MyDirectory"); File file=new File(directory,"textfile.txt"); FileInputStream fis=new FileInputStream(file); InputStreamReader isr=new InputStreamReader(fis); char[] data=new char[100];
            String final_data="";
            int size;
            try{
                while((size=isr.read(data))>0)
                {
//read a data from file
                    String read_data=String.copyValueOf(data,0,size); final_data+=read_data;
                    data=new char[100];
                }
//display the data in output
                Toast.makeText(getBaseContext(),"Message:"+final_data,Toast.LENGTH_LONG).show();
            }catch(IOException e){
                e.printStackTrace();
            }}catch (FileNotFoundException e){
            e.printStackTrace();
        }}});}}
