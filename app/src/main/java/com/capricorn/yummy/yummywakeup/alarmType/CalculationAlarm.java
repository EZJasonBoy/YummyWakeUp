package com.capricorn.yummy.yummywakeup.alarmType;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.capricorn.yummy.yummywakeup.R;
import com.capricorn.yummy.yummywakeup.alarm.AlarmAlertFullScreen;
import com.capricorn.yummy.yummywakeup.infrastructure.fragment.BaseFragment;
import com.capricorn.yummy.yummywakeup.module.UnlockTypeModule.model.UnlockDiffcultLevel;
import com.capricorn.yummy.yummywakeup.util.CalculationFormula;

/**
 * Created by Chuan on 8/4/2015.
 */
public class CalculationAlarm extends UnlockFragment {

    private int[] formula;
    private int result;
    private TextView tvFormula;
    private EditText etCalculResult;
    private ImageView ivFlagResult;
    private Button btnCloseAlarm;
    private OnAlarmAction mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (AlarmAlertFullScreen) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void initView(View container) {

        tvFormula = (TextView) container.findViewById(R.id.tv_formula);
        etCalculResult = (EditText) container.findViewById(R.id.et_calcul_result);
        ivFlagResult = (ImageView) container.findViewById(R.id.iv_flag_result);
        btnCloseAlarm = (Button) container.findViewById(R.id.btn_calcul_close_alarm);
        btnCloseAlarm.setEnabled(false);

        formula = CalculationFormula.generateFormula(UnlockDiffcultLevel.valueObject(1)); // Generate formula
        result = CalculationFormula.getFormulaResult(formula); // Get formula's result
        tvFormula.setText(CalculationFormula.getFormulaString(formula) + " = ?"); // Show formula on textView
    }

    @Override
    public void initConfig(Bundle bundle) {}

    @Override
    public int getLayoutId() {
        return R.layout.fragment_calculation_alarm;
    }

    @Override
    public void initListener() {
        etCalculResult.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                // If typed number is equal to result, then enable image and button
                if(Integer.parseInt(etCalculResult.getText().toString()) == result) {
                    // ToDo change image status
                    btnCloseAlarm.setEnabled(true);
                }
            }
        });
        btnCloseAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.closeAlarm();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void refresh() {}

    @Override
    public boolean checkUnlockAlarm() {
        return false;
    }
}
