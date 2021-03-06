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

public class Change extends AppCompatActivity {
    EditText et1,et2,et3;
    Button btn;
    String s1,s2,s3;
    public static final String MyPreferences = "Preferences";
    public static final String Pass = "pass";
    String code;
    function fn;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_change);

        et1=findViewById(R.id.Prev_Pass);
        et2=findViewById(R.id.new_pass);
        et3=findViewById(R.id.conf_pass);
        btn=findViewById(R.id.btn_chng);

        sharedPreferences=getSharedPreferences(MyPreferences,MODE_PRIVATE);
        code=sharedPreferences.getString(Pass,"7777");
        code=fn.caesar(code,19,1).toString();//Decrypting the stored value.

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=et1.getText().toString();
                s2=et2.getText().toString();
                s3=et3.getText().toString();
                if(s2.equals(s3)){
                    if(code.equals(s1)){
                        String code = fn.caesar(s2,7,0).toString();                 //Shift of 7 positions.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(Pass,code);
                        editor.commit();
                        Toast.makeText( Change.this, "Password successfully changed", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Change.this, "Incorrect passwords", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(Change.this, "Please Type the new Password correctly", Toast.LENGTH_SHORT).show();
                }
                
                Intent intent = new Intent(Change.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }


}