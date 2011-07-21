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
import br.com.jeramobstats.JeraAgent;

public class SubmitDialog extends Dialog {

	private class SubmitOnClickListener implements View.OnClickListener {

		public SubmitOnClickListener(int score, int mapId, Activity context) {
			this.score = score;
			this.mapId = mapId;
			this.context = context;
		}

		public void onClick(View v) {
			boolean scoreSent = false;
			JeraAgent.logEvent("SUBMIT_SCORE_CLICKED");
			if (System.currentTimeMillis() - scoreSendTime > 5000) {
				EditText text = (EditText) findViewById(R.id.submitName);
				CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);

				String name = text.getText().toString();
				if (name != "" && name.length() > 1) {
					scoreSent = sendScore(name, new Integer(score).toString(), new Integer(mapId).toString(), checkBox.isChecked(), context);
				} else {
					JeraAgent.logEvent("SUBMIT_SCORE_INVALID_NAME");
					TDActivity.toast(R.string.valid_name, context);
				}
				scoreSendTime = System.currentTimeMillis();
			}
			if (scoreSent)
				dismiss();
			else
				JeraAgent.logEvent("SUBMIT_SCORE_FAILED");
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

		updateScore(score, gameWon, mapId, context);
		Button button = (Button) findViewById(R.id.cancelButton);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				JeraAgent.logEvent("SUBMIT_SCORE_CANCELLED");
				dismiss();
			}
		});
	}

	private void manageText(Activity context, boolean gameWon) {
		TextView textView = (TextView) findViewById(R.id.send_score_text);
		textView.setText((gameWon) ? R.string.game_won : R.string.game_lost);
		Pattern pattern = Pattern.compile("Cloud Server Pro");
		String scheme = PropertyReader.getOfficialSiteUrl() + "#";
		Linkify.addLinks(textView, pattern, scheme);

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
			String u = JNIImport.getu();
			HttpPost post = new HttpPost(u);

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
				throw new PostException(statusCode);
			}
			TDActivity.toast(R.string.submit_success, context);
		} catch (PostException e) {
			if (e.getStatusCode() == HttpStatus.SC_NOT_FOUND) {
				TDActivity.toast(R.string.not_found, context);
			} else {
				TDActivity.toast(R.string.submit_fail, context);
			}
			return false;
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
