package head.secretspace.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import head.secretspace.R;
import head.secretspace.utils.Constant;
import head.secretspace.utils.GPW;

/**
 * Created by HEAD on 2017/6/6.
 */


public class AddPasswordDialog extends Dialog {
    @InjectView(R.id.bt_add_pw_dialog_close)
    Button btAddPwDialogClose;
    @InjectView(R.id.cb_num)
    CheckBox cbNum;
    @InjectView(R.id.cb_letter)
    CheckBox cbLetter;
    @InjectView(R.id.cb_symbol)
    CheckBox cbSymbol;
    @InjectView(R.id.textview_add_pw_dialog)
    TextView textviewAddPwDialog;
    @InjectView(R.id.seekBar)
    SeekBar seekBar;
    @InjectView(R.id.ed_add_pw_dialog)
    EditText edAddPwDialog;
    @InjectView(R.id.bt_add_pw_dialog_query)
    Button btAddPwDialogQuery;


    private Context context;
    private int ischeck = -1;
    private DialogCallBackListener mDialogCallBackListener;

    @OnClick({R.id.bt_add_pw_dialog_close, R.id.cb_num, R.id.cb_letter, R.id.cb_symbol, R.id.bt_add_pw_dialog_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_add_pw_dialog_close:
                dismiss();
                break;
            case R.id.cb_num:
                if (getIsChecked() == 7) {
                    enableView(seekBar, false);
                } else {
                    enableView(seekBar, true);
                }
                break;
            case R.id.cb_letter:
                if (getIsChecked() == 7) {
                    enableView(seekBar, false);
                } else {
                    enableView(seekBar, true);
                }
                break;
            case R.id.cb_symbol:
                if (getIsChecked() == 7) {
                    enableView(seekBar, false);
                } else {
                    enableView(seekBar, true);
                }
                break;
            case R.id.bt_add_pw_dialog_query:
                if (mDialogCallBackListener != null)
                    mDialogCallBackListener.CallBack(edAddPwDialog.getText().toString());
                dismiss();
                break;
        }
    }


    public interface DialogCallBackListener {
        void CallBack(String msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setWindowAnimations(R.style.DialogBottom);
    }

    public AddPasswordDialog(Context context, DialogCallBackListener dialogCallBackListener) {
        super(context);
        this.context = context;
        this.mDialogCallBackListener = dialogCallBackListener;
        setContentView(R.layout.dialog_add_password);
        ButterKnife.inject(this);
        initView();
        initData();
    }

    private void enableView(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                enableView(child, enabled);
            }
        }
    }


    private void initView() {
        cbNum.setChecked(true);
        cbLetter.setChecked(true);
        cbSymbol.setChecked(true);
        seekBar.setMax(24);
        seekBar.setProgress(6);
    }

    private int getIsChecked() {
        if (cbNum.isChecked() == true && cbLetter.isChecked() == true && cbSymbol.isChecked() == true) {
            ischeck = 0;
        } else if (cbNum.isChecked() == true && cbLetter.isChecked() == false && cbSymbol.isChecked() == false) {
            ischeck = 1;
        } else if (cbNum.isChecked() == true && cbLetter.isChecked() == false && cbSymbol.isChecked() == true) {
            ischeck = 2;
        } else if (cbNum.isChecked() == true && cbLetter.isChecked() == true && cbSymbol.isChecked() == false) {
            ischeck = 3;
        } else if (cbNum.isChecked() == false && cbLetter.isChecked() == true && cbSymbol.isChecked() == true) {
            ischeck = 4;
        } else if (cbNum.isChecked() == false && cbLetter.isChecked() == false && cbSymbol.isChecked() == true) {
            ischeck = 5;
        } else if (cbNum.isChecked() == false && cbLetter.isChecked() == true && cbSymbol.isChecked() == false) {
            ischeck = 6;
        } else {
            ischeck = 7;
        }
        return ischeck;
    }

    private void initData() {
        if (edAddPwDialog.getText().toString().equals("")) {
            edAddPwDialog.setText(GPW.generatePassword(Constant.ALL_NUM_SYMBOL_LETTER, 6));
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textviewAddPwDialog.setText(progress + 1 + "");
                switch (getIsChecked()) {
                    case 0:
                        edAddPwDialog.setText(GPW.generatePassword(Constant.ALL_NUM_SYMBOL_LETTER, progress + 1));
                        break;
                    case 1:
                        edAddPwDialog.setText(GPW.generatePassword(Constant.ALL_NUM, progress + 1));
                        break;
                    case 2:
                        edAddPwDialog.setText(GPW.generatePassword(Constant.ALl_NUM_SYMBOL, progress + 1));
                        break;
                    case 3:
                        edAddPwDialog.setText(GPW.generatePassword(Constant.ALL_NUN_LETTER, progress + 1));
                        break;
                    case 4:
                        edAddPwDialog.setText(GPW.generatePassword(Constant.ALL_SYMBOL_LETTER, progress + 1));
                        break;
                    case 5:
                        edAddPwDialog.setText(GPW.generatePassword(Constant.ALL_SYMBOL, progress + 1));
                        break;
                    case 6:
                        edAddPwDialog.setText(GPW.generatePassword(Constant.ALL_LETTER, progress + 1));
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}