package com.example.edgar.volleytest;

//import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;

import java.util.ArrayList;

public class CheckArf extends ActionBarActivity {

    private DrawerLayout drawer;
    private ExpandableListView drawerList;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private String username;
    private String level = null;
    ArrayList<String> array = new ArrayList();
    ArrayAdapter<String> adapter;
    private String title = null;
    private String idNumber = null;
    private ListView listView;
    private ArrayList<String> listItems = new ArrayList();
    private ArrayList<String> listIds = new ArrayList();
    private String category = null;
    private String arf_id = null;
    private String type = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_arf);

        username = getIntent().getExtras().getString("username");
        level = getIntent().getExtras().getString("level");
        category = getIntent().getExtras().getString("category");
        type = getIntent().getExtras().getString("type");

        mDrawerList = (ListView)findViewById(R.id.navList);mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();

        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        final TextView t_check_arf = (TextView)findViewById(R.id.viewLogsTextView);
        listView = (ListView)findViewById(R.id.listView);

        if(level.equals("low")){
            t_check_arf.setText("Check My A.R.F.s");
        }

        getArfs();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arf_id = listIds.get(position);
                goToArfDetails();
            }
        });




    }

    private void getArfs(){

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
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addDrawerItems() {

        if(level.equals("low")){
            goToLowItems();
        }
        else if(level.equals("mid")){
            goToMidItems();
        }
        else if(level.equals("high")){
            goToHighItems();
        }
        else if(level.equals("tech")){
            goToTechItems();
        }

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(level.equals("low")){
                    goToLowOptions(position);
                }
                else if(level.equals("mid")){
                    goToMidOptions(position);
                }
                else if(level.equals("high")){
                    goToHighOptions(position);
                }
                else if(level.equals("tech")){
                    goToTechOptions(position);
                }

            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.mipmap.ic_launcher, R.string.drawer_open, R.string.drawer_close) {

            //** Called when a drawer has settled in a completely open state.
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("User Level: " + level + " Menu");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            //** Called when a drawer has settled in a completely closed state.
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void goToLowItems(){
        array.add("Check My A.R.F.s");
        array.add("      Submitted");
        array.add("      Drafts");
        array.add("      For Re-Edit");
        array.add("      Rejected");
        array.add("Create A.R.F.s");
        array.add("Change My Password");
        array.add("Sign Out");
    }

    private void goToMidItems(){
        array.add("Check A.R.F.s");
        array.add("      Received");
        array.add("      Submitted");
        array.add("      Drafts");
        array.add("      For Re-Edit");
        array.add("      Rejected");
        array.add("Create A.R.F.s");
        array.add("Change My Password");
        array.add("View Logs");
        array.add("Sign Out");
    }

    private void goToHighItems(){
        array.add("Check A.R.F.s");
        array.add("      Received");
        array.add("      Submitted");
        array.add("      Drafts");
        array.add("      For Re-Edit");
        array.add("      Rejected");
        array.add("Create A.R.F.s");
        array.add("System Notifications");
        array.add("      Standard");
        array.add("      Emergency");
        array.add("Manage Users");
        array.add("      Add New Users");
        array.add("      Edit Registered User");
        array.add("      Reset / Deactivate");
        array.add("Change My Password");
        array.add("Change My PIN Code");
        array.add("View Logs");
        array.add("Sign Out");
    }

    private void goToTechItems(){
        array.add("System Notifications");
        array.add("      Standard");
        array.add("      Emergency");
        array.add("Manage Users");
        array.add("      Add New Users");
        array.add("      Edit Registered User");
        array.add("      Reset / Deactivate");
        array.add("Change My Password");
        array.add("Change My PIN Code");
        array.add("View Logs");
        array.add("Sign Out");
    }

    private void goToLowOptions(int position){
        View v = null;
        switch(position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5: goToCreateARF(v);
                break;
            case 6: goToChangePassword(v);
                break;
            case 7: gotoSignOut(v);
                break;
        }
    }

    private void goToMidOptions(int position){
        View v = null;
        switch(position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6: goToCreateARF(v);
                break;
            case 7: goToChangePassword(v);
                break;
            case 8:
                break;
            case 9: gotoSignOut(v);
                break;
        }
    }

    private void goToHighOptions(int position){
        View v = null;
        switch(position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6: goToCreateARF(v);
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14: goToChangePassword(v);
                break;
            case 15:
                break;
            case 16:
                break;
            case 17: gotoSignOut(v);
                break;
        }
    }

    private void goToTechOptions(int position){
        View v = null;
        switch(position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4: goToAddUser(v);
                break;
            case 5:
                break;
            case 6: goToReset(v);
                break;
            case 7:
                break;
            case 8: goToChangePin(v);
                break;
            case 9:
                break;
            case 10: gotoSignOut(v);
                break;
        }
    }

    private void goToCreateARF(View v){
        Intent intent = new Intent(this,CreateARF.class);
        intent.putExtra("level", this.level);
        intent.putExtra("username",this.username);
        startActivity(intent);
    }

    private void goToChangePassword(View v){
        Intent intent = new Intent(this,ChangePassword.class);
        intent.putExtra("level", this.level);
        intent.putExtra("username",this.username);
        startActivity(intent);
    }

    private void goToAddUser(View v){
        Intent intent = new Intent(this,AddUser.class);
        intent.putExtra("level", this.level);
        intent.putExtra("username",this.username);
        startActivity(intent);
    }

    private void goToReset(View v){
        Intent intent = new Intent(this,Reset.class);
        intent.putExtra("level", this.level);
        intent.putExtra("username",this.username);
        startActivity(intent);
    }

    private void goToChangePin(View v){
        Intent intent = new Intent(this,ChangePin.class);
        intent.putExtra("level", this.level);
        intent.putExtra("username",this.username);
        startActivity(intent);
    }

    private void gotoSignOut(View v){
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    private void goToArfDetails(){
        Intent intent = new Intent(this,CreateARF.class);
        intent.putExtra("level", this.level);
        intent.putExtra("username",this.username);
        intent.putExtra("arf_id", arf_id);
        intent.putExtra("category","arf details");
        intent.putExtra("type",type);
        startActivity(intent);
    }


}
