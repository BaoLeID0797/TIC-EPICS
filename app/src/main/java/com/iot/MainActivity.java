package com.iot;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;
import com.iot.model.Feeds;
import com.iot.model.Response;
import com.iot.network.NetworkUtil;
import com.tuanbao.iot.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements
        OnChartGestureListener, OnChartValueSelectedListener {

    @ViewById(R.id.activity_main_chart_light_and_long)
    protected LineChart chartLightLong;

    @ViewById(R.id.activity_main_tv_long)
    protected TextView tvLong;

    @ViewById(R.id.activity_main_tv_light)
    protected TextView tvLight;

    private CompositeSubscription subscriptions;
    private List<Entry> lights;
    private List<Entry> longs;

    @AfterViews
    protected void init() {
        loadData();
        lights = new ArrayList<Entry>();
        longs = new ArrayList<Entry>();
        new Handler().postDelayed(runnable, 15000);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            init();
        }
    };


    private void loadData() {
        subscriptions = new CompositeSubscription();

        subscriptions.add(NetworkUtil.getRetrofit().data()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(Response data) {
        Log.e("info", new Gson().toJson(data.getFeeds()));
        processData(data);
    }

    private void handleError(Throwable error) {

    }

    private void processData(Response data) {
        //lay data
        float count = 0;
        List<Feeds> dataFeeds = data.getFeeds();
        List<Feeds> dataFeedsShow = new ArrayList<>();
//giới hạn
        if (dataFeeds.size() >= 10) {
            int numberSize = dataFeeds.size() - 10;
            for (int i = numberSize; i < dataFeeds.size(); i++) dataFeedsShow.add(dataFeeds.get(i));
        }
//
        for (Feeds item : dataFeedsShow) {
            try {
                //SimpleDateFormat sdf = new SimpleDateFormat("dd");
                //float dataDate = Float.valueOf(sdf.format(new Date()));
                count++;
                float dataDate = count;
                if (!TextUtils.isEmpty(item.getField1())) {
                    lights.add(new Entry(dataDate, Float.valueOf(item.getField1())));
                } else {
                    lights.add(new Entry(dataDate, 0));
                }
                // kiểm tra thông tin có rỗng ko
                if (!TextUtils.isEmpty(item.getField2())) {
                    longs.add(new Entry(dataDate, Float.valueOf(item.getField2())));
                } else {
                    longs.add(new Entry(dataDate, 0));
                }

            } catch (Throwable throwable) {
                Log.e("Error", throwable.getMessage());
            }
        }

        drawChart();
//CB
        tvLight.setText(data.getFeeds().get(data.getFeeds().size() - 1).getField1());
        tvLong.setText(data.getFeeds().get(data.getFeeds().size() - 1).getField2());
        Toast.makeText(this, "anh sang: " + data.getFeeds().get(data.getFeeds().size() - 1).getField1(), Toast.LENGTH_SHORT).show();
    }

    private void drawChart() {
        LineDataSet dataSetLight = new LineDataSet(lights, "light"); // add entries to dataset
        dataSetLight = point(dataSetLight);
        dataSetLight.setColor(getResources().getColor(R.color.colorDarkGrey));
        dataSetLight.setValueTextColor(getResources().getColor(R.color.colorDarkGrey));

        LineDataSet dataSetLong = new LineDataSet(longs, "long"); // add entries to dataset
        dataSetLong.setColor(getResources().getColor(R.color.colorPrimary));
        dataSetLong.setValueTextColor(getResources().getColor(R.color.colorDarkGrey));
        dataSetLong = point(dataSetLong);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSetLight);
        dataSets.add(dataSetLong);

        LineData lineData = new LineData(dataSets);

        Description des = new Description();
        des.setText("Biểu đồ khoảng cách và ánh sáng ");

        chartLightLong.setDescription(des);
        chartLightLong.setOnChartGestureListener(this);
        chartLightLong.setOnChartValueSelectedListener(this);
        chartLightLong.setDrawGridBackground(false);
        chartLightLong.setData(lineData);
        chartLightLong.invalidate(); // refresh
    }

    // vẽ nút
    private LineDataSet point(LineDataSet dataSet) {
        dataSet.setFillAlpha(110);
        dataSet.setCircleColor(Color.BLACK);
        dataSet.setLineWidth(1f);
        dataSet.setCircleRadius(3f);
        dataSet.setDrawCircleHole(false);
        dataSet.setValueTextSize(9f);
        dataSet.setDrawFilled(true);
        return dataSet;
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
