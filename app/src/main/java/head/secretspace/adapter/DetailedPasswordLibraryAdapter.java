package head.secretspace.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import head.secretspace.R;
import head.secretspace.db.DaoUtils;
import head.secretspace.entity.AddMainValue;
import head.secretspace.entity.AddPwItem;
import head.secretspace.utils.ViewHolder;

/**
 * Created by HEAD on 2017/8/9.
 */

public class DetailedPasswordLibraryAdapter extends ListViewAdapter<AddPwItem> implements View.OnClickListener {
    private CallBackOne callBackOne;
    private CallBackTwo callBackTwo;
    private List<AddPwItem> list=new ArrayList<>();


    @Override
    public void onClick(View v) {
        callBackOne.click(v);
        callBackTwo.click(v);

    }

    public interface CallBackTwo {
        void click(View view);

    }

    public interface CallBackOne {
        void click(View view);

    }

    public DetailedPasswordLibraryAdapter(Context context, List<AddPwItem> datas, CallBackOne callBackOne, CallBackTwo callBackTwo) {
        super(context, datas, R.layout.item_detailed_password_ibrary);
        DaoUtils.init(context);
        this.list=datas;
        this.callBackOne = callBackOne;
        this.callBackTwo = callBackTwo;
    }


    @Override
    public void convert(final ViewHolder holder, final AddPwItem addPwItem, final int i) {
        ((TextView) holder.getView(R.id.tv_dpl_name)).setText(addPwItem.getName());
        ((TextView) holder.getView(R.id.tv_dpl_time)).setText(addPwItem.getTime());


        if (addPwItem.getLike()==null||addPwItem.getLike()==0)
            ((Button) holder.getView(R.id.bt_dpl_coll)).setBackgroundResource(R.drawable.ic_heart_off);
        else if (addPwItem.getLike()==1)
            ((Button) holder.getView(R.id.bt_dpl_coll)).setBackgroundResource(R.drawable.ic_heart_to);

        ((Button) holder.getView(R.id.bt_dpl_coll)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addPwItem.getLike()==null||addPwItem.getLike()==0){
                    ((Button) holder.getView(R.id.bt_dpl_coll)).setBackgroundResource(R.drawable.ic_heart_to);
                    addPwItem.setId(list.get(i).getId());
                    addPwItem.setLike(1);
                    DaoUtils.getAddPwItemManager().daoSession.getAddPwItemDao().update(addPwItem);
                }
                else if (addPwItem.getLike()==1){
                    ((Button) holder.getView(R.id.bt_dpl_coll)).setBackgroundResource(R.drawable.ic_heart_off);
                    addPwItem.setId(list.get(i).getId());
                    addPwItem.setLike(0);
                    DaoUtils.getAddPwItemManager().daoSession.getAddPwItemDao().update(addPwItem);
                }

            }
        });

        ((Button) holder.getView(R.id.bt_dpl_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog(i, addPwItem);
            }
        });
    }

    private void showNormalDialog(final int i, final AddPwItem addPwItem) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(mContext);
        normalDialog.setTitle("提示");
        normalDialog.setMessage("是否删除?");
        normalDialog.setPositiveButton("是",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(i);
                        DaoUtils.getAddPwItemManager().daoSession.getAddPwItemDao().deleteByKeyInTx(addPwItem.getId());
                        notifyDataSetChanged();

                        AddMainValue addMainValue = new AddMainValue();
                        addMainValue.setId(1l);
                        addMainValue.setValue(DaoUtils.getAddPwItemManager().QueryAll(AddPwItem.class).size());
                        DaoUtils.getAddMainValueManager().updateObject(addMainValue);

                    }
                });
        normalDialog.setNegativeButton("否",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        normalDialog.show();
    }
}
