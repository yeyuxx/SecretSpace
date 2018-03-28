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
import head.secretspace.entity.AddMainValue;
import head.secretspace.utils.ViewHolder;

/**
 * Created by HEAD on 2017/10/11.
 */

public class BankDetailedAdapter extends ListViewAdapter<AddBankCard> implements View.OnClickListener{
    private List<AddBankCard> list=new ArrayList<>();


    public BankDetailedAdapter(Context context, List<AddBankCard> datas) {
        super(context, datas, R.layout.item_detailed_bank);
        this.list=datas;
        DaoUtils.init(context);
    }

    @Override
    public void convert(final ViewHolder holder, final   AddBankCard addBankCard, final int i) {
        ((TextView) holder.getView(R.id.tv_bank_name)).setText(addBankCard.getIssuingBank());
        ((TextView) holder.getView(R.id.tv_bank_time)).setText(addBankCard.getTime());


        if (addBankCard.getLike() == null || addBankCard.getLike() == 0)
            ((Button) holder.getView(R.id.bt_bank_coll)).setBackgroundResource(R.drawable.ic_heart_off);
        else if (addBankCard.getLike() == 1)
            ((Button) holder.getView(R.id.bt_bank_coll)).setBackgroundResource(R.drawable.ic_heart_to);

        ((Button) holder.getView(R.id.bt_bank_coll)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addBankCard.getLike() == null || addBankCard.getLike() == 0) {
                    ((Button) holder.getView(R.id.bt_bank_coll)).setBackgroundResource(R.drawable.ic_heart_to);
                    addBankCard.setId(list.get(i).getId());
                    addBankCard.setLike(1);
                    DaoUtils.getBankCardManager().daoSession.getAddBankCardDao().update(addBankCard);
                } else if (addBankCard.getLike() == 1) {
                    ((Button) holder.getView(R.id.bt_bank_coll)).setBackgroundResource(R.drawable.ic_heart_off);
                    addBankCard.setId(list.get(i).getId());
                    addBankCard.setLike(0);
                    DaoUtils.getBankCardManager().daoSession.getAddBankCardDao().update(addBankCard);
                }
            }
        });

        ((Button) holder.getView(R.id.bt_bank_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog(i, addBankCard);
            }
        });
    }

    private void showNormalDialog(final int i, final AddBankCard addBankCard) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(mContext);
        normalDialog.setTitle("提示");
        normalDialog.setMessage("是否删除?");
        normalDialog.setPositiveButton("是",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(i);
                        DaoUtils.getBankCardManager().daoSession.getAddBankCardDao().deleteByKeyInTx(addBankCard.getId());
                        notifyDataSetChanged();

                        AddMainValue addMainValue = new AddMainValue();
                        addMainValue.setId(2l);
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
