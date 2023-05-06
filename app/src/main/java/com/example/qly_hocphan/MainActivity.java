package com.example.qly_hocphan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 1. khai báo các thành phần
    private EditText edtMaHp, edtTenHp, edtSoTc, edtHpTq, edtHpSs, edtKhoaQly, edtTk;
    private Button btnThem, btnSua, btnXoa, btnTk;
    private ListView lvhp;

    // CSDL
    private HPdatabase database;
    private HPAdapter adapter;
    private List<HocPhan> listHP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        // khởi tạo
        database=new HPdatabase(this);
        listHP=database.InformationHP();
        adapter=new HPAdapter(this,R.layout.item_list_hp,listHP);
        lvhp.setAdapter(adapter);
//        setAdapter();
        // bắt sự kiện cho các button

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtMaHp.getText().toString().equals("")||edtTenHp.getText().toString().equals("")
                        ||edtSoTc.getText().toString().equals("")||edtHpTq.getText().toString().equals("")
                        ||edtHpSs.getText().toString().equals("")||edtKhoaQly.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Thông tin không được để trống !!!", Toast.LENGTH_LONG).show();
                }else {
                    HocPhan hp = CreateHP();
                    if(hp!=null){
                        database.AddHP(hp);
                        listHP.clear();
                        listHP.addAll(database.InformationHP());
                       // setAdapter();
                        adapter.notifyDataSetChanged();
                        //Xóa DL nhập vào
                        edtMaHp.setText("");
                        edtTenHp.setText("");
                        edtSoTc.setText("");
                        edtHpTq.setText("");
                        edtHpSs.setText("");
                        edtKhoaQly.setText("");
                    }
                }
            }
        });

        // bắt sự kiện cho từng Item trong ListView
        lvhp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HocPhan hp  = listHP.get(position);
                edtMaHp.setText(hp.getMaHp());
                edtTenHp.setText(hp.getTenHp());
                edtSoTc.setText(hp.getSoTc());
                edtHpTq.setText(hp.getHpTq());
                edtHpSs.setText(hp.getHpSs());
                edtKhoaQly.setText(hp.getKhoaQly());
            }
        });
        //btnSua
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // edtMaHp.setEnabled(false);
                HocPhan hp = new HocPhan();
                hp.setMaHp(String.valueOf(edtMaHp.getText()));
                hp.setTenHp(edtTenHp.getText()+" ");
                hp.setSoTc(edtSoTc.getText()+"");
                hp.setHpTq(edtHpTq.getText()+"");
                hp.setHpSs(edtHpSs.getText()+"");
                hp.setKhoaQly(edtKhoaQly.getText()+"");
                int kq=database.UpdateHP(hp);
                if(kq>0){
                    listHP.clear();
                    listHP.addAll(database.InformationHP());
                    if(adapter!=null) {
                        adapter.notifyDataSetChanged();
                    }
                }
                //Xóa DL nhập vào
                edtMaHp.setText("");
                edtTenHp.setText("");
                edtSoTc.setText("");
                edtHpTq.setText("");
                edtHpSs.setText("");
                edtKhoaQly.setText("");

            }
        });

        //btnXoa
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HocPhan hp = new HocPhan();
                hp.setMaHp(String.valueOf(edtMaHp.getText()));
                int kq=database.DeleteHP(hp.getMaHp());
                if(kq>0){
                    Toast.makeText(MainActivity.this,"Xóa HP thành công", Toast.LENGTH_LONG).show();
                    listHP.clear();
                    listHP.addAll(database.InformationHP());
                    if(adapter!=null){
                        adapter.notifyDataSetChanged();
                    }
                }else {
                    Toast.makeText(MainActivity.this,"Không xóa được HP ", Toast.LENGTH_LONG).show();
                }
                edtMaHp.setText("");
                edtTenHp.setText("");
                edtSoTc.setText("");
                edtHpTq.setText("");
                edtHpSs.setText("");
                edtKhoaQly.setText("");

            }
        });

        btnTk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtTk.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Nhập Mã Hp cần tìm kiếm!!!",Toast.LENGTH_LONG).show();
                }
                else{
                    HocPhan hp = database.findById(edtTk.getText().toString());
                    if(hp !=null){
                        listHP.clear();
                        listHP.add(hp);
                        setAdapter();
                    }
                    else
                        Toast.makeText(MainActivity.this, "Không tìm thấy !!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private HocPhan CreateHP() {
        String MaHp =edtMaHp.getText().toString();
        String TenHp =edtTenHp.getText().toString();
        String SoTc =edtSoTc.getText().toString();
        String HpTq =edtHpTq.getText().toString();
        String HpSs =edtHpSs.getText().toString();
        String KhoaQly = edtKhoaQly.getText().toString();
        HocPhan hp =new HocPhan(MaHp, TenHp, SoTc, HpTq, HpSs, KhoaQly);
        return hp;
    }

    private void setAdapter() {
        if(adapter==null){
            // tạo mới
            adapter=new HPAdapter(this,R.layout.item_list_hp,listHP);
            lvhp.setAdapter(adapter);
        }else {
            // cập nhật lại DL
            adapter.notifyDataSetChanged();
        }
    }


    private void anhxa() {
        edtMaHp = findViewById(R.id.edtMaHp);
        edtTenHp = findViewById(R.id.edtTenHp);
        edtSoTc = findViewById(R.id.edtSoTc);
        edtHpTq = findViewById(R.id.edtHpTq);
        edtHpSs = findViewById(R.id.edtHpSs);
        edtKhoaQly = findViewById(R.id.edtKhoaQl);
        edtTk = findViewById(R.id.edtTk);
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        btnTk = findViewById(R.id.btnTk);
        lvhp = findViewById(R.id.LvHp);
    }
}