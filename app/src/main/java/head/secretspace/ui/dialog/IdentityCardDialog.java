package head.secretspace.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import head.secretspace.R;

/**
 * Created by HEAD on 2017/7/23.
 */

public class IdentityCardDialog extends Dialog {


    @InjectView(R.id.dp_idcard_one)
    DatePicker dpIdcardOne;
    @InjectView(R.id.bt_idcard_cancel)
    Button btIdcardCancel;
    @InjectView(R.id.bt_idcard_query)
    Button btIdcardQuery;
    private Context context;

    private DialogCallBackListener mDialogCallBackListener;

    @OnClick({R.id.bt_idcard_cancel, R.id.bt_idcard_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_idcard_cancel:
                dismiss();
                break;
            case R.id.bt_idcard_query:
                int month = dpIdcardOne.getMonth() + 1;
                if (mDialogCallBackListener != null)
                    mDialogCallBackListener.CallBack(
                            dpIdcardOne.getYear() + "-" +
                                    month + "-" +
                                    dpIdcardOne.getDayOfMonth());
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
    }
    public IdentityCardDialog(@NonNull Context context, DialogCallBackListener mDialogCallBackListener) {
        super(context);
        this.mDialogCallBackListener = mDialogCallBackListener;
        this.context = context;
        setContentView(R.layout.dialog_identity_card_calendar);
        ButterKnife.inject(this);


    }
}
