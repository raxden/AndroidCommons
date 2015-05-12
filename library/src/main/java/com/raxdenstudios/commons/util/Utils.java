package com.raxdenstudios.commons.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.location.LocationManager;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings.Secure;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.raxdenstudios.commons.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by Raxden on 11/05/2015.
 */
public class Utils {

    private static final String TAG = Utils.class.getSimpleName();

    /**
     * Check whether the given String has actual text.
     * @param value the String to check (may be <code>null</code>)
     * @return <code>true</code> if the String is not <code>null</code>, its length is
     * greater than 0, and it does not contain whitespace only
     */
    public static boolean hasValue(String value) {
        return value != null && StringUtils.hasText((String)value) && (!"null".equals((String)value));
    }

    /**
     * Check whether the given CharSequence has actual text.
     * @param value the CharSequence to check (may be <code>null</code>)
     * @return <code>true</code> if the String is not <code>null</code>, its length is
     * greater than 0, and it does not contain whitespace only
     */
    public static boolean hasValue(CharSequence value) {
        return value != null && StringUtils.hasText((CharSequence)value) && (!"null".equals(((CharSequence)value).toString()));
    }

    /**
     * Check whether the given Long has actual value.
     * @param value the Long to check (may be <code>null</code>)
     * @return <code>true</code> if the Long is not <code>null</code>, its value is different to 0
     */
    public static boolean hasValue(Long value) {
        return value != null && !(Long.valueOf(value.toString()) == 0);
    }

    /**
     * Check whether the given Float has actual value.
     * @param value the Float to check (may be <code>null</code>)
     * @return <code>true</code> if the Float is not <code>null</code>, its value is different to 0
     */
    public static boolean hasValue(Float value) {
        return value != null && !(Float.valueOf(value.toString()) == 0);
    }

    /**
     * Check whether the given Double has actual value.
     * @param value the Double to check (may be <code>null</code>)
     * @return <code>true</code> if the Double is not <code>null</code>, its value is different to 0
     */
    public static boolean hasValue(Double value) {
        return value != null && !(Double.valueOf(value.toString()) == 0);
    }

    /**
     * Check whether the given Number has actual value.
     * @param value the Number to check (may be <code>null</code>)
     * @return <code>true</code> if the Number is not <code>null</code>, its value is different to 0
     */
    public static boolean hasValue(Number value) {
        return value != null && !(Integer.valueOf(value.toString()) == 0);
    }

    /**
     * Check whether the given Integer has actual value.
     * @param value the Integer to check (may be <code>null</code>)
     * @return <code>true</code> if the Integer is not <code>null</code>, its value is different to 0
     */
    public static boolean hasValue(Integer value) {
        return value != null && !(Integer.valueOf(value.toString()) == 0);
    }

    /**
     * Check whether the given Map has actual value.
     * @param value the Map to check (may be <code>null</code>)
     * @return <code>true</code> if the Map is not <code>null</code>,  its size is not empty
     */
    public static boolean hasValue(Map value) {
        return value != null && !((Map)value).isEmpty();
    }

    /**
     * Check whether the given List has actual value.
     * @param value the List to check (may be <code>null</code>)
     * @return <code>true</code> if the List is not <code>null</code>, its size is not empty
     */
    public static boolean hasValue(List value) {
        return value != null && !((List)value).isEmpty();
    }

    /**
     * Check whether the given TextView has actual text.
     * @param value the TextView to check (may be <code>null</code>)
     * @return <code>true</code> if the TextView is not <code>null</code>, its length is
     * greater than 0, and it does not contain whitespace only
     */
    public static boolean hasValue(TextView value) {
        return value != null && hasValue(((TextView)value).getText());
    }

    /**
     * Check whether the given Object has actual value.
     * @param value the Object to check (may be <code>null</code>)
     * @return <code>true</code> if the Object is not <code>null</code>
     */
	public static boolean hasValue(Object value) {
		if (value == null) return false;
		else if (value instanceof String) return hasValue((String)value);
		else if (value instanceof CharSequence) return hasValue((CharSequence)value);
        else if (value instanceof Long) return hasValue((Long)value);
        else if (value instanceof Float) return hasValue((Float)value);
        else if (value instanceof Double) return hasValue((Double)value);
        else if (value instanceof Number) return hasValue((Number)value);
        else if (value instanceof Integer) return hasValue((Integer)value);
        else if (value instanceof Map) return hasValue((Map)value);
        else if (value instanceof List) return hasValue((List)value);
        else if (value instanceof TextView) return hasValue((TextView)value);
        return true;
	}

