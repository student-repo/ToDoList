package com.example.ubuntu_master.image_gallery;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.test.espresso.core.deps.guava.primitives.Ints;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

public class MainActivity extends AppCompatActivity implements NoticeListFragment.updateNoticeListFragment, SimpleFragment.updateNoticeInfoData{//

    private Boolean displayFinished = false;
    private int landscapeDisplayId = -1;


    private HashMap<Integer, NoticeDetails> noticeDetails = new HashMap<Integer, NoticeDetails>(){{

    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            restoreInstanceState(savedInstanceState);
        }
        catch(Exception e){}
        getSupportActionBar().setTitle("To Do List");  // provide compatibility to all the versions

        this.getIntent().addFlags(FLAG_ACTIVITY_SINGLE_TOP);
        commitFragment();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_finished:{
                displayFinished = true;
                commitFragment();
                break;
            }
            case  R.id.menu_unfinished:{
                displayFinished = false;
                commitFragment();
                break;
            }
                default:
                    return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        restoreInstanceState(savedInstanceState);
     }

    private void restoreInstanceState(Bundle savedInstanceState){
        ArrayList<Integer> ids = savedInstanceState.getIntegerArrayList("ids");
        ArrayList<String> title = savedInstanceState.getStringArrayList("title");
        ArrayList<String> details = savedInstanceState.getStringArrayList("details");
        ArrayList<Integer> year = savedInstanceState.getIntegerArrayList("year");
        ArrayList<Integer> month = savedInstanceState.getIntegerArrayList("month");
        ArrayList<Integer> day = savedInstanceState.getIntegerArrayList("day");
        ArrayList<Integer> hour = savedInstanceState.getIntegerArrayList("hour");
        ArrayList<Integer> minute = savedInstanceState.getIntegerArrayList("minute");
        ArrayList<Integer> withoutHours = savedInstanceState.getIntegerArrayList("withoutHours");
        ArrayList<Integer> finished = savedInstanceState.getIntegerArrayList("finished");


        for(int i = 0; i < ids.size(); i ++){
            if(year.get(ids.get(i)) < 0){
                if(finished.get(ids.get(i)) == 1){
                    noticeDetails.put(ids.get(i), new NoticeDetails(title.get(ids.get(i)), details.get(ids.get(i)), null, ids.get(ids.get(i)), true, true));
                }
                else{
                    noticeDetails.put(ids.get(i), new NoticeDetails(title.get(ids.get(i)), details.get(ids.get(i)), null, ids.get(ids.get(i)), true, false));
                }
            }
            else if(withoutHours.get(ids.get(i)) == 1 || hour.get(ids.get(i)) < 0){
                if(finished.get(ids.get(i)) == 1){
                    noticeDetails.put(ids.get(i), new NoticeDetails(title.get(ids.get(i)), details.get(ids.get(i)), new GregorianCalendar(year.get(ids.get(i)), month.get(ids.get(i)),
                            day.get(ids.get(i))), ids.get(ids.get(i)), true, true));
                }
                else{
                    noticeDetails.put(ids.get(i), new NoticeDetails(title.get(ids.get(i)), details.get(ids.get(i)), new GregorianCalendar(year.get(ids.get(i)), month.get(ids.get(i)),
                            day.get(ids.get(i))), ids.get(ids.get(i)), true, false));
                }
            }
            else{
                if(finished.get(ids.get(i)) == 1){
                    noticeDetails.put(ids.get(i), new NoticeDetails(title.get(ids.get(i)), details.get(ids.get(i)), new GregorianCalendar(year.get(ids.get(i)), month.get(ids.get(i)),
                            day.get(ids.get(i)), hour.get(ids.get(i)), minute.get(ids.get(i))), ids.get(ids.get(i)), false, true));
                }
                else{
                    noticeDetails.put(ids.get(i), new NoticeDetails(title.get(ids.get(i)), details.get(ids.get(i)), new GregorianCalendar(year.get(ids.get(i)), month.get(ids.get(i)),
                            day.get(ids.get(i)), hour.get(ids.get(i)), minute.get(ids.get(i))), ids.get(ids.get(i)), false, false));
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList("ids", getRestoreIdArrayList());
        outState.putStringArrayList("title", getRestoreTitleArrayList());
        outState.putStringArrayList("details", getRestoreDetailsArrayList());
        outState.putIntegerArrayList("year", getRestoreYearArrayList());
        outState.putIntegerArrayList("month", getRestoreMonthArrayList());
        outState.putIntegerArrayList("day", getRestoreDayArrayList());
        outState.putIntegerArrayList("hour", getRestoreHourArrayList());
        outState.putIntegerArrayList("minute", getRestoreMinuteArrayList());
        outState.putIntegerArrayList("withoutHours", getRestoreWithoutHoursArrayList());
        outState.putIntegerArrayList("finished", getRestoreFinishedArrayList());

    }


    @Override
    public void updateNoticeListFragment(int id, String title, String details, int year, int month, int day, int hour, int minute, boolean finished) {
        if (id != -1){
            if(year < 0){
                noticeDetails.put(id, new NoticeDetails(title, details, null, id, false, finished));
            }
            else if(hour < 0){
                noticeDetails.put(id, new NoticeDetails(title, details, new GregorianCalendar(year, month,day), id, true, finished));
            }
            else{
                noticeDetails.put(id, new NoticeDetails(title, details, new GregorianCalendar(year, month,day, hour,minute), id, false, finished));
            }
        }
        else {
            if(year < 0){
                noticeDetails.put(noticeDetails.keySet().size(), new NoticeDetails(title, details, null, noticeDetails.keySet().size(), false, finished));
            }
            else if(hour < 0){
                noticeDetails.put(noticeDetails.keySet().size(), new NoticeDetails(title, details, new GregorianCalendar(year, month,day), noticeDetails.keySet().size(), true, finished));
            }
            else{
                noticeDetails.put(noticeDetails.keySet().size(), new NoticeDetails(title, details, new GregorianCalendar(year, month,day, hour,minute), noticeDetails.keySet().size(), false, finished));
            }
        }
        commitFragment();
    }

    @Override
    public void updateNoticeInfoData(int id, String title, String details, int year, int month, int day, int hour, int minute, boolean finished) {
        if (id != -1){
            if(year < 0){
                noticeDetails.put(id, new NoticeDetails(title, details, null, id, false, finished));
            }
            else if(hour < 0){
                noticeDetails.put(id, new NoticeDetails(title, details, new GregorianCalendar(year, month,day), id, true, finished));
            }
            else{
                noticeDetails.put(id, new NoticeDetails(title, details, new GregorianCalendar(year, month,day, hour,minute), id, false, finished));
            }
        }
        else {
            if(year < 0){
                noticeDetails.put(noticeDetails.keySet().size(), new NoticeDetails(title, details, null, noticeDetails.keySet().size(), false, finished));
            }
            else if(hour < 0){
                noticeDetails.put(noticeDetails.keySet().size(), new NoticeDetails(title, details, new GregorianCalendar(year, month,day), noticeDetails.keySet().size(), true, finished));
            }
            else{
                noticeDetails.put(noticeDetails.keySet().size(), new NoticeDetails(title, details, new GregorianCalendar(year, month,day, hour,minute), noticeDetails.keySet().size(), false, finished));
            }
        }
        commitFragment();
    }

    @Override
    public void handleLandscapeListClick(int id, String title, String details,int year, int month, int day, int hour, int minute, boolean finished) {
        landscapeDisplayId = id;
        commitFragment();
    }

    private void commitFragment(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // get the display mode
        int displaymode = getResources().getConfiguration().orientation;
        if (displaymode == 1) { // it portrait mode
            NoticeListFragment portraitFragment = new NoticeListFragment();

            portraitFragment.setArguments(getBundle());
            fragmentTransaction.replace(android.R.id.content, portraitFragment);
        }
        else {// its landscape
            LandscapeFragment f = new LandscapeFragment();

            f.setArguments(getBundle());
            fragmentTransaction.replace(android.R.id.content, f);
        }
        fragmentTransaction.commit();
    }


    private Bundle getBundle(){
        Bundle b = new Bundle();
        b.putStringArray("titleArray", getTitleArray());
        b.putStringArray("detailsArray", getDetailsArray());
        b.putIntArray("idArray", getIdArray());
        b.putIntArray("dateYear", getDateYearArray());
        b.putIntArray("dateMonth", getDateMonthArray());
        b.putIntArray("dateDay", getDateDayArray());
        b.putIntArray("dateHour", getDateHourArray());
        b.putIntArray("dateMinute", getDateMinuteArray());
        b.putInt("mode", getMode());
//        if(landscapeDisplayId != -1){
        if(getIdArray().length == 0){
            b.putInt("landscapeDisplayID", -1);
        }
        else {
            b.putInt("landscapeDisplayID", landscapeDisplayId);
        }
//        }
        return b;
    }





















    private String[] getTitleArray(){
        ArrayList<String> s = new ArrayList<>();
        for(Integer i: noticeDetails.keySet()){
            if(displayFinished){
                if(noticeDetails.get(i).getFinished())
                    s.add(noticeDetails.get(i).getTitle());
            }
            else{
                if(!noticeDetails.get(i).getFinished())
                    s.add(noticeDetails.get(i).getTitle());
            }
        }
        return s.toArray(new String[0]);
    }

    private String[] getDetailsArray(){
        ArrayList<String> s = new ArrayList<>();
        for(Integer i: noticeDetails.keySet()){

            if(displayFinished){
                if(noticeDetails.get(i).getFinished())
                    s.add(noticeDetails.get(i).getDetails());
            }
            else{
                if(!noticeDetails.get(i).getFinished())
                    s.add(noticeDetails.get(i).getDetails());
            }
        }
        return s.toArray(new String[0]);
    }


    private int[] getIdArray(){
        List<Integer> s = new ArrayList<>();
        for(Integer i: noticeDetails.keySet()){
            if(displayFinished){
                if(noticeDetails.get(i).getFinished())
                    s.add(noticeDetails.get(i).getId());
            }
            else{
                if(!noticeDetails.get(i).getFinished())
                    s.add(noticeDetails.get(i).getId());
            }
        }
        return Ints.toArray(s);
    }

    private ArrayList<Integer> getIdArrayList(){
        ArrayList<Integer> s = new ArrayList<>();
        for(int i = 0; i <  noticeDetails.keySet().size(); i++){
            s.add(noticeDetails.get(i).getId(), noticeDetails.get(i).getId());
        }
        return s;
    }


    private int[] getDateYearArray(){
        List<Integer> s = new ArrayList<>();
        for(Integer i: noticeDetails.keySet()){
            if(noticeDetails.get(i).getDate() != null){
                if(displayFinished){
                    if(noticeDetails.get(i).getFinished())
                        s.add(noticeDetails.get(i).getDate().get(Calendar.YEAR));
                }
                else{
                    if(!noticeDetails.get(i).getFinished())
                        s.add(noticeDetails.get(i).getDate().get(Calendar.YEAR));
                }
            }
            else{
                if(displayFinished){
                    if(noticeDetails.get(i).getFinished())
                        s.add(-1);
                }
                else{
                    if(!noticeDetails.get(i).getFinished())
                        s.add(-1);
                }
            }
        }
        return Ints.toArray(s);
    }

    private int[] getDateMonthArray(){
        List<Integer> s = new ArrayList<>();
        for(Integer i: noticeDetails.keySet()){
            if(noticeDetails.get(i).getDate() != null){
                if(displayFinished){
                    if(noticeDetails.get(i).getFinished())
                        s.add(noticeDetails.get(i).getDate().get(Calendar.MONTH));
                }
                else{
                    if(!noticeDetails.get(i).getFinished())
                        s.add(noticeDetails.get(i).getDate().get(Calendar.MONTH));
                }
            }
            else{
                if(displayFinished){
                    if(noticeDetails.get(i).getFinished())
                        s.add(-1);
                }
                else{
                    if(!noticeDetails.get(i).getFinished())
                        s.add(-1);
                }
            }
        }
        return Ints.toArray(s);
    }

    private int[] getDateDayArray(){
        List<Integer> s = new ArrayList<>();
        for(Integer i: noticeDetails.keySet()){
            if(noticeDetails.get(i).getDate() != null){
                if(displayFinished){
                    if(noticeDetails.get(i).getFinished())
                        s.add(noticeDetails.get(i).getDate().get(Calendar.DAY_OF_MONTH));
                }
                else{
                    if(!noticeDetails.get(i).getFinished())
                        s.add(noticeDetails.get(i).getDate().get(Calendar.DAY_OF_MONTH));
                }
            }
            else{
                if(displayFinished){
                    if(noticeDetails.get(i).getFinished())
                        s.add(-1);
                }
                else{
                    if(!noticeDetails.get(i).getFinished())
                        s.add(-1);
                }
            }
        }
        return Ints.toArray(s);
    }

    private int[] getDateHourArray(){
        List<Integer> s = new ArrayList<>();
        for(Integer i: noticeDetails.keySet()){
            if(noticeDetails.get(i).getWithoutHours()){
                if(displayFinished){
                    if(noticeDetails.get(i).getFinished())
                        s.add(-1);
                }
                else{
                    if(!noticeDetails.get(i).getFinished())
                        s.add(-1);
                }
            }
            else if(noticeDetails.get(i).getDate() != null){
                if(displayFinished){
                    if(noticeDetails.get(i).getFinished())
                        s.add(noticeDetails.get(i).getDate().get(Calendar.HOUR));
                }
                else{
                    if(!noticeDetails.get(i).getFinished())
                        s.add(noticeDetails.get(i).getDate().get(Calendar.HOUR));
                }
            }
            else{
                if(displayFinished){
                    if(noticeDetails.get(i).getFinished())
                        s.add(-1);
                }
                else{
                    if(!noticeDetails.get(i).getFinished())
                        s.add(-1);
                }
            }
        }
        return Ints.toArray(s);
    }

    private int[] getDateMinuteArray(){
        List<Integer> s = new ArrayList<>();
        for(Integer i: noticeDetails.keySet()){
            if(noticeDetails.get(i).getWithoutHours()){
                if(displayFinished){
                    if(noticeDetails.get(i).getFinished())
                        s.add(-1);
                }
                else{
                    if(!noticeDetails.get(i).getFinished())
                        s.add(-1);
                }
            }
            else if(noticeDetails.get(i).getDate() != null){
                if(displayFinished){
                    if(noticeDetails.get(i).getFinished())
                        s.add(noticeDetails.get(i).getDate().get(Calendar.MINUTE));
                }
                else{
                    if(!noticeDetails.get(i).getFinished())
                        s.add(noticeDetails.get(i).getDate().get(Calendar.MINUTE));
                }
            }
            else{
                if(displayFinished){
                    if(noticeDetails.get(i).getFinished())
                        s.add(-1);
                }
                else{
                    if(!noticeDetails.get(i).getFinished())
                        s.add(-1);
                }
            }
        }
        return Ints.toArray(s);
    }

    private int getMode(){
        if(displayFinished) return 0;
        else return  1;
    }





    private ArrayList<Integer> getRestoreIdArrayList(){
        ArrayList<Integer> s = new ArrayList<>();
        for(int i = 0; i <  noticeDetails.keySet().size(); i++){
            s.add(noticeDetails.get(i).getId(), noticeDetails.get(i).getId());
        }
        return s;
    }

    private ArrayList<String> getRestoreTitleArrayList(){
        ArrayList<String> s = new ArrayList<>();
        for(int i = 0; i <  noticeDetails.keySet().size(); i++){
            s.add(noticeDetails.get(i).getId(), noticeDetails.get(i).getTitle());
        }
        return s;
    }

    private ArrayList<String> getRestoreDetailsArrayList(){
        ArrayList<String> s = new ArrayList<>();
        for(int i = 0; i <  noticeDetails.keySet().size(); i++){
            s.add(noticeDetails.get(i).getId(), noticeDetails.get(i).getDetails());
        }
        return s;
    }

    private ArrayList<Integer> getRestoreYearArrayList(){
        ArrayList<Integer> s = new ArrayList<>();
        for(int i = 0; i <  noticeDetails.keySet().size(); i++){
            if(noticeDetails.get(i).getDate() != null){
                s.add(noticeDetails.get(i).getId(), noticeDetails.get(i).getDate().get(Calendar.YEAR));
            }
            else {
                s.add(noticeDetails.get(i).getId(), -1);
            }
        }
        return s;
    }

    private ArrayList<Integer> getRestoreMonthArrayList() {
        ArrayList<Integer> s = new ArrayList<>();
        for (int i = 0; i < noticeDetails.keySet().size(); i++) {
            if(noticeDetails.get(i).getDate() != null){
                s.add(noticeDetails.get(i).getId(), noticeDetails.get(i).getDate().get(Calendar.MONTH));
            }
            else {
                s.add(noticeDetails.get(i).getId(), -1);
            }
        }
        return s;
    }

    private ArrayList<Integer> getRestoreDayArrayList() {
        ArrayList<Integer> s = new ArrayList<>();
        for (int i = 0; i < noticeDetails.keySet().size(); i++) {
            if(noticeDetails.get(i).getDate() != null){
                s.add(noticeDetails.get(i).getId(), noticeDetails.get(i).getDate().get(Calendar.DAY_OF_MONTH));
            }
            else {
                s.add(noticeDetails.get(i).getId(), -1);
            }
        }
        return s;
    }

    private ArrayList<Integer> getRestoreHourArrayList() {
        ArrayList<Integer> s = new ArrayList<>();
        for (int i = 0; i < noticeDetails.keySet().size(); i++) {

            if(noticeDetails.get(i).getDate() != null){
                s.add(noticeDetails.get(i).getId(), noticeDetails.get(i).getDate().get(Calendar.HOUR));
            }
            else {
                s.add(noticeDetails.get(i).getId(), -1);
            }
        }
        return s;
    }

    private ArrayList<Integer> getRestoreMinuteArrayList() {
        ArrayList<Integer> s = new ArrayList<>();
        for (int i = 0; i < noticeDetails.keySet().size(); i++) {
            if(noticeDetails.get(i).getDate() != null){
                s.add(noticeDetails.get(i).getId(), noticeDetails.get(i).getDate().get(Calendar.MINUTE));
            }
            else {
                s.add(noticeDetails.get(i).getId(), -1);
            }
        }
        return s;
    }

    private ArrayList<Integer> getRestoreWithoutHoursArrayList() {
        ArrayList<Integer> s = new ArrayList<>();
        for (int i = 0; i < noticeDetails.keySet().size(); i++) {
            if(noticeDetails.get(i).getWithoutHours()){
                s.add(noticeDetails.get(i).getId(), 1);
            }
            else {
                s.add(noticeDetails.get(i).getId(), 0);
            }
        }
        return s;
    }

    private ArrayList<Integer> getRestoreFinishedArrayList() {
        ArrayList<Integer> s = new ArrayList<>();
        for (int i = 0; i < noticeDetails.keySet().size(); i++) {
            if(noticeDetails.get(i).getFinished()){
                s.add(noticeDetails.get(i).getId(), 1);
            }
            else {
                s.add(noticeDetails.get(i).getId(), 0);
            }
        }
        return s;
    }
















    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void jsonContent(){
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONObject oo = (JSONObject)obj.getJSONObject("images2").get("2");
            System.out.println(oo.get("name"));
            HashMap<String, JSONObject> h = new HashMap<>();
            System.out.println(oo.keys().getClass());

//            HashMap<String,Object> result =
//                    new ObjectMapper().readValue(loadJSONFromAsset(), HashMap.class);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
