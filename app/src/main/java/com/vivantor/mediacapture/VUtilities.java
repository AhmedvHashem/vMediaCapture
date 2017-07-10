//package com.AhmedNTS;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.widget.ImageView;
//
//import com.AhmedNTS.AsyncCall.AsyncCall;
//import com.AhmedNTS.AsyncCall.AsyncCallback;
//
////import org.joda.time.DateTime;
//
//import java.io.InputStream;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.TimeZone;
//
//
///**
// * Created by AhmedNTS on 2015-08-22.
// */
//public class Utilities
//{
//    public static String getDateNew(String value)
//    {
////        DateTime dt = new DateTime(value);
//
//        long date = System.currentTimeMillis(); //current android time in epoch
//        //Converts epoch to "dd/MM/yyyy HH:mm:ss" dateformat
//        String NormalDate = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(date));
//
//        return null;
//    }
//
//    public static String getDate(String jsonDate)
//    {
//        String finalDate = "";
//        try
//        {
//            String dateTicks = jsonDate.replace("/Date(", "");
//            dateTicks = dateTicks.replace(")/", "");
//
//            String[] data = new String[2];
//            if (dateTicks.contains("-"))
//            {
//                data = dateTicks.split("\\-");
//            }
//            else
//            {
//                data = dateTicks.split("\\+");
//            }
//            String utcString = data[0];
////            String offsetString = data[1];
////            int offsetHours = Integer.parseInt(offsetString);
////            offsetHours = offsetHours / 100;
////            int offsetMinutes = offsetHours % 100;
//            Long utcTicks = Long.parseLong(utcString);
//
//            Date utcDate = new Date(utcTicks);
//
////            int offSetTime = TimeZone.getDefault().getOffset(utcDate.getTime());
////            int manualOffSetTime = (3600 * offsetHours * 1000);// + (60 * offsetMinutes);
//
//            Date localDate = new Date((utcDate.getTime()));// + offSetTime));
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");// hh:mm:ss
//            finalDate = sdf.format(localDate);
////
////
////
////            Calendar cal = Calendar.getInstance();
////            cal.setTimeZone(TimeZone.getTimeZone("UTC"));
////            cal.setTimeInMillis(utcTicks);
////            Long ticks = cal.getTimeInMillis();
////            cal.add(Calendar.HOUR, 2);
////            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
////            finalDate = sdf2.format(cal.getTime());
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        return finalDate;
//
//
//        /*String finalDate = "";
//        try
//        {
//            String dateTicks = jsonDate.replace("/Date(", "");
//            dateTicks = dateTicks.replace(")/", "");
//            String[] data = dateTicks.split("\\+");
//
//            String utcString = data[0];
//            String offsetString = data[1];
//            int offsetHours = Integer.parseInt(offsetString);
//            offsetHours = offsetHours / 100;
////            int offsetMinutes = offsetHours % 100;
//            Long utcTicks = Long.parseLong(utcString);
//
//            Date utcDate = new Date(utcTicks);
//
//            int offSetTime = TimeZone.getDefault().getOffset(utcDate.getTime());
//            int manualOffSetTime = (3600 * offsetHours * 1000);// + (60 * offsetMinutes);
//
//            Date localDate = new Date((utcDate.getTime() + offSetTime));
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//            finalDate = sdf.format(localDate);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        return finalDate;*/
//    }
//
//    public static String getTime(String jsonTime)
//    {
//        String hh = "", mm = "";
//
//        String initDate = jsonTime.replace("PT", "");
//        if (initDate.toLowerCase().contains(("H").toLowerCase()))
//        {
//            String[] initDate2 = initDate.split("H");
//            hh = initDate2[0];
//            if (initDate2[1].toLowerCase().contains(("M").toLowerCase()))
//                mm = initDate2[1].replace(("M").toLowerCase(), "");
//        }
//        else
//        {
//            hh = "00";
//
//            if (initDate.toLowerCase().contains(("M").toLowerCase()))
//            {
////                String[] initDate2 = initDate.split("M");
////                mm = initDate2[0];
//
//                mm = initDate.replace(("M").toLowerCase(), "");
//            }
//            else
//            {
//                mm = "00";
//            }
//        }
//
//        return hh + ":" + mm;
//    }
//
//
//    //        Calendar cal = Calendar.getInstance();
////        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
////        cal.setTimeInMillis(Long.parseLong(utc));
//////        cal.add(Calendar.HOUR, offsetInt);
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd mm:hh:ss");
////        String dob = sdf.format(cal.getTime());
//
//
//    //    public static Date convertTimeZone(Date date, TimeZone fromTimeZone, TimeZone toTimeZone)
////    {
////        long fromTimeZoneOffset = getTimeZoneUTCAndDSTOffset(date, fromTimeZone);
////        long toTimeZoneOffset = getTimeZoneUTCAndDSTOffset(date, toTimeZone);
////
////        return new Date(date.getTime() + (toTimeZoneOffset - fromTimeZoneOffset));
////    }
////
////    private static long getTimeZoneUTCAndDSTOffset(Date date, TimeZone timeZone)
////    {
////        long timeZoneDSTOffset = 0;
////        if(timeZone.inDaylightTime(date))
////        {
////            timeZoneDSTOffset = timeZone.getDSTSavings();
////        }
////
////        return timeZone.getRawOffset() + timeZoneDSTOffset;
////    }
//
//
////    public static String getDate(long ticks)//if you get UTC format only
////    {
////        Date localTime = new Date(ticks);
////        Date fromGmt = new Date(localTime.getTime() + TimeZone.getDefault().getOffset(localTime.getTime()));
////        String format = "yyyy/MM/dd";
////        SimpleDateFormat sdf = new SimpleDateFormat(format);
////        return sdf.format(fromGmt);
////    }
//
//
//    public static void GetImage(final String imageFolder, final String imageName, final ImageView imageView)
//    {
//        AsyncCall customerImage = new AsyncCall(null);
//        customerImage.callback = new AsyncCallback()
//        {
//            Bitmap bitmap;
//
//            @Override
//            public void onStart()
//            {
//
//            }
//
//            @Override
//            public Boolean onProgress()
//            {
//                try
//                {
//                    String url = "http://192.30.163.67/eko_wcf/" + imageFolder + "/" + imageName + ".png";
//                    bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
//
//                    if (bitmap == null)
//                        return false;
//
//                    return true;
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//                return false;
//            }
//
//            @Override
//            public void onFinish(Boolean isSuccess)
//            {
//                if (isSuccess)
//                {
//                    imageView.setImageBitmap(bitmap);
//                }
//                else
//                {
////                    Toast.makeText(getActivity(), "Set you photo in Profile", Toast.LENGTH_SHORT).show();
//                }
//            }
//        };
//        customerImage.execute((Void) null);
//    }
//
//    public static boolean isOnline(Activity context)
//    {
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivityManager != null)
//        {
//            NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
//            for (NetworkInfo ni : networkInfos)
//            {
//                if (ni.getTypeName().equalsIgnoreCase("WIFI"))
//                    if (ni.isConnected())
//                        return true;
//                if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
//                    if (ni.isConnected())
//                        return true;
//            }
//        }
//        return false;
//    }
//}

package com.vivantor.mediacapture;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by AhmedNTS on 2015-08-22.
 */
public class VUtilities
{
	/**
	 * Creates a media file in the {@code Environment.DIRECTORY_PICTURES} directory. The directory
	 * is persistent and available to other applications like gallery.
	 *
	 * @param type Media type. Can be video or image.
	 * @return A file object pointing to the newly created file.
	 */
	public static File getOutputMediaFile(int type)
	{
		// To be safe, you should check that the SDCard is mounted
		// using Environment.getExternalStorageState() before doing this.
		if (!Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED))
		{
			return null;
		}

		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "TempMediaFolder");
		// This location works best if you want the created images to be shared
		// between applications and persist after your app has been uninstalled.

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists())
		{
			if (!mediaStorageDir.mkdirs())
			{
				Log.d("CameraSample", "failed to create directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File mediaFile;
		if (type == 1)
		{
			mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
		}
		else if (type == 2)
		{
			mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp + ".mp4");
		}
		else if (type == 3)
		{
			mediaFile = new File(mediaStorageDir.getPath() + File.separator + "AUD_" + timeStamp + ".3gp");
		}
		else
		{
			return null;
		}

		return mediaFile;
	}
}