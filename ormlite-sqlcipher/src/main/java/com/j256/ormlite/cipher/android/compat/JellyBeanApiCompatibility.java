package com.j256.ormlite.cipher.android.compat;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.CancellationSignal;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

/**
 * Basic class which provides no-op methods for all Android version.
 * 
 * <p>
 * <b>NOTE:</b> Will show as in error if compiled with previous Android versions.
 * </p>
 * 
 * @author graywatson
 */
public class JellyBeanApiCompatibility extends BasicApiCompatibility {

	@Override
	public Cursor rawQuery(SQLiteDatabase db, String sql, String[] selectionArgs, CancellationHook cancellationHook) {
		if (cancellationHook != null) {
			throw new RuntimeException("Not supported by ");
		}
		return db.rawQuery(sql, selectionArgs);
	}

	@Override
	public CancellationHook createCancellationHook() {
		return new JellyBeanCancellationHook();
	}

	/**
	 * Hook object that supports canceling a running query. 
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	protected static class JellyBeanCancellationHook implements CancellationHook {

		private final CancellationSignal cancellationSignal;

		public JellyBeanCancellationHook() {
			this.cancellationSignal = new CancellationSignal();
		}

		@Override
		public void cancel() {
			cancellationSignal.cancel();
		}
	}
}
