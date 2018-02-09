package com.bhxx.goldenstraw.activity;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.utils.ActivityCollector;

public class TiShiActivity extends BasicActivity implements View.OnClickListener {

    private TextView tishiText, titleTitle;
    private ImageView titleBack;

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void click(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_shi);

        titleBack = (ImageView) findViewById(R.id.titleBack);
        titleBack.setOnClickListener(this);
        titleTitle = (TextView) findViewById(R.id.titleTitle);
        titleTitle.setText("防骗提示");
        tishiText = (TextView) findViewById(R.id.tishiText);
        String tishi = "<font color='red'>防骗提示：</font>" + "在您申请借款的过程中，金稻草官方及工作人员不会通过电话、短信、邮件等任何方式，已担保费、咨询费等任何名义向您收取任何费用，请您务必知晓，谨防诈骗！";
        tishiText.setText(Html.fromHtml(tishi));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleBack:
                finish();
                break;
        }
    }
}
