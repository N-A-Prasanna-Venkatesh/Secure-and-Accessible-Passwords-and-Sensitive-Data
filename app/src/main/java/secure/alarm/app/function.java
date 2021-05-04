package secure.alarm.app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

public class function {
    //encrypt the data..
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

    //Copy to ClipBoard....
   /* public void copy(String str)
    {
        ClipData clip;
        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        clip=ClipData.newPlainText("sensitive",str);
        clipboardManager.setPrimaryClip(clip);
    }*/
}
