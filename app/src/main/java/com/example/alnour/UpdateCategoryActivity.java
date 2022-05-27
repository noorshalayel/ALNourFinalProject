package com.example.alnour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateCategoryActivity extends AppCompatActivity {
    public TextInputEditText name;
    String id ;
    Button updateCategoryrbtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_update_category);

        name = findViewById(R.id.edit_cat_name);
        updateCategoryrbtn = findViewById(R.id.updateCategoryrbtn);

        Bundle b = getIntent().getExtras();

        if (b != null) {
            Object cus =  b.getSerializable("category");

            id = b.getString("id");
            name.setText(b.getString("name"));

        } else {
            name.setText("");
        }
        updateCategoryrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputValidation()) {
                    if (UpdateCategory()) {
                        finish();
                    }
                }
            }
        });
    }

public boolean UpdateCategory(){
    DatabaseReference refCus = FirebaseDatabase.getInstance().getReference("categories").child(id);

    String cat_name = name.getText().toString();

    Category cat  = new Category(id , cat_name);
    refCus.setValue(cat);
    Toast.makeText(this, "Updated Successfully " , Toast.LENGTH_SHORT).show();

    return  true ;
}


    public Boolean inputValidation() {
        boolean flag = true;

        if (name.getText().toString().isEmpty()) {
            name.setError("Can't be Empty");
            return false;
        }

        return flag;
    }
}