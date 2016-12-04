package com.adsvantage.activepoints.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by HP on 02/12/2016.
 */

public class IntroManager {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context context;

    public IntroManager(Context context){
        this.context = context;
        prefs = context.getSharedPreferences("first_time_run", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setFirst(Boolean isFirst){
        editor.putBoolean("first_time_run", isFirst);
        editor.commit();
    }

    public boolean check(){
        return prefs.getBoolean("first_time_run", true);
    }

}
