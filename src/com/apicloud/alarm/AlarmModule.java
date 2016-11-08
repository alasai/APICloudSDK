package com.apicloud.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.annotation.UzJavascriptMethod;
import com.uzmap.pkg.uzcore.uzmodule.UZModule;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;

public class AlarmModule extends UZModule {
	/**
	 * 闹钟管理
	 */
	private AlarmManager alarm = null;
	/**
	 * 意图
	 */
	private Intent intent = null;

	public AlarmModule(UZWebView webView) {
		super(webView);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 启动闹钟
	 * 
	 * @param moduleContext
	 */
	@UzJavascriptMethod
	public void jsmethod_start(UZModuleContext moduleContext) {
		AlarmReceiver.uzContext = moduleContext;
		AlarmReceiver.isCancel = false;
		int delayTime = moduleContext.optInt("delayTime");
		int interval = moduleContext.optInt("interval");
		// 系统真是时间
		Log.e("闹钟", "开始......");
		long firstime = SystemClock.elapsedRealtime();
		if (alarm == null)
			alarm = (AlarmManager) mContext
					.getSystemService(mContext.ALARM_SERVICE);
		intent = new Intent(mContext, AlarmReceiver.class);
		intent.setAction("alerm");
		PendingIntent sender = PendingIntent.getBroadcast(mContext, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		alarm.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime
				+ delayTime, interval, sender);
		Log.e("闹钟", "启动成功！");
	}

	/**
	 * 停止闹钟
	 * 
	 * @param moduleContext
	 */
	@UzJavascriptMethod
	public void jsmethod_stop(UZModuleContext moduleContext) {
		AlarmReceiver.isCancel = true;
		AlarmReceiver.uzContext = moduleContext;
		stopAlarm();
	}

	private void stopAlarm() {
		if (alarm != null) {			
			alarm.cancel(PendingIntent.getBroadcast(mContext, 0, intent,
					PendingIntent.FLAG_CANCEL_CURRENT));
		}
		Log.e("闹钟", "取消闹钟");

	}

	/**
	 * 获取gps数据
	 * 
	 * @param moduleContext
	 */
	@UzJavascriptMethod
	public void jsmethod_check(UZModuleContext moduleContext) {
		String moduString = moduleContext.optString("message");
		if (moduString == null) {
			AlarmReceiver.isCancel = true;
			AlarmReceiver.uzContext = moduleContext;
			stopAlarm();
		}
		//Toast.makeText(mContext, moduString, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 获取gps数据
	 * 
	 * @param moduleContext
	 */
	@UzJavascriptMethod
	public void jsmethod_getgps(UZModuleContext moduleContext) {

	}

}
