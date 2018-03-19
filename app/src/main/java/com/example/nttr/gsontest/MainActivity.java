package com.example.nttr.gsontest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //arrayConvert();
        classConvert();
    }

    // クラスをメンバに含むクラス
    class ParentClass {
        int a;
        String b;
        ChildClass c;

        ParentClass() {
            this.a = 100;
            this.b = "XYZ";
            this.c = new ChildClass();
        }

    }

    // 配列のクラス
    class ChildClass {
        int[] d;
        String[] e;

        ChildClass() {
            this.d = new int[]{1, 2, 3, 4, 5};
            this.e = new String[]{"abc", "def", "ghi"};
        }
    }

    // クラス、ArrayListのgson
    void classConvert() {
        Gson gson = new Gson();

        ChildClass ch = new ChildClass();
        ParentClass pa = new ParentClass();
        ArrayList<ChildClass> listCh = new ArrayList<>();
        ArrayList<ParentClass> listPa = new ArrayList<>();

        // 要素は3個ほど
        for (int i = 0; i < 3; i++) {
            listCh.add(new ChildClass());
        }
        listCh.get(1).e[2] = "jkl";

        Log.d("gson-java", listCh.toString());

        Type listType = new TypeToken<ArrayList<ChildClass>>(){}.getType();
        String str_listCh = gson.toJson(listCh, listType);

        Log.d("gson-JSON", "str_listCh="+str_listCh);

        // https://stackoverflow.com/questions/5554217/google-gson-deserialize-listclass-object-generic-type
        //Type listType = new TypeToken<ArrayList<YourClass>>(){}.getType();
        //List<YourClass> yourClassList = new Gson().fromJson(jsonArray, listType);

        //Type listType = new TypeToken<ArrayList<ChildClass>(){}.getType();
        ArrayList<ChildClass> listCh2 = new Gson().fromJson(str_listCh, listType);

        Log.d("gson-java", listCh2.toString());
        Log.d("gson-java", listCh2.get(1).e[2]);

    }

    // 配列のgson
    void arrayConvert() {
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

        Log.d("gson-JSON", "str_ints="+str_ints);
        Log.d("gson-JSON", "str_str"+str_str);
        Log.d("gson-JSON", "str_dints="+str_dints);

        // Deserialization
        int[] ints2 = gson.fromJson(str_ints, int[].class);
        String[] strings2 = gson.fromJson(str_str,String[].class);
        int[][] dints2 = gson.fromJson(str_dints, int[][].class);

        Log.d("gson-java", Arrays.toString(ints2));
        Log.d("gson-java", Arrays.toString(strings2));
        Log.d("gson-java", Arrays.deepToString(dints2));
    }
}
