package com.example.qly_hocphan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class HPAdapter extends ArrayAdapter {
    // khai báo các thành phần
    private Context context;
    private int resource;
    private List<HocPhan> listHP;

    public HPAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listHP = objects;
    }
    class ViewHolder{
        // khai báo các biến tương ứng với các thành phần trong giao diện item_list_hp
        TextView tv_MaHp, tv_TenHp, tv_SoTc, tv_Hptq, tv_HpSs, tv_KhoaQly;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // khai báo ViewHolder
        ViewHolder viewHolder;
        // kiểm tra xem convertView xem có rỗng không?
        // nếu rỗng thì set hiển thị item_listHP lên
        // nếu không rỗng thì setTag nội dung lên
        if(view == null){
            view= LayoutInflater.from(context).inflate(R.layout.item_list_hp,
                    viewGroup,false);
            viewHolder=new ViewHolder();
            // ánh xạ
            viewHolder.tv_MaHp=(TextView)view.findViewById(R.id.tv_MaHp);
            viewHolder.tv_TenHp=(TextView)view.findViewById(R.id.tv_TenHp);
            viewHolder.tv_SoTc=(TextView)view.findViewById(R.id.tv_SoTc);
            viewHolder.tv_Hptq=(TextView)view.findViewById(R.id.tv_HpTq);
            viewHolder.tv_HpSs=(TextView)view.findViewById(R.id.tv_HpSs);
            viewHolder.tv_KhoaQly = (TextView)view.findViewById(R.id.tv_KhoaQly);
            view.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)view.getTag();
        }
        // thiết lập giá trị
        HocPhan hp =listHP.get(i);
        viewHolder.tv_MaHp.setText(hp.getMaHp());
        viewHolder.tv_TenHp.setText(hp.getTenHp());
        viewHolder.tv_SoTc.setText(hp.getSoTc());
        viewHolder.tv_Hptq.setText(hp.getHpTq());
        viewHolder.tv_HpSs.setText(hp.getHpSs());
        viewHolder.tv_KhoaQly.setText(hp.getKhoaQly());
        return view;
    }
}
