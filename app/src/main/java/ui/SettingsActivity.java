package ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bbqbuddy.R;

public class SettingsActivity extends AppCompatActivity {
    Switch themeSwitch;
    Switch tempUnitSwitch;
    Switch weightUnitSwitch;

    Button settingsSaveButton;
    Button settingsCancelButton;

    public static final String SHARED_PREFS  = "sharedPrefs";
    public static final String ThemeSwitch = "themeSwitch";
    public static final String TempUnitSwitch = "tempUnitSwitch";
    public static final String WeightUnitSwitch = "weightUnitSwitch";

    private boolean themeSwitchOnOff;
    private boolean tempUnitSwitchOnOff;
    private boolean weightUnitSwitchOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        themeSwitch = findViewById(R.id.themeSwitch);
        tempUnitSwitch = findViewById(R.id.tempUnitSwitch);
        weightUnitSwitch = findViewById(R.id.weightUnitSwitch);

        settingsCancelButton = findViewById(R.id.settingsCancelButton);
        settingsSaveButton = findViewById(R.id.settingsSaveButton);

        setupSwitches();
        setupButtons();
        loadData();
        updateViews();
    }

    private void setupSwitches(){
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


            }
        });

        tempUnitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        weightUnitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    public void setupButtons(){
        settingsSaveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                saveData();
            }
        });


        settingsCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return true;
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(ThemeSwitch,themeSwitch.isChecked());
        editor.putBoolean(TempUnitSwitch,tempUnitSwitch.isChecked());
        editor.putBoolean(WeightUnitSwitch,weightUnitSwitch.isChecked());

        editor.apply();

        Boolean thmChkd = themeSwitch.isChecked();
        Boolean tmpChkd = tempUnitSwitch.isChecked();
        Boolean wtChkd = weightUnitSwitch.isChecked();

        Intent settingsIntent = new Intent(this, MainActivity.class);
        settingsIntent.putExtra("themeKey", thmChkd);
        settingsIntent.putExtra("tempKey", tmpChkd);
        settingsIntent.putExtra("weightKey", wtChkd);
        this.startActivity(settingsIntent);

    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        themeSwitchOnOff = sharedPreferences.getBoolean(ThemeSwitch, false);
        tempUnitSwitchOnOff = sharedPreferences.getBoolean(TempUnitSwitch,false);
        weightUnitSwitchOnOff = sharedPreferences.getBoolean(WeightUnitSwitch,false);
    }
    public void updateViews(){
        themeSwitch.setChecked(themeSwitchOnOff);
        tempUnitSwitch.setChecked(tempUnitSwitchOnOff);
        weightUnitSwitch.setChecked(weightUnitSwitchOnOff);

    }
}
