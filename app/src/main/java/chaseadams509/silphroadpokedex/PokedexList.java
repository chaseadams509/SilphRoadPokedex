package chaseadams509.silphroadpokedex;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.server.converter.StringToIntConverter;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import java.io.FileReader;
import java.io.InputStream;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class PokedexList extends AppCompatActivity {
    private boolean iv_calc;
    private TextView pokedextext;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Intent get_intent = getIntent();
        iv_calc = get_intent.getBooleanExtra("iv_calc", false);

        pokedextext = (TextView) this.findViewById(R.id.PokedexText);
        if (iv_calc)
            pokedextext.setText("Select a Pokemon to calculate their Individual Values.");
        else
            pokedextext.setText("Select a Pokemon to view.");


        InputStream pdata = getResources().openRawResource(R.raw.pokemon_data);
        InputSource psrc = new InputSource(pdata);
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();
        NodeList pokemon_list;
        try {
            String query = "/PokemonGo/Pokemon";
            pokemon_list = (NodeList) xPath.evaluate(query, psrc, XPathConstants.NODESET);
        } catch (Exception e) {

            // ATTENTION: This was auto-generated to implement the App Indexing API.
            // See https://g.co/AppIndexing/AndroidStudio for more information.
            client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
            return;
        }


        for(int i = 0; i < pokemon_list.getLength(); i++) {
            Button pokeButton = new Button(this);
            Element pokemon = (Element)pokemon_list.item(i);
            String pokemon_name;
            String pokemon_num;
            try {
                pokemon_name = getPokemonName(xPath, pokemon);
                pokemon_num = getPokemonNumber(xPath, pokemon);
            } catch (Exception e) {
                pokemon_name = e.getMessage();
                pokemon_num = "ERROR";
            }
            pokeButton.setText(pokemon_num + ": " + pokemon_name);
            pokeButton.setId(i);
            final int num = i+1;
            pokeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    open_entry(v, num);
                }
            });

            LinearLayout ll = (LinearLayout) findViewById(R.id.pokelist);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(pokeButton, lp);
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public String getPokemonName(XPath xPath, Element pokemon) throws Exception {
        String query = "@name";
        String name = xPath.evaluate(query, pokemon);
        return name;
    }
    public String getPokemonNumber(XPath xPath, Element pokemon) throws Exception {
        String query = "@id";
        String num = "#" + xPath.evaluate(query, pokemon);
        return num;
    }
    public void open_entry(View view, int id) {
        Intent intent = new Intent(PokedexList.this, PokedexEntry.class);
        intent.putExtra("id", id);
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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("PokedexList Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