	/**
	 * Close keyboard on screen.
	 * @param context
	 * @param binder Get binder from view -> view.getWindowToken()
	 */
	public static void closeKeyboard(Context context, IBinder binder) {
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(binder, 0);
	}

    /**
     * Este método retorna la cuenta principal de google. Si el usuario tiene más de una cuenta
     * configurada se retornará la primera. El uso de este método requiere del siguiente permiso
     * android.permission.GET_ACCOUNTS
     * @param context
     * @return email
     */
	public static String getMainGoogleEmailAccount(Context context) {
		String email = "";
		AccountManager accountManager = AccountManager.get(context);
		Account[] accounts = accountManager.getAccountsByType("com.google");
		for (Account a: accounts) {
			if (hasValue(a.name)) {
		    	email = a.name;
		    	break;
		    }
		}
		return email;
	}

    /**
     * Este método retorna las cuentas de google configuradas en el dispositivo.
     * configurada se retornará la primera. El uso de este método requiere del siguiente permiso
     * android.permission.GET_ACCOUNTS
     * @param context
     * @return Un listado de cuentas de correo.
     */
	public static List<String> getGoogleEmailAccounts(Context context) {
		List<String> emails = new ArrayList<String>();
		AccountManager accountManager = AccountManager.get(context);
		Account[] accounts = accountManager.getAccountsByType("com.google");
		for (Account a: accounts) {
			if (hasValue(a.name)) {
//			if (a.name.contains("@gmail.com")) {
		    	emails.add(a.name);
		    }
		}
		return emails;
	}	
	
	/**
	 * A 64-bit number (as a hex string) that is randomly generated on the device's first boot and should remain 
	 * constant for the lifetime of the device. (The value may change if a factory reset is performed on the device.)
	 * @param context
	 * @return Identificador único de dispositivo.
	 */
	public static String getSecureAndroidId(Context context) {
		return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
	}
	
	@SuppressWarnings("serial")
	private static final Map<Integer,String> dpiMap = new HashMap<Integer, String>() {
		{
			put(120, "l");
			put(160, "m");
			put(240, "h");
			put(320, "xh");
			put(480, "xxh");
			put(640, "xxxh");
		}
	};
	
	public static String getDensityKeyDisplay(Context context) {
		String dpi = null;
		int value = getDensityDisplay(context);
		if (dpiMap.containsKey(value)) {
			dpi = dpiMap.get(value);
		} else {
			int rest = 999;
			for (Entry<Integer, String> entry : dpiMap.entrySet()) {
				if (rest >= Math.abs(value - entry.getKey())) {
					rest = Math.abs(value - entry.getKey());
					dpi = entry.getValue();
				}
			}				
		}
		return dpi;
	}		
	
	public static int getDensityDisplay(Context context) {
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		return dm.densityDpi;
	}

