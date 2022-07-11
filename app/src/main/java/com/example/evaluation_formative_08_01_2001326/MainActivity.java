package com.example.evaluation_formative_08_01_2001326;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.evaluation_formative_08_01_2001326.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity<PaquetsDeCartes> extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private PaquetDeCartes paquet = new PaquetDeCartes();
    private ArrayAdapter<Carte> adaptateur;
    private ArrayList<Carte> main = new ArrayList<>();

    private ListView cartesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        View.OnClickListener annulerOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dernierePos = main.size() - 1;
                Carte carteRetiree = main.remove(dernierePos);

                adaptateur.notifyDataSetChanged();

                Snackbar.make(view, carteRetiree.descriptionAbrégée() + "suprimee.", Snackbar.LENGTH_LONG)
                        .setAction("Annuler", null).show();
            }
        };

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Carte carteAjoutee = ajouterCarte();

                if (carteAjoutee != null)
                Snackbar.make(view, "Il reste "+ paquet.taille() +  " cartes.", Snackbar.LENGTH_LONG)
                        .setAction("Annuler", annulerOnClickListener).show();
                else
                    Snackbar.make(view, "Paquet vide.", Snackbar.LENGTH_LONG)
                            .setAction("Annuler", null).show();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        cartesListView = findViewById(R.id.cartesListViewId);

        adaptateur = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,main);
        cartesListView.setAdapter(adaptateur);

        cartesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Carte carteRetiree = main.remove(i);

                adaptateur.notifyDataSetChanged();

                Snackbar.make(view, carteRetiree.descriptionAbrégée() + "suprimee.", Snackbar.LENGTH_LONG)
                        .setAction("Annuler", null).show();

            }
        });

        for (int i = 0; i < 5; i++)
           ajouterCarte();
    }

    private Carte ajouterCarte()
    {
        Carte carte = paquet.piger();

        if(carte != null)
        {
            main.add(carte);

            adaptateur.notifyDataSetChanged();
            cartesListView.smoothScrollToPosition(adaptateur.getCount());
        }
        return carte;
    }

    private void remiseZero() {
        main.clear();
        paquet =  new PaquetDeCartes();

        for ( int i=0; i < 5; i++) {
            ajouterCarte();
        }
        adaptateur.notifyDataSetChanged();
        cartesListView.smoothScrollToPosition(adaptateur.getCount());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.recommencer) {
            remiseZero();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}