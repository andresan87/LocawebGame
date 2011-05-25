package br.com.jera.locaweb;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.Dialog;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import br.com.jera.jni.JNIImport;
import br.com.jera.resources.PropertyReader;
import br.com.jera.towerdefenselib.StringEncrypter;
import br.com.jera.towerdefenselib.TDActivity;

public class SubmitDialog extends Dialog {
	
	private class SubmitOnClickListener implements View.OnClickListener {

		public SubmitOnClickListener(int score, int mapId, Activity context) {
			this.score = score;
			this.mapId = mapId;
			this.context = context;
		}
		
		public void onClick(View v) {
			boolean scoreSent = false;
			if (System.currentTimeMillis() - scoreSendTime > 5000) {
				EditText text = (EditText) findViewById(R.id.submitName);
				CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
				
				String name = text.getText().toString(); 
				if (name != "" && name.length() > 1) {
					scoreSent = sendScore(name, new Integer(score).toString(), new Integer(mapId).toString(), checkBox.isChecked(), context);
				} else {
					TDActivity.toast(R.string.valid_name, context);
				}
				scoreSendTime = System.currentTimeMillis();
			}
			if (scoreSent)
				dismiss();
		}

		private int score;
		private int mapId;
		private Activity context;
	}
	
	public void updateScore(int score, boolean gameWon, int mapId, Activity context) {
		Button button = (Button) findViewById(R.id.sendButton);
		button.setOnClickListener(new SubmitOnClickListener(score, mapId, context));
		manageText(context, gameWon);
	}

	public SubmitDialog(final Activity context, int score, int mapId, String version, boolean gameWon) {
		super(context);
		SubmitDialog.setVersion(version);

		setContentView(R.layout.submit);
		setupButtonActions(context);

		updateScore(score, gameWon, mapId, context);
		Button button = (Button) findViewById(R.id.cancelButton);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dismiss();
			}
		});
	}

	private void manageText(Activity context, boolean gameWon) {
		TextView textView = (TextView) findViewById(R.id.send_score_text);
		textView.setText((gameWon) ? R.string.game_won : R.string.game_lost);
		// Linkify.addLinks(textView, Linkify.WEB_URLS);
		Pattern pattern = Pattern.compile("Cloud Server Pro");
		String scheme = PropertyReader.getOfficialSiteUrl();
		Linkify.addLinks(textView, pattern, scheme);

	}
	
	private void setupButtonActions(final Activity context) {
		//View view;
		/*view = findViewById(R.id.locaweb_button);
		view.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(PropertyReader.getOfficialSiteUrl()));
				context.startActivity(intent);
			}
		});*/
		/*view = findViewById(R.id.facebook_button);
		view.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(PropertyReader.getFacebookUrl()));
				context.startActivity(intent);
			}
		});
		view = findViewById(R.id.twitter_button);
		view.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(PropertyReader.getTwitterUrl()));
				context.startActivity(intent);
			}
		});*/
	}
	
	protected static String generateCode(String name, String score) {
		String code = JNIImport.getc(name, score);
		return StringEncrypter.encodeSHA(code);
	}

	private static boolean sendScore(String name, String points, String mapId, boolean twitterAccount, Activity context) {
		DefaultHttpClient client = new DefaultHttpClient();
		String l = JNIImport.getl();
		String p = JNIImport.getp();
		Credentials creds = new UsernamePasswordCredentials(l, p);
		client.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), creds);

		try {
			HttpPost post = new HttpPost(JNIImport.getu());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("score", points));
			nameValuePairs.add(new BasicNameValuePair("name", name));
			nameValuePairs.add(new BasicNameValuePair("twitter", twitterAccount ? "1" : "0"));
			nameValuePairs.add(new BasicNameValuePair("version", version));
			
			String code = generateCode(name, points);
			
			nameValuePairs.add(new BasicNameValuePair("ticket", code));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = client.execute(post);
			final int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception();
			}
			TDActivity.toast(R.string.submit_success, context);
		} catch (Exception e) {
			TDActivity.toast(R.string.submit_fail, context);
			return false;
		}
		return true;
	}
	
	public static void setVersion(String version) {
		SubmitDialog.version = version;
	}

	private static String version;
	static private long scoreSendTime;

}
