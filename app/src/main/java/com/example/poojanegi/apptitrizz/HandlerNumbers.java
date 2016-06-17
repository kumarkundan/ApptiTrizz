package com.example.poojanegi.apptitrizz;

/**
 * Created by kkajnabi on 5/26/2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class HandlerNumbers extends SQLiteOpenHelper {

    private final Context context;

    private static final String DB_NAME = "numbers.db";
    public static final String DB_PATH = "data/data/com.example.poojanegi.apptitrizz/databases/";
    private SQLiteDatabase mDatabase;

    //TABLE INFO
    public static final String TABLE_NAME = "numbers";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "questions";
    public static final String KEY_ANS1 = "answers1";
    public static final String KEY_ANS2 = "answers2";
    public static final String KEY_ANS3 = "answers3";
    public static final String KEY_ANS4 = "answers4";
    public static final String KEY_SOL = "solutions";
    public static final String KEY_CANS = "canswers";
    public static final String[] ALL_KEY = {KEY_ID, KEY_NAME, KEY_ANS1,KEY_ANS2,KEY_ANS3,KEY_ANS4,KEY_SOL,KEY_CANS};

    public HandlerNumbers(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    public void createDatabase() {
        boolean checkExist = checkDatabase();
        if (checkExist) {

        } else {
            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkDatabase() {
        SQLiteDatabase chkDatabase = null;
        String myPath = DB_PATH + DB_NAME;
//        chkDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
//        if (chkDatabase != null)
//            chkDatabase.close();
//        return chkDatabase != null ? true : false;
        File file = new File(myPath);
        return file.exists();
    }

    public void copyDatabase() throws IOException {
        InputStream mInputStream = context.getAssets().open(DB_NAME);
        String outPath = DB_PATH + DB_NAME;
        OutputStream mOutputStream = new FileOutputStream(outPath);

        byte[] buff = new byte[1024];
        int lengh;
        while ((lengh = mInputStream.read(buff)) > 0) {
            mOutputStream.write(buff, 0, lengh);
        }
        mOutputStream.flush();
        mOutputStream.close();
        mInputStream.close();
    }

    public void openDatabase() {
        String myPath = DB_PATH + DB_NAME;
        mDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if (mDatabase != null)
            mDatabase.close();
        super.close();
    }

    public ArrayList<DataNumbers> getAllStudents() {
        ArrayList<DataNumbers> list = new ArrayList<DataNumbers>();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DataNumbers s = new DataNumbers();
            s.setId(cursor.getInt(5));
            s.setQuestion(cursor.getString(6));
            s.setAnswer1(cursor.getString(7));
            s.setAnswer2(cursor.getString(4));
            s.setAnswer3(cursor.getString(3));
            s.setAnswer4(cursor.getString(2));
            s.setCanswer(cursor.getString(0));
            s.setSolution(cursor.getString(1));
            list.add(s);

            Log.v("here ONNNNLYY",cursor.getString(5));

            cursor.moveToNext();
        }
        return list;
    }

    public DataNumbers getStudent(int id) {
        DataNumbers s = new DataNumbers();
        Cursor cursor = mDatabase.query(TABLE_NAME, ALL_KEY, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();

        s.setId(cursor.getInt(5));
        s.setQuestion(cursor.getString(6));
        s.setAnswer1(cursor.getString(7));
        s.setAnswer2(cursor.getString(4));
        s.setAnswer3(cursor.getString(3));
        s.setAnswer4(cursor.getString(2));
        s.setCanswer(cursor.getString(0));
        s.setSolution(cursor.getString(1));

        return s;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}