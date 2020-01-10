package com.example.edgar.animales;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
      private final String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/misfotos/";
      private File file = new File(ruta_fotos);
      private Button botonfoto;
      private ImageView img;
        private MediaPlayer cerdo,pollo,vaca;
      Intent intent;
      Bitmap bmp;
      ImageView imaFot;


      final static int cons = 0;

    private TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        // tx = (TextView) findViewById(R.id.textView);
        img = (ImageView)this.findViewById(R.id.verImagen);
        botonfoto = (Button) this.findViewById(R.id.buttonFoto);


        cerdo = MediaPlayer.create(this,R.raw.cerdo);
        pollo = MediaPlayer.create(this,R.raw.pollito);
        vaca = MediaPlayer.create(this,R.raw.vaca);
        //Añadimos el Listener Boton
        botonfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent para llamar a la Camara
                Intent cameraIntent = new Intent(
                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                //Creamos una carpeta en la memeria del terminal
                File imagesFolder = new File(
                        getApplicationContext().getExternalFilesDir(null), "Fotoo");
                imagesFolder.mkdirs();
                System.err.println("Soy el path:"+imagesFolder.getPath());
                //añadimos el nombre de la imagen
                File image = new File(imagesFolder, "foto.jpg");
                Uri uriSavedImage = Uri.fromFile(image);
                //Le decimos al Intent que queremos grabar la imagen
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                //Lanzamos la aplicacion de la camara con retorno (forResult)
                startActivityForResult(cameraIntent, 1);
            }});

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onStart() {
        super.onStart();
        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    10);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_foto, menu);
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

    public void lanzarCerdo(View view){

        String textoCerdo;
        textoCerdo = "El cerdo (Sus scrofa domestica) es una especie de mamífero artiodáctilo de la " +
                "familia Suidae. Es un animal doméstico usado en la alimentación humana por   muchos  " +
                "pueblos. Su nombre científico es Sus scrofa ssp.";
        Intent i = new Intent(this, ActivityCerdo.class );
        i.putExtra("textoCerdo", textoCerdo);
        startActivity(i);
        //Dar estar al sonido
        cerdo.start();
    }

    public void lanzarPollo(View view){
        String textoPollo;
        textoPollo = "Polluelo, pollo o pichón son nombres usados para designar a las crías de las" +
                " aves, llamadas así desde que eclosiona el huevo hasta que aprenden a valerse por sí " +
                "mismas sin necesidad de los cuidados de los adultos, y gracias al desarrollo fisiológico" +
                " correspondiente ";
        Intent i = new Intent(this, ActivityPollo.class );
        i.putExtra("textoPollo", textoPollo);
        startActivity(i);
        //Activa sonido de Pollo
        pollo.start();
    }
    public void lanzarVaca(View view){
        String textoVaca;
        textoVaca = "La vaca, en el caso de la hembra, o toro en el caso del macho (Bos primigenius taurus " +
                "o Bos taurus), es un mamífero artiodáctilo de la familia de los bóvidos. Es el nombre científico " +
                "que se le asignó al animal vacuno doméstico europeo y norasiático,";
        Intent i = new Intent(this, ActivityVaca.class);
        i.putExtra("textoVaca", textoVaca);
        startActivity(i);
        vaca.start();
    }
    public void lanzarFoto(View view){
        Intent i = new Intent(this, ActivityFoto.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {




    }
}



