package eseo.eseoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


public class MainCV extends AppCompatActivity implements View.OnClickListener{

    //Defining views
    private EditText editTextNom;
    private EditText editTextMail;
    private EditText editTextTelephone;
    private EditText editTextPromo;
    private EditText editTextOption;
    private EditText editTextPro;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cv);

        //Initializing views
        editTextNom = (EditText) findViewById(R.id.editTextNom);
        editTextMail = (EditText) findViewById(R.id.editTextMail);
        editTextTelephone = (EditText) findViewById(R.id.editTextTelephone);
        editTextPromo = (EditText) findViewById(R.id.editTextPromo);
        editTextOption = (EditText) findViewById(R.id.editTextOption);
        editTextPro = (EditText) findViewById(R.id.editTextPro);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }


    //Ajoute un CV
    private void addCV(){

        final String nom = editTextNom.getText().toString().trim();
        final String mail = editTextMail.getText().toString().trim();
        final String telephone = editTextTelephone.getText().toString().trim();
        final String promo = editTextPromo.getText().toString().trim();
        final String option = editTextOption.getText().toString().trim();
        final String pro = editTextPro.getText().toString().trim();

        class AddCV extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainCV.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainCV.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_CV_NOM,nom);
                params.put(Config.KEY_CV_MAIL,mail);
                params.put(Config.KEY_CV_TELEPHONE,telephone);
                params.put(Config.KEY_CV_PROMO,promo);
                params.put(Config.KEY_CV_OPTION,option);
                params.put(Config.KEY_CV_PRO,pro);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD_CV, params);
                return res;
            }
        }

        AddCV addCV = new AddCV();
        addCV.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addCV();
        }

        if(v == buttonView){
            startActivity(new Intent(this,ViewAllCV.class));
        }
    }
}

