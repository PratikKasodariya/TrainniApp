package com.softices.lit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.widget.Toast;

import com.softices.lit.models.StudentModel;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Softices on 3/24/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "lit";

    public static final String TABLE_STUDENT = "student";

    public static final String STUDENT_ENROLLMENT = "Enrollment";
    public static final String STUDENT_FIRST_NAME = "First_Name";
    public static final String STUDENT_LAST_NAME = "Last_Name";
    public static final String STUDENT_PASSWORD = "Password";
    public static final String STUDENT_EMAIL = "Email";
    public static final String STUDENT_SEM = "Sem";
    public static final String STUDENT_DEPATRMENT = "Department";
    public static final String STUDENT_MOBILE_NO = "Mobile_No";
    public static final String STUDENT_ADDRESS = "Address";
    public static final String STUDENT_CPI = "CPI";


    public static final String TABLE_FACULTY = "faculty";

    public static final String FACULTY_EMAIL = "Email";
    public static final String FACULTY_FIRST_NAME = "First_name";
    public static final String FACULTY_LAST_NAME = "Last_name";
    public static final String FACULTY_PASSWORD = "Password";
    public static final String FACULTY_SUBJECTS = "Subjects";
    public static final String FACULTY_DEPARTMENT = "Department";
    public static final String FACULTY_MOBILE_NO = "Mobile_no";
    public static final String FACULTY_ADDRESS = "Address";
    public static final String FACULTY_DEGREE = "Drgree";
    public static final String FACULTY_ADD_LACTURE = "Image";


    public static final String TABLE_HOD = "hod";

    public static final String HOD_EMAIL = "Email";
    public static final String HOD_FIRST_NAME = "First_name";
    public static final String HOD_LAST_NAME = "Last_name";
    public static final String HOD_PASSWORD = "Password";
    public static final String HOD_SUBJECTS = "Subjects";
    public static final String HOD_DEPARTMENT = "Department";
    public static final String HOD_MOBILE_NO = "Mobile_no";
    public static final String HOD_ADDRESS = "Address";
    public static final String HOD_DEGREE = "Drgree";
    public static final String HOD_ADD_LACTURE = "Image";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String CREATE_TABLE_STUDENT =
            "CREATE TABLE " + TABLE_STUDENT + " ("
                    + STUDENT_EMAIL + " TEXT PRIMARY KEY,"
                    + STUDENT_FIRST_NAME + " TEXT,"
                    + STUDENT_LAST_NAME + " TEXT,"
                    + STUDENT_PASSWORD + " TEXT,"
                    + STUDENT_SEM + " TEXT,"
                    + STUDENT_DEPATRMENT + " TEXT,"
                    + STUDENT_MOBILE_NO + " TEXT,"
                    + STUDENT_ADDRESS + " TEXT,"
                    + STUDENT_ENROLLMENT + " LONG INTEGER,"
                    + STUDENT_CPI + "FLOAT"
                    + ")";


    public static final String CREATE_TABLE_FACULTY =
            "CREATE TABLE " + TABLE_FACULTY + " ("
                    + FACULTY_EMAIL + " TEXT PRIMARY KEY,"
                    + FACULTY_FIRST_NAME + " TEXT,"
                    + FACULTY_LAST_NAME + " TEXT,"
                    + FACULTY_PASSWORD + " TEXT,"
                    + FACULTY_DEPARTMENT + " TEXT,"
                    + FACULTY_MOBILE_NO + " TEXT,"
                    + FACULTY_ADDRESS + " TEXT,"
                    + FACULTY_DEGREE + " TEXT,"
                    + FACULTY_SUBJECTS + " TEXT,"
                    + FACULTY_ADD_LACTURE + " BLOB"
                    + ")";

    public static final String CREATE_TABLE_HOD =
            "CREATE TABLE " + TABLE_HOD + " ("
                    + HOD_EMAIL + " TEXT PRIMARY KEY,"
                    + HOD_FIRST_NAME + " TEXT,"
                    + HOD_LAST_NAME + " TEXT,"
                    + HOD_PASSWORD + " TEXT,"
                    + HOD_DEPARTMENT + " TEXT,"
                    + HOD_MOBILE_NO + " TEXT,"
                    + HOD_ADDRESS + " TEXT,"
                    + HOD_DEGREE + " TEXT,"
                    + HOD_SUBJECTS + " TEXT,"
                    + HOD_ADD_LACTURE + " BLOB"
                    + ")";

    @Override
    public void onCreate(SQLiteDatabase student) {
        // create notes table
        try {
            student.execSQL(CREATE_TABLE_STUDENT);
            student.execSQL(CREATE_TABLE_FACULTY);
            student.execSQL(CREATE_TABLE_HOD);
            Log.e("mye", "table" + CREATE_TABLE_STUDENT);
            Log.e("mye", "table" + CREATE_TABLE_FACULTY);
            Log.e("mye", "table" + CREATE_TABLE_HOD);
        } catch (Exception e) {
            Log.e("mye", "onCreate" + e);
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS student" + DataBaseHelper.TABLE_STUDENT);
            db.execSQL("DROP TABLE IF EXISTS faculty" + DataBaseHelper.TABLE_FACULTY);
            db.execSQL("DROP TABLE IF EXISTS hod" + DataBaseHelper.TABLE_HOD);
            onCreate(db);
        } catch (Exception e) {
            Log.e("mye", "onUpgrade" + e);
        }
    }

    public void addImageToFaculty(Context context, String name, byte[] Image) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FACULTY_ADD_LACTURE, Image);
        database.update(TABLE_FACULTY, cv, FACULTY_EMAIL + " = ?", new String[]{name});
        database.close();
        Toast.makeText(context, "image saved" + name, Toast.LENGTH_SHORT).show();
    }

    public Cursor fetchProfileImageFromDatabase(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT Image FROM faculty WHERE Email = " + email;
        return db.rawQuery(query, null);
    }

    public List<StudentModel> getAllStudent() {
        String[] columns = {
                STUDENT_FIRST_NAME,
                STUDENT_LAST_NAME,
                STUDENT_ENROLLMENT,
                STUDENT_EMAIL,
                STUDENT_MOBILE_NO,
        };

        String sortOrder = STUDENT_FIRST_NAME + " ASC";
        List<StudentModel> userList = new ArrayList<StudentModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDENT,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);
        if (cursor.moveToFirst()) {
            do {
                StudentModel user = new StudentModel();
                user.setFirstName(cursor.getString(cursor.getColumnIndex(STUDENT_FIRST_NAME)));
                user.setLastName(cursor.getString(cursor.getColumnIndex(STUDENT_LAST_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(STUDENT_EMAIL)));
                user.setMobileNo(cursor.getString(cursor.getColumnIndex(STUDENT_MOBILE_NO)));
                user.setEnrollment(cursor.getString(cursor.getColumnIndex(STUDENT_ENROLLMENT)));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }
}