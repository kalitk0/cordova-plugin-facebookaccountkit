package org.apache.cordova.facebook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;


import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AccountKitPlugin extends CordovaPlugin {
	private static final int FRAMEWORK_REQUEST_CODE = 1;

   
    private final String TAG = "AccountKitPlugin";


    @Override
    protected void pluginInitialize() {

    //	AccountKit.initialize(cordova.getActivity().getApplicationContext());
       
       
    }

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        // Developers can observe how frequently users activate their app by logging an app activation event.
      //  AppEventsLogger.activateApp(cordova.getActivity());
    }

    @Override
    public void onPause(boolean multitasking) {
        super.onPause(multitasking);

    }
    
    /* public void onLoginPhone() {
        onLogin(LoginType.PHONE);
    }
    
    private void onLogin(final LoginType loginType) {
        final Intent intent = new Intent(this, AccountKitActivity.class);
        final AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder
                = new AccountKitConfiguration.AccountKitConfigurationBuilder(
                loginType,
                AccountKitActivity.ResponseType.TOKEN);
        final AccountKitConfiguration configuration = configurationBuilder.build();
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configuration);
        OnCompleteListener completeListener = new OnCompleteListener() {
            @Override
            public void onComplete() {
                startActivityForResult(intent, FRAMEWORK_REQUEST_CODE);
            }
        };
        switch (loginType) {
            case EMAIL:
                final OnCompleteListener getAccountsCompleteListener = completeListener;
                completeListener = new OnCompleteListener() {
                    @Override
                    public void onComplete() {
                        
                    }
                };
                break;
            case PHONE:
                if (configuration.isReceiveSMSEnabled()) {
                    final OnCompleteListener receiveSMSCompleteListener = completeListener;
                    completeListener = new OnCompleteListener() {
                        @Override
                        public void onComplete() {
                            
                        }
                    };
                }
                if (configuration.isReadPhoneStateEnabled()) {
                    final OnCompleteListener readPhoneStateCompleteListener = completeListener;
                    completeListener = new OnCompleteListener() {
                        @Override
                        public void onComplete() {
                           
                        }
                    };
                }
                break;
        }
        completeListener.onComplete();
    }
*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d(TAG, "activity result in plugin: requestCode(" + requestCode + "), resultCode(" + resultCode + ")");
     	final String toastMessage;

    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals("login")) {
           // onLoginPhone();
            return true;

        } else if (action.equals("logout")) {
         /*   if (hasAccessToken()) {
              //  LoginManager.getInstance().logOut();
                callbackContext.success();
            } else {
                callbackContext.error("No valid session found, must call init and login before logout.");
            }*/
            return true;

        } else if (action.equals("getLoginStatus")) {
          //  callbackContext.success(getResponse());
            return true;

        } else if (action.equals("getAccessToken")) {
        /*    if (hasAccessToken()) {
            //    callbackContext.success(AccessToken.getCurrentAccessToken().getToken());
            } else {
                // Session not open
                callbackContext.error("Session not open.");
            }*/
            return true;

        } else if (action.equals("logEvent")) {
         //   executeLogEvent(args, callbackContext);
            return true;

        }
        return false;
    }



    // Simple active session check
   // private boolean hasAccessToken() {
       // return AccessToken.getCurrentAccessToken() != null;
  //  }



    /**
     * Create a Facebook Response object that matches the one for the Javascript SDK
     * @return JSONObject - the response object
     */
  /*  public JSONObject getResponse() {
        String response;
        final AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (hasAccessToken()) {
            Date today = new Date();
            long expiresTimeInterval = (accessToken.getExpires().getTime() - today.getTime()) / 1000L;
            response = "{"
                + "\"status\": \"connected\","
                + "\"authResponse\": {"
                + "\"accessToken\": \"" + accessToken.getToken() + "\","
                + "\"expiresIn\": \"" + Math.max(expiresTimeInterval, 0) + "\","
                + "\"session_key\": true,"
                + "\"sig\": \"...\","
                + "\"userID\": \"" + accessToken.getUserId() + "\""
                + "}"
                + "}";
        } else {
            response = "{"
                + "\"status\": \"unknown\""
                + "}";
        }
        try {
            return new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    */

    /**
     * Wraps the given object if necessary.
     *
     * If the object is null or , returns {@link #JSONObject.NULL}.
     * If the object is a {@code JSONArray} or {@code JSONObject}, no wrapping is necessary.
     * If the object is {@code JSONObject.NULL}, no wrapping is necessary.
     * If the object is an array or {@code Collection}, returns an equivalent {@code JSONArray}.
     * If the object is a {@code Map}, returns an equivalent {@code JSONObject}.
     * If the object is a primitive wrapper type or {@code String}, returns the object.
     * Otherwise if the object is from a {@code java} package, returns the result of {@code toString}.
     * If wrapping fails, returns null.
     */
    private static Object wrapObject(Object o) {
        if (o == null) {
            return JSONObject.NULL;
        }
        if (o instanceof JSONArray || o instanceof JSONObject) {
            return o;
        }
        if (o.equals(JSONObject.NULL)) {
            return o;
        }
        try {
            if (o instanceof Collection) {
                return new JSONArray((Collection) o);
            } else if (o.getClass().isArray()) {
                return new JSONArray(o);
            }
            if (o instanceof Map) {
                return new JSONObject((Map) o);
            }
            if (o instanceof Boolean ||
                o instanceof Byte ||
                o instanceof Character ||
                o instanceof Double ||
                o instanceof Float ||
                o instanceof Integer ||
                o instanceof Long ||
                o instanceof Short ||
                o instanceof String) {
                return o;
            }
            if (o.getClass().getPackage().getName().startsWith("java.")) {
                return o.toString();
            }
        } catch (Exception ignored) {
        }
        return null;
    }
}
