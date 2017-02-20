package css.cis3334.heartratetracker;

import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView lvHeartRates;
    TextView tvSelect;
    HeartRateList heartRateList;
    ArrayAdapter<HeartRate> hrAdapter;

    //ArrayList<HeartRate> basicheartRateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSelect = (TextView) findViewById(R.id.textViewSelect);
        lvHeartRates = (ListView) findViewById(R.id.listViewHeartRates);

        heartRateList = new HeartRateList();
        heartRateList.InitRandomElderly();

        //ArrayAdapter<HeartRate> planetAdapter = new ArrayAdapter<HeartRate>(this, android.R.layout.simple_list_item_1, basicheartRateList);
        hrAdapter = new HeartRateAdapter(this, R.layout.heart_rate_row, R.id.textViewPulse, heartRateList);
        hrAdapter.setDropDownViewResource(R.layout.heart_rate_row);
        lvHeartRates.setAdapter(hrAdapter);

        lvHeartRates.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                HeartRate hr = (HeartRate) parent.getItemAtPosition(position);
                tvSelect.setText("You selected: " + hr.toString() + ": " + hr.getRangeDescrtiption());

                if(hr.toString().contains("Moderate")){
                    tvSelect.setTextColor(ResourcesCompat.getColor(getResources(), R.color.blue, null));
                    tvSelect.setTypeface(Typeface.SERIF);
                } else if(hr.toString().contains("Aerobic")){
                    tvSelect.setTextColor(ResourcesCompat.getColor(getResources(), R.color.darkorange, null));
                    tvSelect.setTypeface(Typeface.DEFAULT);
                } else if(hr.toString().contains("Endurance")){
                    tvSelect.setTextColor(ResourcesCompat.getColor(getResources(), R.color.orange, null));
                    tvSelect.setTypeface(Typeface.DEFAULT_BOLD);
                } else if(hr.toString().contains("Anaerobic")){
                    tvSelect.setTextColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
                    tvSelect.setTypeface(Typeface.DEFAULT);
                } else if(hr.toString().contains("Resting")){
                    tvSelect.setTextColor(ResourcesCompat.getColor(getResources(), R.color.purple, null));
                    tvSelect.setTypeface(Typeface.MONOSPACE);
                } else if(hr.toString().contains("Red zone")){
                    tvSelect.setTextColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
                    tvSelect.setTypeface(Typeface.SANS_SERIF);
                }
            }
        });

    }
}
