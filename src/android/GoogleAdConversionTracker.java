package com.jnuine.cordova;

import android.util.Log;

import com.google.ads.conversiontracking.AdWordsConversionReporter;
import com.google.ads.conversiontracking.AdWordsRemarketingReporter;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GoogleAdConversionTracker extends CordovaPlugin {

  @Override
  public boolean execute (String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    String conversionId = args.getString(0);

    if (action.equals("trackConversion")) {
      String trackingLabel = args.getString(1);
      String trackingValue = args.getString(2);
      Boolean repeatable = args.getBoolean(3);
      this.trackConversion(conversionId, trackingLabel, trackingValue, repeatable, callbackContext);
      return true;
    }
    else if (action.equals("trackRemarketingConversion")) {
      JSONObject params = args.getJSONObject(1);
      this.trackRemarketingConversion(conversionId, params, callbackContext);
      return true;
    }

    return false;
  }

  private void trackConversion (String conversionId, String trackingLabel, String trackingValue, Boolean repeatable, CallbackContext callbackContext) {

    try {
      // see: https://developers.google.com/app-conversion-tracking/docs/android-conversion-tracking
      AdWordsConversionReporter.reportWithConversionId(
        this.cordova.getActivity().getApplicationContext(),
        conversionId,
        trackingLabel,
        trackingValue,
        repeatable
      );
    } catch (final Exception e) {
      callbackContext.error("Error in GoogleAppConversion tracking.");
    }

  }

  private void trackRemarketingConversion (String conversionId, JSONObject params, CallbackContext callbackContext) {

    try {
      Map<String, Object> paramsMap = new HashMap<String, Object>();
      Iterator<String> keys = params.keys();
      String key = null;
      while (keys.hasNext()) {
        key = keys.next();
        paramsMap.put(key, params.get(key));
      }
      AdWordsRemarketingReporter.reportWithConversionId(
        this.cordova.getActivity().getApplicationContext(),
        conversionId,
        paramsMap
      );
    } catch (final Exception e) {
      e.printStackTrace();
      callbackContext.error("Error in GoogleAppConversion remarketing tracking.");
    }
  }

}
