package com.example.gamegallery.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.gamegallery.R;
import com.example.gamegallery.domain.Info;
import com.example.gamegallery.domain.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private MainFragment fragment;

    @Override
    protected void onRestart() {
        super.onRestart();
        this.recreate();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),LoginActivity.class);

                view.getContext().startActivity(intent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment = new MainFragment();
        fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();

        descargarDatos();

        updateUsuario(Info.Companion.getUsuarioActual());

        if(Info.Companion.getUsuarioActual().getUsuario().equals("default") && Info.Companion.getValido()==false){
            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
        }


    }

    private void descargarDatos(){
        if(!Info.Companion.getValido())
            new DownloadDataTask().execute();
    }

    private class DownloadDataTask extends AsyncTask<Void,Integer,Void> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            this.progressDialog = new ProgressDialog(MainActivity.this);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();

        }

        protected void onProgressUpdate(Integer... progress) {
            progressDialog.setProgress(progress[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Info.Companion.cargarDatos();
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            Info.Companion.setValido(true);
            this.progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "Contenido Descargado", Toast.LENGTH_SHORT).show();

        }
    }

    public void repintar(){
      getSupportFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id==R.id.action_settings){
            Intent intent = new Intent(this.getApplicationContext(),SettingsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.getApplicationContext().startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (menuItem.getItemId()){
            case R.id.nav_genre_Action:
                filtar("Accion",fragmentManager);
                break;
            case R.id.nav_home:
                filtar("All",fragmentManager);
                break;
            case R.id.nav_genre_Race:
                filtar("Carreras",fragmentManager);
                break;
            case R.id.nav_genre_RPG:
                filtar("RPG",fragmentManager);
                break;
            case R.id.nav_genre_Shooter:
                filtar("Shooter",fragmentManager);
                break;
            case R.id.nav_genre_Sport:
                filtar("Deportes",fragmentManager);
                break;
            case R.id.nav_genre_Terror:
                filtar("Terror",fragmentManager);
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void filtar(String genero,FragmentManager fragmentManager){
        Info.Companion.setGenero(genero);
        fragment=new MainFragment();
        fragmentManager.beginTransaction().replace(R.id.contenedor,fragment).commit();

    }

    public void updateUsuario(Usuario usuario){
        NavigationView nv = findViewById(R.id.nav_view);
        View v = nv.getHeaderView(0);
        TextView txtUsuario = v.findViewById(R.id.txtUsuarioHeader);
        txtUsuario.setText(usuario.getUsuario());
        TextView txtCorreo = v.findViewById(R.id.txtCorreoHeader);
        txtCorreo.setText(usuario.getCorreo());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Info.Companion.actualizarPreferenciasUsuario();
    }
}
