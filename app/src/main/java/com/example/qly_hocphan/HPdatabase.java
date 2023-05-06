package com.example.qly_hocphan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.PropertyReference0Impl;

public class HPdatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="QLHP.db";
    // khai báo tên bảng
    private static final String TABLE_NAME="hp01";
    // khai báo tên trường
    private static final String ID="MaHp";
    private static final String NAME="TenHp";
    private static final String CREDITS="SoTc";
    private static final String PRIORITY_COURSE="HpTq";
    private static final String DUAL_COURSE="HpSs";
    private static final String FACULTY_OF_MANAGEMENT = "KhoaQly";
    private static int VERSION =1;
    private Context context;

    public HPdatabase(Context context ) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    //Tạo bảng
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE "+TABLE_NAME+" ( "+
                ID+" Text primary key, "+
                NAME+" Text, "+
                CREDITS+ " Text, "+
                PRIORITY_COURSE+" Text, "+
                DUAL_COURSE+" Text, "+
                FACULTY_OF_MANAGEMENT+" Text )";
        // thực thi tạo bảng
        sqLiteDatabase.execSQL(sql);
    }

    //Xóa bảng
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    //Thêm
    public void AddHP(HocPhan hp)
    {
        // gọi CSDL
        SQLiteDatabase db=this.getWritableDatabase();
        // tạo đối tượng values chứa các nội dung của sv
        ContentValues values=new ContentValues();
        values.put(ID,hp.getMaHp());
        values.put(NAME,hp.getTenHp());
        values.put(CREDITS,hp.getSoTc());
        values.put(PRIORITY_COURSE,hp.getHpTq());
        values.put(DUAL_COURSE,hp.getHpSs());
        values.put(FACULTY_OF_MANAGEMENT,hp.getKhoaQly());
        // chèn values vào CSDL
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    //Sửa

    public int UpdateHP(HocPhan hp)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NAME,hp.getTenHp());
        values.put(CREDITS,hp.getSoTc());
        values.put(PRIORITY_COURSE,hp.getHpTq());
        values.put(DUAL_COURSE,hp.getHpSs());
        values.put(FACULTY_OF_MANAGEMENT,hp.getKhoaQly());
        int kq = db.update(TABLE_NAME,values,ID+"=?",new String[]{hp.getMaHp()});
        db.close();
        return kq;

    }
    //Xóa
    public int DeleteHP(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        int kq = db.delete(TABLE_NAME,ID+"=?",new String[]{id});
        db.close();
        return kq;

    }
    //Lấy toàn bộ thông tin HP trong CSDL
    public List<HocPhan> InformationHP(){
        List<HocPhan> listHP = new ArrayList<HocPhan>();
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="SELECT * FROM "+TABLE_NAME;
        // thực thi
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                HocPhan hp1=new HocPhan();
                hp1.setMaHp(cursor.getString(0));
                hp1.setTenHp(cursor.getString(1));
                hp1.setSoTc(cursor.getString(2));
                hp1.setHpTq(cursor.getString(3));
                hp1.setHpSs(cursor.getString(4));
                hp1.setKhoaQly(cursor.getString(5));
                listHP.add(hp1);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listHP;
    }
    public HocPhan findById(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME,new String[]{ID, NAME, CREDITS, PRIORITY_COURSE, DUAL_COURSE, FACULTY_OF_MANAGEMENT}, ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null){
            try {
                cursor.moveToFirst();
                HocPhan hp = new HocPhan();
                hp.setMaHp(cursor.getString(0));
                hp.setTenHp(cursor.getString(1));
                hp.setSoTc(cursor.getString(2));
                hp.setHpTq(cursor.getString(3));
                hp.setHpSs(cursor.getString(4));
                hp.setKhoaQly(cursor.getString(5));
                cursor.close();
                db.close();
                return hp;
            }
            catch (Exception e){
                return null;
            }
        }
        return null;
    }


}
