package head.secretspace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import head.secretspace.R;
import head.secretspace.entity.AddMainValue;
import head.secretspace.utils.Constant;

/**
 * Created by HEAD on 2017/6/20.
 */

public class FmAllAdapter extends BaseAdapter {
    private Context context;

    private LayoutInflater inflater;

    private Holder holder;

    private List<AddMainValue> list=new ArrayList<>();


    class Holder{
        private ImageView imageView;
        private TextView tvFmName,tvFmNum;
    }
    public FmAllAdapter(Context context,List<AddMainValue> list){
        this.list=list;
        this.context=context;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return  list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView!=null) {
            holder= (Holder) convertView.getTag();
        }else {
            convertView=inflater.inflate(R.layout.item_fm_all,null);
            holder=new Holder();
            holder.tvFmName= (TextView) convertView.findViewById(R.id.tv_item_fm_name);
            holder.tvFmNum= (TextView) convertView.findViewById(R.id.tv_item_fm_num);
            holder.imageView= (ImageView) convertView.findViewById(R.id.im_item_fm);
            convertView.setTag(holder);
        }
        holder.imageView.setBackgroundResource(Constant.PROJECTIMG[position]);
        holder.tvFmName.setText(Constant.PROJECTNAME[position]);
        holder.tvFmNum.setText(list.get(position).getValue()+"个项目");
        return convertView;
    }
}
