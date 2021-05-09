package secure.alarm.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Generate_Pass extends AppCompatActivity {
    EditText key;
    Button Generate,Add;
    TextView tv_pass;
    int val;
    String pass,keyss;
    ClipData clip;
    DBHelper DB;
    function fn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_generate__pass);

        String cap="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String sma="abcdefghijklmnopqrstuvxyz";
        String num="0123456789";
        String sym="!@#$%^&*()";
        String all = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+ "0123456789"+ "abcdefghijklmnopqrstuvxyz"+"!@#$%^&*()";
        val=0;
        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        DB=new DBHelper(this);


        key=findViewById(R.id.Gen_Key);
        Generate=findViewById(R.id.Generate_Pass);
        Add=findViewById(R.id.Add_GeneratedPass);
        tv_pass=findViewById(R.id.GeneratedPass);
        
        Generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val=1;
                StringBuilder sb = new StringBuilder(12);
                for (int i = 0; i < 8; i++) {
                    int index = (int)(all.length()*Math.random());
                    sb.append(all.charAt(index));
                }
                int index = (int)(cap.length()*Math.random());
                sb.append(cap.charAt(index));
                 index = (int)(sma.length()*Math.random());
                sb.append(sma.charAt(index));
                 index = (int)(num.length()*Math.random());
                sb.append(num.charAt(index));
                 index = (int)(sym.length()*Math.random());
                sb.append(sym.charAt(index));

                pass = sb.toString();
                tv_pass.setText(pass);
                Add.setVisibility(View.VISIBLE);
                Toast.makeText(Generate_Pass.this, "Generate Button pressed", Toast.LENGTH_SHORT).show();
            }
        });

        tv_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(val==1)
                {
                    clip=ClipData.newPlainText("sensitive",pass);
                    clipboardManager.setPrimaryClip(clip);
                }
            }
        });
        
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(key.getText().toString().equals(""))
                {
                    Toast.makeText(Generate_Pass.this, "To Add details please fill a key", Toast.LENGTH_SHORT).show();
                }else
                    {
                        keyss=key.getText().toString();
                        String value_pass=fn.caesar(pass,15,0).toString();
                        Boolean chkinsert = DB.insertdata(keyss,value_pass);
                        if(chkinsert)
                        {
                            Toast.makeText(Generate_Pass.this, "Successfully inserted data", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(Generate_Pass.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                        }

                    }

            }
        });
    }

}