package ryhma1.hyteprojekti;

import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.concurrent.RunnableFuture;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseHelper myDb;

    private BluetoothAdapter BA;    /*Luodaan Bluetooth adapteri*/
    private Set<BluetoothDevice>pairedDevices;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    NavigationView navigationView = null;
    ListView lv;
    EditText message;
    TextView myLabel;
    EditText myTextbox;
    BluetoothAdapter BTAdapter;
    BluetoothDevice BTDevice;
    BluetoothSocket BTSocket;
    InputStream BTInputStream;
    OutputStream BTOutputStream;
    Thread BTThread;
    byte[] readBuffer;
    int readBufferPos;
    int counter;
    volatile boolean stopWorker;
    static final int READ_BLOCK_SIZE =100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        //set the fragment initially
        MainFragment fragment = new MainFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        BA = BluetoothAdapter.getDefaultAdapter();
        lv = (ListView)findViewById(R.id.listView);


    /*      User datan napista lisäämisen logiikka

            public void AddUserData(){
                addUserDataButton.setOnClickListener(
                        new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean isInserted = myDb.insertUserData(arg1,arg2,arg3,arg4,arg5);
                                if(isInserted = true)
                                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                                else
                                     Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                            }
                        }
                )
        }
    */

        //Navigation
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open_nav, R.string.close_nav);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    public void on(View v){
        if (!BA.isEnabled()){
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Käynnistetty", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(), "Jo päällä", Toast.LENGTH_LONG).show();
        }
    }
    public void off(View v){
        BA.disable();
        Toast.makeText(getApplicationContext(), "Pois päältä", Toast.LENGTH_LONG).show();
    }
    public void visible(View v){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }
    public void list(View v){
        pairedDevices = BA.getBondedDevices();
        ArrayList list = new ArrayList();
        for(BluetoothDevice bt:pairedDevices) list.add(bt.getName());
        Toast.makeText(getApplicationContext(), "Paritetut laitteet",Toast.LENGTH_LONG).show();
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //handle navigation view item clicks here
        int id = item.getItemId();
        if (id == R.id.nav_menu){
            //set the fragment initially
            MainFragment fragment = new MainFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_challenges){
            ChallengesFragment fragment = new ChallengesFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_account){
            MyAccountFragment fragment = new MyAccountFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void Write(View v){
     try{
         FileOutputStream fileout=openFileOutput("DataSave.txt", MODE_PRIVATE); //Avaa tiedoston ja kirjoittaa message-muuttujan tiedostoon
         OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
         outputWriter.write(message.getText().toString());
         outputWriter.close();
     }catch (Exception e){
         e.printStackTrace();
     }
    }
    public void Read(View v){
        try{
            FileInputStream fileIn=openFileInput("DataSave.txt");
            InputStreamReader InputRead=new InputStreamReader(fileIn);
            char[] inputBuffer=new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while((charRead=InputRead.read(inputBuffer))>0){     //Char2String konversio
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void openBT() throws IOException{
        UUID uuid=UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //Standardi sarjaportti ID
        BTSocket = BTDevice.createRfcommSocketToServiceRecord(uuid);
        BTSocket.connect();
        BTInputStream=BTSocket.getInputStream();
        BTOutputStream=BTSocket.getOutputStream();
        beginListenForData();
    }

    void beginListenForData()
    {
        final Handler handler=new Handler();
        final byte delimiter = 10;  //ASCII koodi rivinvaihdolle

        stopWorker = false;
        readBufferPos=0;
        readBuffer=new byte[1024];
        BTThread=new Thread(new Runnable(){
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted() && !stopWorker) {
                    try {
                        int bytesAvailable = BTInputStream.available();
                        if (bytesAvailable > 0) {
                            byte[] packetBytes = new byte[bytesAvailable];
                            BTInputStream.read(packetBytes);
                            for (int i = 0; i < bytesAvailable; i++) {
                                byte b = packetBytes[i];
                                if (b == delimiter) {
                                    byte[] encodedBytes = new byte[readBufferPos];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "US-ASCII");
                                    readBufferPos = 0;
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            myLabel.setText(data);
                                        }
                                    });
                                }
                                else{
                                    readBuffer[readBufferPos++]=b;
                                }
                            }
                        }
                    }catch (IOException ex){
                     stopWorker=true;
                    }
                }
            }
        });
        BTThread.start();

    }
    void closeBT() throws IOException{
        stopWorker=true;        //Suljetaan BT yhteys
        BTInputStream.close();
        BTOutputStream.close();
        BTSocket.close();
    }
}