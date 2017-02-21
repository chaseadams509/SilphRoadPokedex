package chaseadams509.silphroadpokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PokedexEntry extends AppCompatActivity {
    private ImageView pokemonGif;
    private TextView pokemonName;
    private ImageView buddyKm;
    private ImageView typeOne;
    private ImageView typeTwo;
    private TextView attackStat;
    private TextView defenseStat;
    private TextView staminaStat;
    private int id;
    private boolean iv_calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Intent get_intent = getIntent();
        id = get_intent.getIntExtra("id", 0);
        iv_calc = get_intent.getBooleanExtra("iv_calc", false);

        pokemonGif = (ImageView)this.findViewById(R.id.dex_image);
        pokemonName = (TextView)this.findViewById(R.id.dex_name);
        buddyKm = (ImageView)this.findViewById(R.id.dex_buddy);
        typeOne = (ImageView)this.findViewById(R.id.type1);
        typeTwo = (ImageView)this.findViewById(R.id.type2);
        attackStat = (TextView)this.findViewById(R.id.dex_atk);
        defenseStat = (TextView)this.findViewById(R.id.dex_def);
        staminaStat = (TextView)this.findViewById(R.id.dex_sta);

        pokemonName.setText("#" + id + ": POKEMON");
        attackStat.setText("ATK: 0");
        defenseStat.setText("DEF: 0");
        staminaStat.setText("STA: 0");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_silph_road_pokedex, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
