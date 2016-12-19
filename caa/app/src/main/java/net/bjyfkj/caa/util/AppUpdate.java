package net.bjyfkj.caa.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by rophie on 16/6/13.
 */
public class AppUpdate {

    private Context mContext;
    private boolean isAutoInstall = false;

    public AppUpdate(Context context) {
        mContext = context;
    }

    public interface OnResult {
        /**
         * 有新版本
         */
        void onNewVersion();

        /**
         * 已是最新版本
         */
        void onLatestVersion();

        /**
         * 下载进度
         *
         * @param current 已下载大小
         * @param total   总大小
         */
        void onDownloading(long current, long total);

        /**
         * 下载完成
         */
        void onDownLoaCompleted();

        /**
         * 错误
         */
        void onError();
    }

    //电视检查更新（全自动）
    public void checkUpdateForTV(final OnResult resultListener) {
        isAutoInstall = true;
        checkUpdate(resultListener);
    }

    //手机客户端检查更新
    public void checkUpdateForAndroid(final OnResult resultListener) {
        isAutoInstall = false;
        checkUpdate(resultListener);
    }

    //apk下载存放的位置
    private String getPath() {
        String path = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/DZZS";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return path;
    }

    /***
     * 访问接口检查更新
     *
     * @param resultListener
     */
    private void checkUpdate(final OnResult resultListener) {
        String sign = MD5Util.encrypt("App" + MD5Util.encrypt("bjyfkj4006010136") + "checkUpdate");
        RequestParams params = new RequestParams(PropertiesUtils.getpath("check_update"));
        if (isAutoInstall) {
            params.addParameter("client", "tv");
        } else {
            params.addParameter("client", "android");
        }
        params.addParameter("version_code", getAppVersionCode());
        params.addParameter("sign", sign);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    if (result.getInt("status") == 1) {
                        resultListener.onNewVersion();
                        final String dl_url = result.getJSONObject("data").getString("dl_url");
                        Log.i("dl_url", dl_url + "");
                        if (isAutoInstall) {
                            downLoadApk(dl_url, resultListener);
                        } else {
                            new AlertDialog.Builder(mContext).setTitle("版本更新").setMessage("发现新版本，是否立即下载安装？")
                                    .setPositiveButton("下载并安装", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            downLoadApk(dl_url, resultListener);
                                        }
                                    }).setNegativeButton("以后再说", null).create().show();
                        }
                    } else {
                        resultListener.onLatestVersion();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                resultListener.onError();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //安装apk
    private void installApp(String appPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(
                Uri.parse("file://" + appPath),
                "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }

    //下载新版apk
    private void downLoadApk(String url, final OnResult resultListener) {
        //如果本地存在则不下载
        File file = new File(getPath() + "/" + url.substring(url.lastIndexOf("/") + 1));
        if (file.exists()) {
            resultListener.onDownLoaCompleted();
            if (isAutoInstall) {
                if (!rootInstallAPK(file.getName())) {
                    resultListener.onError();
                }
            } else {
                installApp(file.getAbsolutePath());
            }
            return;
        }
        final ProgressDialog dialog = new ProgressDialog(mContext);
        if (!isAutoInstall) {
            dialog.setTitle("版本更新");
            dialog.setMessage("正在下载新版本0%...");
            dialog.show();
        }
        RequestParams params = new RequestParams(url);
        params.setSaveFilePath(getPath() + "/" + url.substring(url.lastIndexOf("/") + 1));
        final Callback.Cancelable cancelable = x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                resultListener.onDownloading(current, total);
                if (!isAutoInstall) {
                    dialog.setMessage("正在下载新版本" + current * 100 / total + "%...");
                }
            }

            @Override
            public void onSuccess(File file) {
                resultListener.onDownLoaCompleted();
                if (isAutoInstall) {
                    if (!rootInstallAPK(file.getName())) {
                        resultListener.onError();
                    }
                } else {
                    installApp(file.getAbsolutePath());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                resultListener.onError();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        if (!isAutoInstall) {
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    cancelable.cancel();
                }
            });
        }
    }

    private void reboot() {
        rootExec("reboot");
    }


    //获取app版本号
    private int getAppVersionCode() {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            Log.i("versionCode", pi.versionCode + "");
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pi.versionCode;
    }

    //root身份静默安装
    private boolean rootInstallAPK(String filename) {
        String setFilePermissionCommand = "chmod 644 /data/app/net.bjyfkj.caa-1.apk";
        String copyFileCommand = "cat /storage/sdcard0/DZZS/" + filename + " >/data/app/net.bjyfkj.caa-1.apk";
        if (!rootExec(copyFileCommand)) {
            return false;
        }
        if (!rootExec(setFilePermissionCommand)) {
            return false;
        }
        reboot();
        return true;
    }

    private boolean rootExec(String cmd) {
        Process process = null;
        DataOutputStream out = null;
        BufferedReader input = null;
        try {
            process = Runtime.getRuntime().exec("su");
            input = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            out = new DataOutputStream(process.getOutputStream());
            out.writeBytes(cmd + "\n");
            out.flush();
            out.writeBytes("exit\n");
            out.flush();
            String line = "";
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            return process.waitFor() == 0 ? true : false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            process.destroy();
        }
    }

}
