package com.example.museum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private String[] museums;
    
   
    @Override
     /*
   What to do upon starting the gui view
   @param Bundle  saved Instance State
    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent secondActivity = new Intent(this, MuseumActivity.class);
        ListView museumList = (ListView) findViewById(R.id.museumList);
        museumList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String museumName = (String)parent.getItemAtPosition(position);
                int museumImg;
                String url;
                switch (position) {
                    case 1:
                        museumImg = R.drawable.modern;
                        url = "https://www.moma.org/";
                        break;
                    case 2:
                        museumImg = R.drawable.art;
                        url = "https://www.amnh.org/";
                        break;
                    case 3:
                        museumImg = R.drawable.brklyn;
                        url = "https://www.brooklynmuseum.org/";
                        break;
                    case 4:
                       museumImg = R.drawable.met;
                       url = "https://www.metmuseum.org/visit/plan-your-visit/met-cloisters";
                        break;
                    default:
                        museumImg = R.drawable.new_muse;
                        url =  "https://www.newmuseum.org/";
                }
                secondActivity.putExtra("MuseumName", museumName);
                secondActivity.putExtra("MuseumImg", museumImg);
                secondActivity.putExtra("url", url);
                startActivity(secondActivity);
            }
        });
    }
}