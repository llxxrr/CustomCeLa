package customcela.com.example.lxr.customcela;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by lxr on 2018/2/5.
 */

public class MyAdapter extends BaseAdapter {
    private List<HaoHan> peoplelist;
    private Context context;

    public MyAdapter(Context context, List<HaoHan> peoplelist) {
        this.context = context;
        this.peoplelist = peoplelist;
    }

    @Override
    public int getCount() {
        return peoplelist.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh = null;
        if (view == null) {
            view = View.inflate(context, R.layout.list_item_layout, null);
            vh = new ViewHolder();
            vh.tv_index = (TextView) view.findViewById(R.id.tv_index);
            vh.tv_name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        HaoHan haoHan = peoplelist.get(i);
        String currentstr = haoHan.getPinyin().charAt(0) + "";
        String indexstr = null;
        if (i == 0) {
            //隐藏
            indexstr = "ppp";
        } else {
            //当前首字母
            String laststr = peoplelist.get(i - 1).getPinyin().charAt(0) + "";
            Log.d("MyAdapter", laststr);
            if (!laststr.equals(currentstr)) {
                //隐藏
                indexstr = "ppp";
            }
            vh.tv_index.setVisibility(indexstr != null ? View.VISIBLE : View.GONE);
            vh.tv_index.setText(currentstr);
            vh.tv_name.setText(haoHan.getName());

        }
        return view;
    }

    @Override
    public Object getItem(int i) {
        return peoplelist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder {
        TextView tv_index;
        TextView tv_name;
    }

}
