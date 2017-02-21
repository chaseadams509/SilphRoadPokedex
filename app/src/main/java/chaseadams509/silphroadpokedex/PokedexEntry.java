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

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.InputStream;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

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

        InputStream pdata = getResources().openRawResource(R.raw.pokemon_data);
        InputSource psrc = new InputSource(pdata);
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();
        NodeList pokemon_list;
        try {
            String query = "/PokemonGo/Pokemon[@id=" + id + "]";
            pokemon_list = (NodeList) xPath.evaluate(query, psrc, XPathConstants.NODESET);
            Element entry = (Element)pokemon_list.item(0);

            pokemonName.setText("#" + id + ": " + getPokemonAttribute(xPath, entry, "@name"));
            attackStat.setText("ATK: " + getPokemonAttribute(xPath, entry, "Stats/@attack"));
            defenseStat.setText("DEF: " + getPokemonAttribute(xPath, entry, "Stats/@defense"));
            staminaStat.setText("STA: " + getPokemonAttribute(xPath, entry, "Stats/@stamina"));
        } catch (Exception e) {

            pokemonName.setText("#" + id + ": NOT FOUND");
            attackStat.setText("ATK: 0");
            defenseStat.setText("DEF: 0");
            staminaStat.setText("STA: 0");
        }
    }

    public String getPokemonAttribute(XPath xPath, Element pokemon, String query) throws Exception {
        String attrib = xPath.evaluate(query, pokemon);
        return attrib;
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
