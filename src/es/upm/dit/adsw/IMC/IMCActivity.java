package es.upm.dit.adsw.IMC;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class IMCActivity extends ActionBarActivity {
	
    public void calcular(View c) {
    	try {
    		EditText altura = (EditText) findViewById(R.id.editAltura);
    		EditText peso = (EditText) findViewById(R.id.editPeso);
        	
        	String stringpeso = peso.getText().toString();
        	float valorpeso = Float.parseFloat(stringpeso);
        	String stringaltura = altura.getText().toString();
        	float valoraltura = Float.parseFloat(stringaltura)/100;
        	
        	if (valoraltura!=0) {
        		double resultado = Math.rint((valorpeso/(valoraltura*valoraltura))*100)/100;
            	
            	TextView result = (TextView) findViewById(R.id.tvIMCresult);
            	result.setText("" + resultado);
            	
            	TextView clasif = (TextView) findViewById(R.id.tvIMCclasif);
            	if (resultado < 18.50 && resultado >= 16.00) clasif.setText("Infrapeso");
            	else if (resultado < 16.00) clasif.setText("Delgadez severa");
            	else if (resultado >= 16.00 && resultado <= 16.99) clasif.setText("Delgadez moderada");
            	else if (resultado >= 17.00 && resultado <= 18.49) clasif.setText("Delgadez no muy pronunciada");
            	else if (resultado >= 18.5 && resultado <= 24.99) clasif.setText("Normal");
            	else if (resultado >= 25.00 && resultado <= 29.99) clasif.setText("Sobrepeso (Preobeso)");
            	else if (resultado >= 30.00) clasif.setText("Sobrepeso (Obeso)");
        	}
        	else {
        		Toast toast = Toast.makeText(this,"Introduzca una altura válida",Toast.LENGTH_SHORT);
        		toast.show();
        	}
    	}
    	catch (Exception e) {
    		Toast toast = Toast.makeText(this,"Error",Toast.LENGTH_SHORT);
    		toast.show();
    	}
    }
    
   public void calcular2 (View c) {
	   try {
		   int s=1;
		   int edad=0;
		   double resultado=0;
		   RadioGroup radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
		   int selectedId = radioSexGroup.getCheckedRadioButtonId();
		   RadioButton radioSexButton = (RadioButton) findViewById(selectedId);
		   
		   if (radioSexButton == findViewById(R.id.radioMale)) s=1;
		   else if (radioSexButton == findViewById(R.id.radioFemale)) s=0;
		   
		   TextView imc = (TextView) findViewById(R.id.tvIMCresult);
		   String stringimc = imc.getText().toString();
		   float valorimc = Float.parseFloat(stringimc);
		   
		   DatePicker datePick = (DatePicker) findViewById(R.id.datePicker);
		   int year = datePick.getYear();
		   edad = 2014 - year;
		   
		   if (edad >= 0) {
			   if (edad < 16) resultado = Math.rint((1.51*valorimc - 0.70*edad - 3.6*s + 1.4)*100)/100;
			   else if (edad >= 16) resultado = Math.rint((1.20*valorimc + 0.23*edad - 10.8*s - 5.4)*100)/100;
			   
			   TextView result = (TextView) findViewById(R.id.tvIGCEresult);
			   result.setText("" + resultado + "%");
		   }
		   else {
			   Toast toast = Toast.makeText(this,"Introduzca una edad válida",Toast.LENGTH_SHORT);
			   toast.show();
		   }
	   }
	   catch (Exception e) {
		   Toast toast = Toast.makeText(this,"Error",Toast.LENGTH_SHORT);
		   toast.show();
	   }
    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imc);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.imc, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_imc, container,
					false);
			return rootView;
		}
	}

}
