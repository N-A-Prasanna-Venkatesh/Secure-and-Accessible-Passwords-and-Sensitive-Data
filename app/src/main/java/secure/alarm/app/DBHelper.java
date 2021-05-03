package secure.alarm.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "sensitive.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table sensitive(keys TEXT , value TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists sensitive");
    }
//For inserting data....
    public Boolean insertdata(String key, String value)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("keys", key);
        contentValues.put("value", value);
        long result=DB.insert("sensitive", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateuserdata(String key, String value) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", value);
        Cursor cursor = DB.rawQuery("Select * from sensitive where keys = ?", new String[]{key});
        if (cursor.getCount() > 0) {
            long result = DB.update("sensitive", contentValues, "keys=?", new String[]{key});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}


    public Boolean deletedata (String key)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from sensitive where keys = ?", new String[]{key});
        if (cursor.getCount() > 0) {
            long result = DB.delete("sensitive", "keys=?", new String[]{key});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }




//Fore reading the data....
    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from sensitive", null);
        return cursor;

    }


}
