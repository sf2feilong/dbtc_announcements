package com.example.edgar.volleytest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eddie on 6/29/2016.
 */
public class Login extends Activity{
    String username;
    String password;
    InputStream is=null;
    String result = null;
    String line = null;
    int code;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final EditText e_user=(EditText) findViewById(R.id.usernameEditText);
        final EditText e_pass=(EditText) findViewById(R.id.passwordEditText);

        Button signIn=(Button) findViewById(R.id.signInButton);
        Button cancel=(Button) findViewById(R.id.cancelButton);
        queue = Volley.newRequestQueue(this);
        queue.start();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = e_user.getText().toString();
                password = e_pass.getText().toString();
                select();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

    public void select() {
        String url = "http://sf2feilong.16mb.com/website_android/android_login.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                result = response.toString();
                try
                {
                    JSONObject json_data = new JSONObject(result);
                    code=(json_data.getInt("code"));

                    if(code==1)
                    {
                        Toast.makeText(getBaseContext(), "Signed In Successfully",
                                Toast.LENGTH_SHORT).show();
                        View v = null;
                        goToAnnouncements(v);
                    }
                    else if(code==2)
                    {
                        Toast.makeText(getBaseContext(), "Username can't be blank",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if(code==3)
                    {
                        Toast.makeText(getBaseContext(), "Password can't be blank",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if(code==4)
                    {
                        Toast.makeText(getBaseContext(), "Invalid username or password",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if(code==5)
                    {
                        Toast.makeText(getBaseContext(), "Username does not exist",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if(code==6)
                    {
                        Toast.makeText(getBaseContext(), "Username and Password can't be blank",
                                Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(), "Sorry, Try Again " + code,
                                Toast.LENGTH_LONG).show();
                    }
                }
                catch(Exception e)
                {
                    Log.e("Fail 3",code  + " " +  e.toString());
                    Toast.makeText(getBaseContext(), "Sorry, Try Again " + result,
                            Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getBaseContext(), "Signed In Successfully",
                        Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "Sign In Failed",
                        Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("user", username);
                MyData.put("pass", password);
                return MyData;
            }
        };
        queue.add(MyStringRequest);
        /*
        try
        {
            JSONObject json_data = new JSONObject(result);
            code=(json_data.getInt("code"));

            if(code==1)
            {
                Toast.makeText(getBaseContext(), "Signed In Successfully",
                        Toast.LENGTH_SHORT).show();
                View v = null;
                goToAnnouncements(v);
            }
            else if(code==2)
            {
                Toast.makeText(getBaseContext(), "Username can't be blank",
                        Toast.LENGTH_SHORT).show();
            }
            else if(code==3)
            {
                Toast.makeText(getBaseContext(), "Password can't be blank",
                        Toast.LENGTH_SHORT).show();
            }
            else if(code==4)
            {
                Toast.makeText(getBaseContext(), "Invalid username or password",
                        Toast.LENGTH_SHORT).show();
            }
            else if(code==5)
            {
                Toast.makeText(getBaseContext(), "Username does not exist",
                        Toast.LENGTH_SHORT).show();
            }
            else if(code==6)
            {
                Toast.makeText(getBaseContext(), "Username and Password can't be blank",
                        Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getBaseContext(), "Sorry, Try Again " + code,
                        Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Log.e("Fail 3",code  + " " +  e.toString());
            Toast.makeText(getBaseContext(), "Sorry, Try Again " + result,
                    Toast.LENGTH_LONG).show();
        }*/
    }

    public void goToAnnouncements(View v){
        //Intent intent = new Intent(this, Announcements.class);
        //intent.putExtra("username", this.username);
        //startActivity(intent);
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

        return super.onOptionsItemSelected(item);
    }
}
