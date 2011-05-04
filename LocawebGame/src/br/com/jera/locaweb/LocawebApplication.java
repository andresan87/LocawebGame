package br.com.jera.locaweb;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;

@ReportsCrashes(formKey = "dFo0al9ucEp2dmNEM0VKM1pjcWN5OFE6MQ")
public class LocawebApplication extends Application {
	@Override
	public void onCreate() {
		ACRA.init(this);
		super.onCreate();
	}
}
