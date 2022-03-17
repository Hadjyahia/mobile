package hadj.tn.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.ResultSet;
import java.sql.Statement;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    EditText email,phone,password;
    Spinner spin;
    soup.neumorphism.NeumorphButton signup;

    String[] users = { "Person", "Hospital", "Blood bank" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        spin = (Spinner) findViewById(R.id.identity);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        signup = (soup.neumorphism.NeumorphButton) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connexion();
                Intent intent = new Intent(SignUpActivity.this,Sign_InActivity.class);
                startActivity(intent);
            }
        });

        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
    }

    private void connexion() {

        try {
            Fonctions fonc = new Fonctions();
            Statement st = fonc.connexionBDDSQL();

            String sql = "select * from user;";

            final ResultSet rs = st.executeQuery(sql);
            rs.next();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}