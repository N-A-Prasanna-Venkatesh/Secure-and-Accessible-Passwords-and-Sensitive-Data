package secure.alarm.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import secure.alarm.app.DBHelper;

public class Data extends AppCompatActivity {
    GridView gridView;
    Button gen_pass,more,refresh;
    DBHelper DB;
    int a,n,val;
    int[] arr;
    String[] gridData;
    ClipData clip;
    ArrayList<String> data;
    function fn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_data);

        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);

        data = new ArrayList<String>();
        gridView=findViewById(R.id.grid_view);
        gen_pass=findViewById(R.id.Gen_Pass);
        more = findViewById(R.id.More);
        refresh=findViewById(R.id.Refresh);

        DB=new DBHelper(this);
        Cursor res = DB.getdata();
        if(res.getCount()==0){
            Toast.makeText(Data.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            a=0;
        }else
            {
                a=1;
                while(res.moveToNext()){
                    data.add(res.getString(0));
                    data.add(res.getString(1));
                }
            }

        if(a==1)
        {
            n=data.size();
            arr = new int[n];

            gridData =data.toArray(new String[n]);
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(Data.this, android.R.layout.simple_list_item_1,gridData);
            gridView.setAdapter(adapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String str=((TextView)view).getText().toString();
                    if(position%2==1)
                    {
                        val=arr[position];
                        //Toast.makeText(Data.this, Integer.toString(val), Toast.LENGTH_SHORT).show();
                        if(val==0)
                        {
                            arr[position]+=1;
                            String str1 = caesar(str,11,1).toString();
                            ((TextView)view).setText(str1);
                            //ClipBoard
                            clip=ClipData.newPlainText("sensitive",str1);
                            clipboardManager.setPrimaryClip(clip);
                            Toast.makeText(Data.this, "Copied.", Toast.LENGTH_SHORT).show();
                        }else
                            {
                                String str1=caesar(str,15,0).toString();
                                ((TextView)view).setText(str1);
                                //Toast.makeText(Data.this, str1, Toast.LENGTH_SHORT).show();
                                arr[position]-=1;
                            }


                    }
                }
            });
        }




        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = new ArrayList<String>();
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(Data.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    a=0;
                }else
                {
                    a=1;
                    while(res.moveToNext()){
                        data.add(res.getString(0));
                        data.add(res.getString(1));
                    }
                }

                if(a==1) {
                    n = data.size();
                    int[] arr = new int[n];


                    String[] gridData = data.toArray(new String[n]);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Data.this, android.R.layout.simple_list_item_1, gridData);
                    gridView.setAdapter(adapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                    {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String str=((TextView)view).getText().toString();
                            if(position%2==1)
                            {
                                val=arr[position];
                                //Toast.makeText(Data.this, Integer.toString(val), Toast.LENGTH_SHORT).show();
                                if(val==0)
                                {
                                    String str1 = caesar(str,11,1).toString();
                                    ((TextView)view).setText(str1);
                                    //Toast.makeText(Data.this, str1, Toast.LENGTH_SHORT).show();
                                    clip=ClipData.newPlainText("sensitive",str1);
                                    clipboardManager.setPrimaryClip(clip);
                                    Toast.makeText(Data.this, "Copied.", Toast.LENGTH_SHORT).show();
                                    arr[position]+=1;
                                }else
                                {
                                    String str1=caesar(str,15,0).toString();
                                    ((TextView)view).setText(str1);
                                    //Toast.makeText(Data.this, str1, Toast.LENGTH_SHORT).show();
                                    arr[position]-=1;
                                }


                            }
                        }
                    });

                }
            }
        });
        gen_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Data.this,Generate_Pass.class);
                startActivity(intent);
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Data.this,More.class);
                startActivity(intent);
            }
        });
    }

    public static StringBuffer caesar(String text, int s,int msg)
    {
        StringBuffer result= new StringBuffer();
        int s1;
        if(msg==0)
        {
            s1 =s%10;
        }else
        {
            s1 = 26-s;
            s1=10-s1;
            s1=s1%10;
        }

        for(int i=0;i<text.length();i++)
        {
            char ch = text.charAt(i);
            if(Character.isDigit(ch))
            {
                int b = (int)text.charAt(i);
                b-=48;

                b=(b+s1+10)%10;
                b+=48;
                char ch1 = (char)b;
                result.append(ch1);
            }else
            {
                if(Character.isUpperCase(ch))
                {
                    char ch1 = (char) (((int) text.charAt(i) + s - 65) % 26 + 65);
                    result.append(ch1);
                }else
                {
                    if(Character.isLowerCase(ch))
                    {
                        char ch1 = (char) (((int) text.charAt(i) + s - 97) % 26 + 97);
                        result.append(ch1);
                    }else
                    {
                        result.append(text.charAt(i));
                    }
                }
            }
        }

        return result;
    }

}