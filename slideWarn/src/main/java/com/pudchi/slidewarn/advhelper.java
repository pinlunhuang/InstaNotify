package com.pudchi.slidewarn;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Spinner;

public class advhelper extends Activity {


    static final int DIALOG_ID1 = 0;
    static final int DIALOG_ID2 = 1;
    int year_d, month_d, day_d, hour_t, minute_t;
    EditText datetext;
    EditText timetext;
    Spinner typespin;
    String min_t;
    WebView helper_web;
    private String[] servicetype = {"普通照護", "陪同回診", "特殊照護", "其他"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advhelper);

        helper_web = (WebView) findViewById(R.id.helper_web);
        helper_web.setWebViewClient(new WebViewClientImpl());
        helper_web.setWebChromeClient(new WebChromeClient());
        helper_web.getSettings().setJavaScriptEnabled(true);
        helper_web.loadUrl("http://140.134.26.9/Group4/Home/Index");

        /*final Calendar cal = Calendar.getInstance();
        year_d = cal.get(Calendar.YEAR);
        month_d = cal.get(Calendar.MONTH);
        day_d = cal.get(Calendar.DAY_OF_MONTH);
        hour_t = cal.get(Calendar.HOUR_OF_DAY);
        minute_t = cal.get(Calendar.MINUTE);
        datetext = (EditText)findViewById(R.id.datetext);
        datetext.setInputType(InputType.TYPE_NULL);
        timetext = (EditText)findViewById(R.id.timetext);
        timetext.setInputType(InputType.TYPE_NULL);
        typespin = (Spinner)findViewById(R.id.typespinner);

        ImageButton addalert = (ImageButton) findViewById(R.id.addalertbutton);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,servicetype);
        adapter.setDropDownViewResource(R.layout.spinnertext);
        typespin.setAdapter(adapter);
        showDialogOndatetext();
        showDialogOntimetext();*/
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            boolean close_web;
            if (helper_web.canGoBack())
            {
                close_web = false;
                helper_web.goBack();
            }
            else
            {
                close_web = true;
            }

            event.startTracking();
            return close_web;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_advhelper, menu);
        return false;
    }

    /*public void showDialogOndatetext(){
        datetext.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID1);
                    }
                }
        );
    }


    protected Dialog onCreateDialog(int id){
        if (id == DIALOG_ID1)
            return new DatePickerDialog(this, datepickerListener, year_d, month_d, day_d);
        if (id == DIALOG_ID2)
            return new TimePickerDialog(this, timepickerListener, hour_t, minute_t,true);
        return null;
    }

    private DatePickerDialog.OnDateSetListener datepickerListener
            = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_d = year;
            month_d = monthOfYear + 1;
            day_d = dayOfMonth;
            datetext.setText(new StringBuilder().append(year_d).append("/").append(month_d).append("/").append(day_d));
            Toast.makeText(advhelper.this,year_d + " / " + month_d +" / "+ day_d, Toast.LENGTH_LONG).show();

        }
    };

    public void showDialogOntimetext(){
        timetext.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID2);
                    }
                }
        );
    }

    /*protected  Dialog showTimeDialog(int id){
        if (id == DIALOG_ID2)
            return new TimePickerDialog(this, timepickerListener, hour_t, minute_t,true);
        return null;

    };

    private TimePickerDialog.OnTimeSetListener timepickerListener
            = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_t = hourOfDay;
            minute_t = minute;
            if (minute < 10)
            {
                min_t = String.format("0" + minute);
                timetext.setText(new StringBuilder().append(hour_t).append(" : ").append(min_t));
                Toast.makeText(advhelper.this, hour_t + " : " + min_t, Toast.LENGTH_LONG).show();
            }
            else
            {
                timetext.setText(new StringBuilder().append(hour_t).append(" : ").append(minute_t));
                Toast.makeText(advhelper.this, hour_t + " : " + minute_t, Toast.LENGTH_LONG).show();
            }
        }
    };*/

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

    private final class WebViewClientImpl extends WebViewClient {
        public void WebViewClientImpl() {
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.compareTo("about:blank") == 0) {
                finish();
            } else if (url.endsWith(".mp4")) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                view.getContext().startActivity(i);
                finish();
            }
            return false;
        }
    }
}
