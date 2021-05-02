package secure.alarm.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button nxt,chng;
    EditText pass;
    public static final String MyPreferences = "Preferences";
    public static final String Pass = "pass";
    String code,entered_code;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        nxt=findViewById(R.id.Enter_Alarm);
        chng=findViewById(R.id.Chng_Pass);
        pass=findViewById(R.id.Passcode);

        sharedPreferences=getSharedPreferences(MyPreferences,MODE_PRIVATE);

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entered_code=pass.getText().toString().trim();
                code=sharedPreferences.getString(Pass,"7777");
                //Toast.makeText(MainActivity.this, code, Toast.LENGTH_SHORT).show();//Encrypted password is shown here.
                code=caesar(code,19,1).toString();       //decrypting the stored encrypted value
                //Toast.makeText(MainActivity.this, code, Toast.LENGTH_SHORT).show();//Original Password to type(The decrypted one)
                if(entered_code.equals(code)){
                    Intent intent = new Intent(MainActivity.this,Alarm_Set.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Sorry Bro This is classified", Toast.LENGTH_SHORT).show();
                }
            }
        });
        chng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Change.class);
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

                b=(b+s1)%10;
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