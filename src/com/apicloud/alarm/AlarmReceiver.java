package com.apicloud.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;

public class AlarmReceiver extends BroadcastReceiver {

	public static UZModuleContext uzContext = null;
	public static boolean isCancel = false;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
//		Toast.makeText(context, "闹钟...", Toast.LENGTH_SHORT).show();
		Log.e("闹钟", "c测试");
		if(uzContext != null){
			if(isCancel){
				uzContext.success("取消闹钟",false,false);	
			}else{
				uzContext.success("成功回调",false,false);	
			}					
		}
	}

}
