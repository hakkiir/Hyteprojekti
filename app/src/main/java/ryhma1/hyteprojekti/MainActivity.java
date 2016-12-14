package ryhma1.hyteprojekti;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
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
import android.database.sqlite.*;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/*
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
*/
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.UUID;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;

import android.widget.Button;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseHelper myDb;
    Button haasteButton1;

    private BluetoothAdapter BA;    /*Luodaan Bluetooth adapteri*/
    private Set<BluetoothDevice> pairedDevices;
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
    static final int READ_BLOCK_SIZE = 100;
    private static final String FORMAT = "%02d:%02d:%02d";
    int seconds, minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        //set the fragment initially
        MainFragment fragment = new MainFragment();
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        BA = BluetoothAdapter.getDefaultAdapter();
        /*lv = (ListView) findViewById(R.id.listView);



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

    haasteButton1 = (Button) findViewById(R.id.haasteButton1);
        


        //creating user


        List<User> users = myDb.getAllUsers();

        //create test user if doesn't exist
        if(users.size() < 1){
            myDb.addUser(new User (1, "Testi", "Henkilö", 180, 80, 0, 1, 4, 12));
        }




        //creating challenges if doesn't exist
        List<Challenge> challenges =  myDb.getAllChallenges();

        if (challenges.size() < 1){
            myDb.addChallenge(new Challenge (1, "Out of bed", 1, 100, 1, 0, "9" ));
            myDb.addChallenge(new Challenge (2, "Rocksteady", 4, 1000, 1, 0, "9" ));
            myDb.addChallenge(new Challenge (3, "Time for bed", 1, 100, 1, 0, "9" ));
            myDb.addChallenge(new Challenge (4, "Delicious", 1, 100, 1, 0, "9" ));
            myDb.addChallenge(new Challenge (5, "Like a clock", 1, 100, 1, 0, "9" ));


        }

        myDb.addNormalValues(new NormalValues(4, 7));
/*
        TextView haaste1 = (TextView) findViewById(R.id.haaste1);
        haaste1.setText("Tähän haasteen tiedot");*/

}



    public void startChallenge(int id) {
        User user = myDb.getUser(1);
        Challenge challenge = myDb.getChallenge(id);
        String nameChallenge = challenge.getChallengename();
        int points = challenge.getChallengePoints();
        int durationChallenge = challenge.getDuration();
        int activityChallenge = challenge.getActivity();
        String clock = challenge.getClock();
        double min = user.getBound1();
        double max = user.getBound2();
        int level = user.getLevel();
        ArrayList data = new ArrayList();
        int activityUser = data.size();

        int durationUser = 0; // tähän jotain



        if (id == 1) {
            myDb.makeActive(user.getUserID(), challenge.getChallengeID());
            while (activityUser < activityChallenge) {
                activityUser = activityUser + activityUser;
            }

        }
        switch (level) {
            case 1:
                while (durationUser < durationChallenge || activityUser < activityChallenge || data.size()<1) {

                }
                break;
            case 2:
                while (durationUser != durationChallenge || activityUser != activityChallenge) {

                }
                break;
            case 3:
                while (durationUser != durationChallenge || activityUser != activityChallenge) {

                }
                break;
            default:
                break;
        }
    }


    public void active(){


    }


    public void on(View v) {
        if (!BA.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Käynnistetty", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Jo päällä", Toast.LENGTH_LONG).show();
        }
    }

    public void off(View v) {
        BA.disable();
        Toast.makeText(getApplicationContext(), "Pois päältä", Toast.LENGTH_LONG).show();
    }

    public void visible(View v) {
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }

    public void list(View v) {
        pairedDevices = BA.getBondedDevices();
        ArrayList list = new ArrayList();
        for (BluetoothDevice bt : pairedDevices) list.add(bt.getName());
        Toast.makeText(getApplicationContext(), "Paritetut laitteet", Toast.LENGTH_LONG).show();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //handle navigation view item clicks here
        int id = item.getItemId();
        if (id == R.id.nav_menu) {
            //set the fragment initially
            MainFragment fragment = new MainFragment();
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_challenges) {
            ChallengesFragment fragment = new ChallengesFragment();
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_account) {
            MyAccountFragment fragment = new MyAccountFragment();
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Write(View v) {
        try {
            FileOutputStream fileout = openFileOutput("DataSave.txt", MODE_PRIVATE); //Avaa tiedoston ja kirjoittaa message-muuttujan tiedostoon
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(message.getText().toString());
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Read(View v) {
        try {
            FileInputStream fileIn = openFileInput("DataSave.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {     //Char2String konversio
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            InputRead.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openBT() throws IOException {
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //Standardi sarjaportti ID
        BTSocket = BTDevice.createRfcommSocketToServiceRecord(uuid);
        BTSocket.connect();
        BTInputStream = BTSocket.getInputStream();
        BTOutputStream = BTSocket.getOutputStream();
        beginListenForData();
    }

    void beginListenForData() {
        final Handler handler = new Handler();
        final byte delimiter = 10;  //ASCII koodi rivinvaihdolle

        stopWorker = false;
        readBufferPos = 0;
        readBuffer = new byte[1024];
        BTThread = new Thread(new Runnable() {
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
                                } else {
                                    readBuffer[readBufferPos++] = b;
                                }
                            }
                        }
                    } catch (IOException ex) {
                        stopWorker = true;
                    }
                }
            }
        });
        BTThread.start();

    }

    void closeBT() throws IOException {
        stopWorker = true;        //Suljetaan BT yhteys
        BTInputStream.close();
        BTOutputStream.close();
        BTSocket.close();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    /*
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
     }
                */

}