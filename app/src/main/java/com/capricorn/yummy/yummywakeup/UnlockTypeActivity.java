package com.capricorn.yummy.yummywakeup;

import android.app.FragmentManager;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.capricorn.yummy.yummywakeup.adapter.UnlockTypeAdapter;
import com.capricorn.yummy.yummywakeup.dialog.UnlockDialogFragment;
import com.capricorn.yummy.yummywakeup.infrastructure.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class UnlockTypeActivity extends BaseActivity
        implements View.OnClickListener {

    private GridView gvUnlockType;
    private Button btnAccept;
    private Button btnCancel;
    

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
                switch (position) {
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

  /*      gvUnlockType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("yummywakeup", "Unlock type clicking position:" + String.valueOf(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.v("yummywakeup", "Unlock type clicking position:");

            }
        });*/
        btnAccept.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void initData() {

        List<UnlockTypeAdapter.UnlockType> unlockTypeList = new ArrayList<>();

        UnlockTypeAdapter.UnlockType unlockType = new UnlockTypeAdapter.UnlockType();
        unlockType.mTypeNames = "Normal";
        unlockType.mTypeImages = R.mipmap.ic_launcher;
        unlockTypeList.add(unlockType);

        UnlockTypeAdapter.UnlockType unlockType2 = new UnlockTypeAdapter.UnlockType();
        unlockType2.mTypeNames = "Math";
        unlockType2.mTypeImages = R.mipmap.ic_launcher;
        unlockTypeList.add(unlockType2);


        UnlockTypeAdapter.UnlockType unlockType3 = new UnlockTypeAdapter.UnlockType();
        unlockType3.mTypeNames = "Puzzle";
        unlockType3.mTypeImages = R.mipmap.ic_launcher;
        unlockTypeList.add(unlockType3);

        UnlockTypeAdapter.UnlockType unlockType4 = new UnlockTypeAdapter.UnlockType();
        unlockType4.mTypeNames = "Shake";
        unlockType4.mTypeImages = R.mipmap.ic_launcher;
        unlockTypeList.add(unlockType4);
        


        UnlockTypeAdapter adapter = new UnlockTypeAdapter(this, unlockTypeList);
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
