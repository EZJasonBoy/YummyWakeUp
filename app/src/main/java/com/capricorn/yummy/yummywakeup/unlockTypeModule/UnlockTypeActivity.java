package com.capricorn.yummy.yummywakeup.unlockTypeModule;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.capricorn.yummy.yummywakeup.R;
import com.capricorn.yummy.yummywakeup.unlockTypeModule.adapter.UnlockTypeAdapter;
import com.capricorn.yummy.yummywakeup.unlockTypeModule.dialog.UnlockDialogFragment;
import com.capricorn.yummy.yummywakeup.infrastructure.activity.BaseActivity;
import com.capricorn.yummy.yummywakeup.unlockTypeModule.model.UnlockType;
import com.capricorn.yummy.yummywakeup.unlockTypeModule.model.UnlockDiffcultLevel;

import java.util.ArrayList;
import java.util.List;

public class UnlockTypeActivity extends BaseActivity
        implements View.OnClickListener {

    private GridView gvUnlockType;
    private Button btnAccept;
    private Button btnCancel;

    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.v("yummywakeup", "Type: " + msg.what  + "Difficult lvl: " + msg.arg1);
            if(msg != null) {
                // If it's not normal alarm
                if(msg.what != 0) {
                    ((TextView)gvUnlockType.getChildAt(msg.what).findViewById(R.id.tv_diff_lvl))
                            .setText(UnlockDiffcultLevel.valueString(msg.arg1));
                }
            }
        }
    };

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
                        UnlockDialogFragment dialog = new UnlockDialogFragment(uiHandler, position);
                        dialog.show(manager, "testTag");
                        break;
                }
            }
        });

        btnAccept.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void initData() {

        List<UnlockType> unlockTypeList = new ArrayList<>();

        unlockTypeList.add(new UnlockType("Normal", "Easy", R.mipmap.ic_launcher));
        unlockTypeList.add(new UnlockType("Math", "Easy", R.mipmap.ic_launcher));
        unlockTypeList.add(new UnlockType("Puzzle", "Easy", R.mipmap.ic_launcher));
        unlockTypeList.add(new UnlockType("Shake", "Easy", R.mipmap.ic_launcher));

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
