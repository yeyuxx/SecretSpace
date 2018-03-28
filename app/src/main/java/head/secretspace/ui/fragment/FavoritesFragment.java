package head.secretspace.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import head.secretspace.R;
import head.secretspace.db.DaoUtils;
import head.secretspace.entity.AddBankCard;
import head.secretspace.entity.AddIdentityCard;
import head.secretspace.entity.AddMainValue;
import head.secretspace.entity.AddPwItem;
import head.secretspace.ui.activity.MainActivity;
import head.secretspace.ui.activity.login.NetLoginActivity;
import head.secretspace.utils.Constant;
import head.secretspace.utils.Copydb;

/**
 * Created by HEAD on 2017/5/30.
 */

public class FavoritesFragment extends Fragment {
    @InjectView(R.id.ll_local_backup)
    LinearLayout llLocalBackup;
    @InjectView(R.id.ll_native_recovery)
    LinearLayout llNativeRecovery;
    @InjectView(R.id.ll_cloud_backup)
    LinearLayout llCloudBackup;
    @InjectView(R.id.ll_cloud_recovery)
    LinearLayout llCloudRecovery;
    public static int IOI=0;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };
    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.inject(this, view);
        verifyStoragePermissions(getActivity());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DaoUtils.init(getContext());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.ll_local_backup, R.id.ll_native_recovery, R.id.ll_cloud_backup, R.id.ll_cloud_recovery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_local_backup:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.F_fragment_title1);
                builder.setMessage(R.string.F_fragment_message2);
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Copydb.copyFile(Constant.PATH1, Constant.PATH2);
                    }
                });
                builder.create().show();
                break;
            case R.id.ll_native_recovery:

                AlertDialog.Builder builde1= new AlertDialog.Builder(getContext());
                builde1.setTitle(R.string.F_fragment_title2);
                builde1.setMessage(R.string.F_fragment_message2);
                builde1.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builde1.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getCursorValue();
                        startActivity(new Intent(getContext(), MainActivity.class));
                        getActivity().finish();
                    }
                });
                builde1.create().show();
                break;
            case R.id.ll_cloud_backup:
                IOI=1;
                startActivity(new Intent(getContext(), NetLoginActivity.class));
                break;
            case R.id.ll_cloud_recovery:
                IOI=2;
                startActivity(new Intent(getContext(), NetLoginActivity.class));
                break;
            }
        }

    private void getCursorValue() {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase( Constant.PATH2, null);
        SQLiteDatabase database= SQLiteDatabase.openOrCreateDatabase( Constant.PATH1, null);
        Cursor cursor1 = db.rawQuery("select * from ADD_MAIN_VALUE", null);
        Cursor cursor2 = db.rawQuery("select * from ADD_PW_ITEM", null);
        Cursor cursor3 = db.rawQuery("select * from ADD_BANK_CARD", null);
        Cursor cursor4 = db.rawQuery("select * from ADD_IDENTITY_CARD", null);
        database.execSQL("DELETE FROM ADD_IDENTITY_CARD");
        database.execSQL("DELETE FROM ADD_MAIN_VALUE");//清除表中所有记录：
        database.execSQL("DELETE FROM ADD_PW_ITEM");
        database.execSQL("DELETE FROM ADD_BANK_CARD");
        if (cursor1!=null) {
            while (cursor1.moveToNext()) {
                AddMainValue addMainValue = new AddMainValue(cursor1.getLong(0),
                        cursor1.getString(1),
                        cursor1.getInt(2),
                        cursor1.getString(3),
                        cursor1.getInt(4));
                addMainValue.setLike(cursor1.getInt(3));
                DaoUtils.getAddMainValueManager().insertObject(addMainValue);
            }
        }
        if (cursor2!=null) {
            while (cursor2.moveToNext()) {
                AddPwItem addPwItem=new AddPwItem(cursor2.getLong(0),
                        cursor2.getString(1),
                        cursor2.getString(2),
                        cursor2.getString(3),
                        cursor2.getString(4),
                        cursor2.getInt(5));
                DaoUtils.getAddPwItemManager().insertObject(addPwItem);
            }
        }
        if (cursor3!=null) {
            while (cursor3.moveToNext()) {
                AddBankCard addBankCard=new AddBankCard(cursor3.getLong(0),
                        cursor3.getString(1),
                        cursor3.getString(2),
                        cursor3.getString(3),
                        cursor3.getString(4),
                        cursor3.getString(5),
                        cursor3.getString(6),
                        cursor3.getString(7),
                        cursor3.getInt(8));

                DaoUtils.getBankCardManager().insertObject(addBankCard);
            }
        }
        if (cursor4 != null) {
            while (cursor4.moveToNext()) {
                AddIdentityCard addIdentityCard = new AddIdentityCard(cursor4.getLong(0),
                        cursor4.getString(1),
                        cursor4.getString(2),
                        cursor4.getString(3),
                        cursor4.getString(4),
                        cursor4.getString(5),
                        cursor4.getString(6),
                        cursor4.getString(7),
                        cursor4.getString(8),
                        cursor4.getString(9),
                        cursor4.getInt(10));

                DaoUtils.getIdentityCardManager().insertObject(addIdentityCard);
            }
        }

        cursor4.close();

        cursor3.close();
        cursor2.close();
        cursor1.close();

        db.close();
    }

}
