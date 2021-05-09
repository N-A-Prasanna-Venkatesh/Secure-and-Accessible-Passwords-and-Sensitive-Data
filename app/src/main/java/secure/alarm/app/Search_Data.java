package secure.alarm.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.database.Cursor;
import android.opengl.ETC1;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Search_Data extends AppCompatActivity {
    EditText et_Search;
    Button btn_Search;
    GridView gv1;
    String str;
    DBHelper DB;
    Cursor cursor;
    int n,val;
    ArrayList<String> data;
    int[] arr;
    String[] gridData;
    ClipData clip;
    function fn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_search__data);

        et_Search=findViewById(R.id.Search_Key);
        btn_Search=findViewById(R.id.Search);
        gv1=findViewById(R.id.GV1);
        DB = new DBHelper(this);
        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);

        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = et_Search.getText().toString();
                //cursor=DB.fetch_data(str);
                cursor = DB.getdata();
                if(cursor.getCount()==0)
                {
                    Toast.makeText(Search_Data.this, "No Match Found", Toast.LENGTH_SHORT).show();

                }else
                    {
                        data = new ArrayList<>();

                        while (cursor.moveToNext())
                        {
                            String str1 = cursor.getString(0);
                            if (str1.indexOf(str)!=-1){
                                data.add(str1);
                                data.add(cursor.getString(1));
                            }


                            /*data.add(cursor.getString(0));
                            data.add(cursor.getString(1));*/
                        }
                        n=data.size();
                        arr = new int[n];
                        gridData =data.toArray(new String[n]);
                        ArrayAdapter<String> adapter=new ArrayAdapter<String>(Search_Data.this, android.R.layout.simple_list_item_1,gridData);
                        gv1.setAdapter(adapter);


                    }



            }
        });
        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener()
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
                        String str1 = fn.caesar(str,11,1).toString();
                        ((TextView)view).setText(str1);
                        //ClipBoard
                        clip= ClipData.newPlainText("sensitive",str1);
                        clipboardManager.setPrimaryClip(clip);
                        Toast.makeText(Search_Data.this, "Copied.", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        String str1=fn.caesar(str,15,0).toString();
                        ((TextView)view).setText(str1);
                        arr[position]-=1;
                    }


                }
            }
        });


    }
}