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
import head.secretspace.entity.AddBankCard;
import head.secretspace.entity.AddIdentityCard;
import head.secretspace.entity.AddMainValue;
import head.secretspace.utils.ViewHolder;

/**
 * Created by HEAD on 2017/11/24.
 */

public class IdentityDetailedAdapter extends ListViewAdapter<AddIdentityCard> implements View.OnClickListener {
    private List<AddIdentityCard> list=new ArrayList<>();


    public IdentityDetailedAdapter(Context context, List<AddIdentityCard> datas) {
        super(context, datas, R.layout.item_detailed_bank);
    }

    @Override
    public void convert(final ViewHolder holder, final   AddIdentityCard addIdentityCard, final int i) {
        ((TextView) holder.getView(R.id.tv_bank_name)).setText(addIdentityCard.getName());
        ((TextView) holder.getView(R.id.tv_bank_time)).setText(addIdentityCard.getTime());


        if (addIdentityCard.getLike() == null || addIdentityCard.getLike() == 0)
            ((Button) holder.getView(R.id.bt_bank_coll)).setBackgroundResource(R.drawable.ic_heart_off);
        else if (addIdentityCard.getLike() == 1)
            ((Button) holder.getView(R.id.bt_bank_coll)).setBackgroundResource(R.drawable.ic_heart_to);

        ((Button) holder.getView(R.id.bt_bank_coll)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addIdentityCard.getLike() == null || addIdentityCard.getLike() == 0) {
                    ((Button) holder.getView(R.id.bt_bank_coll)).setBackgroundResource(R.drawable.ic_heart_to);
                    addIdentityCard.setId(list.get(i).getId());
                    addIdentityCard.setLike(1);
                    DaoUtils.getBankCardManager().daoSession.getAddIdentityCardDao().update(addIdentityCard);
                } else if (addIdentityCard.getLike() == 1) {
                    ((Button) holder.getView(R.id.bt_bank_coll)).setBackgroundResource(R.drawable.ic_heart_off);
                    addIdentityCard.setId(list.get(i).getId());
                    addIdentityCard.setLike(0);
                    DaoUtils.getBankCardManager().daoSession.getAddIdentityCardDao().update(addIdentityCard);
                }

            }
        });

        ((Button) holder.getView(R.id.bt_bank_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog(i, addIdentityCard);
            }
        });
    }

    private void showNormalDialog(final int i, final AddIdentityCard addIdentityCard) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(mContext);
        normalDialog.setTitle("提示");
        normalDialog.setMessage("是否删除?");
        normalDialog.setPositiveButton("是",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(i);
                        DaoUtils.getIdentityCardManager().daoSession.getAddIdentityCardDao().deleteByKeyInTx(addIdentityCard.getId());
                        notifyDataSetChanged();

                        AddMainValue addMainValue = new AddMainValue();
                        addMainValue.setId(3l);
                        addMainValue.setValue(DaoUtils.getBankCardManager().QueryAll(AddBankCard.class).size());
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

    @Override
    public void onClick(View v) {

    }


}