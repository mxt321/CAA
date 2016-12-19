package net.bjyfkj.caa.util;

import android.app.ProgressDialog;
import android.content.Context;

public class VProgressDialog {

	private Context mContext = null;
	private ProgressDialog mProgressDlg = null;

	public VProgressDialog(Context context) {

		mContext = context;
	}

	public void showProgressDlg(String msg) {
		dismissProgressDlg();

		mProgressDlg = new ProgressDialog(mContext);
		mProgressDlg.setMessage(msg);
		mProgressDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDlg.setIndeterminate(false);  //setIndeterminate(true)的意思就是不明确具体进度,进度条在最大值与最小值之间来回移动,形成一个动画效果,就是仅仅告诉你,我在工作,但是无法知道工作到那个阶段,
		mProgressDlg.setCancelable(true);
		mProgressDlg.setCanceledOnTouchOutside(false);
		mProgressDlg.show();
	}

	public void dismissProgressDlg() {
		if (mProgressDlg != null) {
			mProgressDlg.dismiss();
			mProgressDlg = null;
		}
	}
	public void setMessage(String msg) {
		mProgressDlg.setMessage(msg);
	}

	public void showProgressDlg(int res) {
		dismissProgressDlg();

		mProgressDlg = new ProgressDialog(mContext);
		mProgressDlg.setMessage(mContext.getString(res));
		mProgressDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDlg.setIndeterminate(false);
		mProgressDlg.setCancelable(true);
		mProgressDlg.setCanceledOnTouchOutside(false);
		mProgressDlg.show();
	}
}