    /**
     * This method indicates whether device has virtual menu keys such as the nexus
     * @param context
     * @return <code>true</code> if device has virtual menu keys.
     */
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public static boolean hasVirtualMenuKeys(Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return !ViewConfiguration.get(context).hasPermanentMenuKey();
		} else {
			return false;
		}
	}

    /**
     * This method return information about device.
     * @return information about device
     */
	public static String getDeviceModel() {
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;
		if (model.startsWith(manufacturer)) {
			return StringUtils.capitalize(model);
		} else {
			return StringUtils.capitalize(manufacturer) + " " + model;
		}
	}

    /**
     * This method return the size of the screen
     * @param context
     * @return <code>int[]</code> dimensions[0] = width, dimensions[1] = height
     */
	@SuppressLint("NewApi")
	public static int[] getScreenDimension(Context context) {
		int[] dimensions = new int[2];
		
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		
		if (hasHoneycombMR2()) {
			Point size = new Point();
			display.getSize(size);
			dimensions[0] = size.x;
			dimensions[1] = size.y;
		} else {
			dimensions[0] = display.getWidth();
			dimensions[1] = display.getHeight();
		}
		
		return dimensions;
	}

    /**
     * Checks if device is a tablet
     * @param context
     * @return <code>true</code> if device is a tablet.
     */
	public static boolean isTablet(Context context) {
	    return context.getResources().getBoolean(R.bool.isTablet);
	}


    /**
     * Checks if the device has Froyo or higher version.
     * @return <code>true</code> if device is a tablet.
     */
    public static boolean hasFroyo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    /**
     * Checks if the device has Gingerbread or higher version.
     * @return <code>true</code> if device is a tablet.
     */
    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    /**
     * Checks if the device has Honeycomb or higher version.
     * @return <code>true</code> if device is a tablet.
     */
    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    /**
     * Checks if the device has HoneycombMR1 or higher version.
     * @return <code>true</code> if device is a tablet.
     */
    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    /**
     * Checks if the device has HoneycombMR2 or higher version.
     * @return <code>true</code> if device is a tablet.
     */
    public static boolean hasHoneycombMR2() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2;
    }

    /**
     * Checks if the device has JellyBean or higher version.
     * @return <code>true</code> if device is a tablet.
     */
    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    /**
     * Checks if the device has KitKat or higher version.
     * @return <code>true</code> if device is a tablet.
     */
    public static boolean hasKitKat() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    /**
     * Checks if the device has network provider enabled.
     * @param context
     * @return <code>true</code> if device has network provider
     */
    public static boolean hasNetworkProviderEnabled(Context context) {
    	return hasProviderEnabled(context, LocationManager.NETWORK_PROVIDER);
    }

    /**
     * Checks if the device has gps provider enabled.
     * @param context
     * @return <code>true</code> if device has gps provider
     */
    public static boolean hasGPSProviderEnabled(Context context) {
    	return hasProviderEnabled(context, LocationManager.GPS_PROVIDER);
    }

    private static boolean hasProviderEnabled(Context context, String provider) {
    	boolean providerEnabled = false;
    	try {
	    	LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
	    	if (lm != null) {
	    		providerEnabled = lm.isProviderEnabled(provider);
	    	}
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}
    	return providerEnabled;
    }

    /**
     * This method searches the device an installed application
     * @param context
     * @param intent
     * @param appPackage application package to search
     * @return <code>true</code> if application package is found.
     */
	public static boolean findTargetAppPackage(Context context, Intent intent, String appPackage) {
		PackageManager pm = context.getPackageManager();
		List<ResolveInfo> availableApps = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		if (availableApps != null) {
			if (contains(availableApps, appPackage)) {
				return true;
			}
		}
		return false;
	}
    
	private static boolean contains(Iterable<ResolveInfo> availableApps, String targetApp) {
		for (ResolveInfo availableApp : availableApps) {
			String packageName = availableApp.activityInfo.packageName;
			if (targetApp.equals(packageName)) {
				return true;
			}
		}
		return false;
	}	 

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     * @param context
     * @return <code>true</code> if device contains Google Play Services.
     */
	public static boolean checkPlayServices(Context context) {

        final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

        if (context == null || !((context instanceof FragmentActivity) || (context instanceof Activity))) {
            throw new IllegalStateException("Context must be FragmentActivity instance.");
        }
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                if (context instanceof FragmentActivity) {
                    GooglePlayServicesUtil.getErrorDialog(resultCode, (FragmentActivity) context, PLAY_SERVICES_RESOLUTION_REQUEST).show();
                } else if (context instanceof Activity) {
                    GooglePlayServicesUtil.getErrorDialog(resultCode, (Activity) context, PLAY_SERVICES_RESOLUTION_REQUEST).show();
                }
            } else {
                Log.i(TAG, "This device is not supported.");
                if (context instanceof FragmentActivity) {
                    ((FragmentActivity) context).finish();
                } else if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            }
            return false;
        }
        return true;
    }

    /**
     * Get application name
     * @param context
     * @return application name
     */
    public static String getApplicationName(Context context) {
        return context.getString(context.getApplicationInfo().labelRes);
    }

    /**
     * Get application package
     * @param context
     * @return package name
     */
	public static String getPackageName(Context context) {
		String packageName = "";
		try {
			PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			packageName = pInfo.packageName;
		} catch (NameNotFoundException e) {
			Log.e(TAG, e.getMessage(), e);
		}
		return packageName;
	}

    /**
     * Get version name from application
     * @param context
     * @return version name from application
     */
	public static String getVersionName(Context context) {
		String versionName = "";
		try {
			PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			versionName = pInfo.versionName;
		} catch (NameNotFoundException e) {
			Log.e(TAG, e.getMessage(), e);
		}
		return versionName;
	}

    /**
     * Get version code from application
     * @param context
     * @return version code from application
     */
	public static int getVersionCode(Context context) {
		int versionCode = 0;
		try {
			PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			versionCode = pInfo.versionCode;
		} catch (NameNotFoundException e) {
			Log.e(TAG, e.getMessage(), e);
		}
		return versionCode;
	}

}
