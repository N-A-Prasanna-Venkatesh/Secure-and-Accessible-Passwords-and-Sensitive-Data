package secure.alarm.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Pass extends AppCompatActivity {
    EditText et_key,et_value;
    Button btn_Update;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__pass);

        et_key=findViewById(R.id.Update_Key);
        et_value=findViewById(R.id.Update_Value);
        btn_Update=findViewById(R.id.Update);

        DB=new DBHelper(this);

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = et_key.getText().toString();
                String b = et_value.getText().toString();
                String encr = caesar(b,15,0).toString();
                Boolean chk = DB.updatedata(a,encr);
                if(chk)
                {
                    Toast.makeText(Update_Pass.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                }else
                    {
                        Toast.makeText(Update_Pass.this, "Data not Updated", Toast.LENGTH_SHORT).show();
                    }

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