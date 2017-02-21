package vn.eazy.example;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vn.eazy.core.helper.CommonHelper;
import vn.eazy.core.loader.LoaderUtils;
import vn.eazy.loader.EzLoader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnNormal;
    private Button btnFullContent;
    private Button btnView;
    private Button btnType;

    private EzLoader ezLoader = new EzLoader();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNormal = (Button) findViewById(R.id.btnNormal);
        btnFullContent = (Button) findViewById(R.id.btnFullContent);
        btnView = (Button) findViewById(R.id.btnView);
        btnType = (Button) findViewById(R.id.btnType);

        btnNormal.setOnClickListener(this);
        btnFullContent.setOnClickListener(this);
        btnType.setOnClickListener(this);
        btnView.setOnClickListener(this);
    }

    private void delayToDimiss() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ezLoader.hideLoading();
            }
        }, 3000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNormal:
                ezLoader.showLoading(this);
                break;
            case R.id.btnFullContent:
                ezLoader.showLoading("This is title", "This is content", this);
                break;
            case R.id.btnType:
                ezLoader.showLoading("", "", null, LoaderUtils.TYPE.ANNULAR, this);
                break;
            case R.id.btnView:
                View view = new View(this);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.width = CommonHelper.getScreenWidth() / 5;
                layoutParams.height = CommonHelper.getScreenWidth() / 5;
                view.setBackgroundColor(Color.RED);
                view.setLayoutParams(layoutParams);
                view.requestLayout();
                ezLoader.showLoading("This is custom view", view, this);
                break;
        }
        delayToDimiss();
    }
}
