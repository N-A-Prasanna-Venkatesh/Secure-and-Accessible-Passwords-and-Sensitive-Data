package secure.alarm.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Pass extends AppCompatActivity {
    EditText et_key,et_value;
    Button btn_Update;
    DBHelper DB;
    function fn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        getSupportActionBar().hide();

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
                String encr = fn.caesar(b,15,0).toString();
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

}