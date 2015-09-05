package com.capricorn.yummy.yummywakeup;

import android.app.FragmentManager;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Switch;

import com.capricorn.yummy.yummywakeup.R;
import com.capricorn.yummy.yummywakeup.adapter.RingtoneCursorAdapter;
import com.capricorn.yummy.yummywakeup.adapter.UnlockTypeAdapter;
import com.capricorn.yummy.yummywakeup.dialog.UnlockDialogFragment;
import com.capricorn.yummy.yummywakeup.infrastructure.activity.BaseActivity;

public class UnlockTypeActivity extends BaseActivity
        implements View.OnClickListener {

    private GridView gvUnlockType;
    private Button btnAccept;
    private Button btnCancel;

    private final String[] mTypeNames = {
            "Normal",
            "Math",
            "Puzzle",
            "Shake"};
    private final int[] mTypeImages = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};

    @Override
    public void initToolbar() {

    }

    @Override
    public void initView() {
        gvUnlockType = (GridView) findViewById(R.id.gv_unlock_type);
        btnAccept = (Button) findViewById(R.id.btn_unlock_accept);
        btnCancel = (Button) findViewById(R.id.btn_unlock_cancel);
    }

    @Override
    public void initListener() {
        gvUnlockType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("yummywakeup", "Unlock type clicking position:" + String.valueOf(position));
                switch(position) {
                    case 0://Normal
                        break;
                    case 1://Calculation
                    case 2://Puzzle
                    case 3://Shake
                    default:
                        FragmentManager manager = getFragmentManager();
                        UnlockDialogFragment dialog = new UnlockDialogFragment();
                        dialog.show(manager, "testTag");
                        break;
                }
            }
        });

        gvUnlockType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("yummywakeup", "Unlock type clicking position:" + String.valueOf(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnAccept.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void initData() {
        UnlockTypeAdapter adapter = new UnlockTypeAdapter(this, mTypeNames, mTypeImages);
        gvUnlockType.setAdapter(adapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_unlock_type;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch(v.getId()) {
            case R.id.btn_unlock_accept:
                // ToDo
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btn_unlock_cancel:
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
            default:
                break;
        }
    }
}
