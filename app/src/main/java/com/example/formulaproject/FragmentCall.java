package com.example.formulaproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class FragmentCall extends Fragment {
    View v;
    ArrayList<RaceItem> arr;
    private RecyclerView rc;
    ArrayList<String> locations,date,time;
    CustomAdapter ca;
    ArrayList<String> res;
    public static String str="";
    TabLayout tl;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private RecyclerView rv;
    public FragmentCall() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.race_fragment,container,false);
        rc=(RecyclerView) v.findViewById(R.id.rv);
        rc.setLayoutManager(new LinearLayoutManager(getActivity()));
        ca=new CustomAdapter(arr);
        rc.setAdapter(ca);


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res=new ArrayList<>();
        arr=new ArrayList<>();


        fetchData pro=new fetchData();
        pro.execute();
        Log.d("Hea",str);
        try {
            str=pro.get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            JSONObject reader=new JSONObject(str);
            JSONArray temp= reader.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");
            for(int i=0;i<temp.length();i++)
            {
                JSONObject rec=temp.getJSONObject(i);
                String loc=rec.getString("raceName");
                String dte=rec.getString("date");
                String ti=rec.getString("time");
                RaceItem race=new RaceItem(loc,dte,ti,R.drawable.monza);
                arr.add(race);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    class fetchData extends AsyncTask<URL,Void,String>
    {   String data="";

        @Override
        protected String doInBackground(URL... urls) {
            try {
                URL url=new URL("https://ergast.com/api/f1/current.json");
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String line="";
                while(line!=null)
                {
                    line=bufferedReader.readLine();
                    data=data+line;


                }

                res.add(data);
                Log.d("Lines",res.get(0));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            str=s;

        }
    }
}
