package com.jnuine.cordova;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.ads.conversiontracking.*;

public class GoogleAdConversionTracker extends CordovaPlugin {

  @Override
  public boolean execute (String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    String conversionId = args.getString(0);
    String trackingLabel = args.getString(1);
    String trackingValue = args.getString(2);
    Boolean repeatable = args.getBoolean(3);

    if (action.equals("trackConversion")) {
      this.trackConversion(conversionId, trackingLabel, trackingValue, repeatable, callbackContext);
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
}

