package com.example.nttr.gsontest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new Gson();
        int[] ints = {1, 2, 3, 4, 5};
        String[] strings = {"abc", "def", "ghi"};
        int[][] dints = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
        };

        Log.d("gson-java", Arrays.toString(ints));
        Log.d("gson-java", Arrays.toString(strings));
        Log.d("gson-java", Arrays.deepToString(dints));

        // Serialization
        String str_ints = gson.toJson(ints);     // ==> [1,2,3,4,5]
        String str_str = gson.toJson(strings);  // ==> ["abc", "def", "ghi"]
        String str_dints = gson.toJson(dints);

        Log.d("Gson-JSON", "str_ints="+str_ints);
        Log.d("Gson-JSON", "str_str"+str_str);
        Log.d("Gson-JSON", "str_dints="+str_dints);

        // Deserialization
        int[] ints2 = gson.fromJson(str_ints, int[].class);
        String[] strings2 = gson.fromJson(str_str,String[].class);
        int[][] dints2 = gson.fromJson(str_dints, int[][].class);

        Log.d("gson-java", Arrays.toString(ints2));
        Log.d("gson-java", Arrays.toString(strings2));
        Log.d("gson-java", Arrays.deepToString(dints2));

    }
}
