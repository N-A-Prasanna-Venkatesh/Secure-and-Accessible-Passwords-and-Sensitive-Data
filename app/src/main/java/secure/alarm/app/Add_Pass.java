package secure.alarm.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Pass extends AppCompatActivity {
    EditText key,value;
    Button add,show;
    DBHelper DB;
    function fn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_add__pass);
        
        key=findViewById(R.id.add_key);
        value=findViewById(R.id.add_Value);
        add=findViewById(R.id.Add_Key_Value);
        show=findViewById(R.id.show);

        DB=new DBHelper(this);
        
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Add_Pass.this, "Button pressed", Toast.LENGTH_SHORT).show();
                String keys= key.getText().toString();
                String values= value.getText().toString();
                values=fn.caesar(values,15,0).toString();
                Boolean chkinsert = DB.insertdata(keys,values);
                if(chkinsert)
                {
                    Toast.makeText(Add_Pass.this, "Successfully inserted data", Toast.LENGTH_SHORT).show();
                }else
                    {
                        Toast.makeText(Add_Pass.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(Add_Pass.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Key :"+res.getString(0)+"\n");
                    buffer.append("Value :"+res.getString(1)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Add_Pass.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }


        });

    }
}