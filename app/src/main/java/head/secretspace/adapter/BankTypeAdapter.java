package head.secretspace.adapter;

import android.content.Context;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import head.secretspace.R;
import head.secretspace.utils.ViewHolder;

/**
 * Created by HEAD on 2017/9/24.
 */

public class BankTypeAdapter extends ListViewAdapter {
    private List<Integer> data=new ArrayList<>();

    public BankTypeAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
        this.data=datas;
    }

    @Override
    public void convert(ViewHolder holder, Object o, int i) {
        ((ImageView) holder.getView(R.id.im_bank_type)).setBackgroundResource(data.get(i));

    }
}
