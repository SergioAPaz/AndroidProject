package com.example.sergioapaz.proyectofinal10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1,et2,et3,et4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText3);
        et4=(EditText)findViewById(R.id.editText4);
    }

    public void alta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombr = et1.getText().toString();
        String eda = et2.getText().toString();
        String altur = et3.getText().toString();
        String sex = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombr);
        registro.put("edad", eda);
        registro.put("altura", altur);
        registro.put("sexo", sex);
        bd.insert("personas", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        Toast.makeText(this, "Se cargaron todos los datos con exito",
                Toast.LENGTH_SHORT).show();
    }

    public void consultapornombre(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombr = et1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select edad,altura,sexo from personas where nombre='" + nombr +"'", null);
        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe una persona con ese nombre",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }



    public void bajapornombre(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombr= et1.getText().toString();
        int cant = bd.delete("personas", "nombre='" + nombr +"'", null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        if (cant == 1)
            Toast.makeText(this, "Resgistro eliminado",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe el nombre",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombr = et1.getText().toString();
        String eda = et2.getText().toString();
        String altur = et3.getText().toString();
        String sex = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombr);
        registro.put("edad", eda);
        registro.put("altura", altur);
        registro.put("sexo", sex);
        int cant = bd.update("personas", registro, "nombre='" + nombr+ "'", null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe una persona con el nombre introducido",
                    Toast.LENGTH_SHORT).show();
    }
}
