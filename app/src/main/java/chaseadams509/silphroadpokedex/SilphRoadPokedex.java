package chaseadams509.silphroadpokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Interpolator;
import android.widget.Button;

public class SilphRoadPokedex extends AppCompatActivity {
    private Button pokedexlist;
    private Button ivlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silph_road_pokedex);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pokedexlist = (Button)this.findViewById(R.id.Pokedex);
        pokedexlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_pokedex_list(v, false);
            }
        });

        ivlist = (Button)this.findViewById(R.id.IVCalc);
        ivlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_pokedex_list(v, true);
            }
        });
    }

    public void open_pokedex_list(View view, boolean iv_calc) {
        Intent intent = new Intent(SilphRoadPokedex.this, PokedexList.class);
        intent.putExtra("iv_calc", iv_calc);
        startActivity(intent);
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
