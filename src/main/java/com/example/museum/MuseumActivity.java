package com.example.museum;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.net.URI;
import java.text.DecimalFormat;

public class MuseumActivity extends AppCompatActivity {
    private String currencyFormat(Double d) {
        DecimalFormat format = new DecimalFormat("#,###,##0.00");
        return format.format(d);
    }
    final int ticketMax = 5;
    final double nycTax = 0.08875;
    final double adultPrice = 23;
    final double studentPrice = 15;
    final double seniorPrice = 18;
    Toast ticketWarning;
    int ticketCount = 0;
    Spinner studentTiks;
    Spinner seniorTiks;
    Spinner adultTiks;
    Button calculate;
    ImageView museumImg;
    Intent intent;
    
    
    @Override
    /*
    What to do upon starting the gui view
    @param Bundle  saved Instance State
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_museum);
        museumImg = (ImageView) findViewById(R.id.imageView);
        TextView museumName = (TextView) findViewById(R.id.museumName);
         studentTiks = (Spinner)findViewById(R.id.student_spinner);
         seniorTiks = (Spinner)findViewById(R.id.senior_spinner);
        adultTiks = (Spinner)findViewById(R.id.adult_spinner);
         intent = getIntent();
        museumName.setText(intent.getExtras().getString("MuseumName"));
        museumImg.setImageResource(((intent.getExtras().getInt("MuseumImg"))));
        ticketWarning = Toast.makeText(this, getString(R.string.ticket_warning), Toast.LENGTH_SHORT);
        ticketWarning.show();
        TextView subtotal = (TextView)findViewById(R.id.Subtotal);
        subtotal.setText(getString(R.string.subtotal)+ " $0.00");
        TextView tax = (TextView)findViewById(R.id.Tax);
        tax.setText(getString(R.string.tax)+ " $0.00");
        TextView total = (TextView)findViewById(R.id.Total);
        total.setText(getString(R.string.total)+ " $0.00");

        calculate = (Button)findViewById(R.id.button);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView subtotal = (TextView)findViewById(R.id.Subtotal);
                double price = (studentTiks.getSelectedItemPosition() * studentPrice) + (seniorTiks.getSelectedItemPosition() * seniorPrice) + (adultTiks.getSelectedItemPosition() * adultPrice);
                subtotal.setText(getString(R.string.subtotal) + " $" + currencyFormat(price) );
                TextView tax = (TextView)findViewById(R.id.Tax);
                tax.setText(getString(R.string.tax) + " $" + currencyFormat(price * nycTax) );
                TextView total = (TextView)findViewById(R.id.Total);
                total.setText(getString(R.string.total)+ " $" + currencyFormat(price*(1 + nycTax)) );
            }
        });
    }
    /*
      Method used when image is clicked
      @param View  needed for gui on click action
     */
    public void goToWebsite (View view) {
       
        String url = intent.getExtras().getString("url");
        Uri uri = Uri.parse( url);
        Intent urlIntent =  new Intent(Intent.ACTION_VIEW, uri);
        startActivity(urlIntent);
    }
    
}
