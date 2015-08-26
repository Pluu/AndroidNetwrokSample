package com.pluu.androidnetworksample.jsoup;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.pluu.androidnetworksample.R;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

public class VersionCheckActivity extends AppCompatActivity {

	@Bind(R.id.editId)
	EditText editId;
	@Bind(R.id.imgLogo)
	ImageView imgLogo;
	@Bind(R.id.txtAppName)
	TextView txtAppName;
	@Bind(R.id.txtVerCode)
	TextView txtVerCode;
	@Bind(R.id.txtVerName)
	TextView txtVerName;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_version_check);
		ButterKnife.bind(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		OttoBusHolder.get().register(this);
	}

	@Override
	public void onPause() {
		OttoBusHolder.get().unregister(this);
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_version_check, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.action_settings) {
			return true;
		}

		switch (id) {
			case R.id.action_init:
				editId.setText("com.coupang.mobile");
				break;
			case R.id.action_clear:
				editId.setText("");
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	@OnClick(R.id.btnSubmit)
	public void submitClick() {
		if (TextUtils.isEmpty(editId.getText())) {
			Toast.makeText(this, "Empty Package Name", Toast.LENGTH_SHORT).show();
			return;
		}

		dialog = ProgressDialog.show(this, null, "Loading");
		GooglePlayUtil.checkVersion(this, editId.getText().toString());
	}

	@Subscribe
	public void responseNetwork(GooglePlayEvent result) {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}

		if (!result.isSuccess) {
			Toast.makeText(this, "Check Fail", Toast.LENGTH_SHORT).show();
			return;
		}

		Picasso.with(this)
			.load(result.imgUrl)
			.into(imgLogo);

		txtAppName.setText(result.appName);
		txtVerCode.setText(result.verCode);
		txtVerName.setText(result.verName);
	}

}
