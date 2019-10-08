package com.kwmax.up.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.kwmax.up.dao.DaoMaster;
import com.kwmax.up.dao.DaoSession;

/**
 * Created by kwmax on 2019/10/8.
 */
public class DBManager {

    // 是否加密
    public static final boolean ENCRYPTED = true;

    private static final String DB_NAME = "kwmax_up.db";
    private static DaoMaster.DevOpenHelper mDevOpenHelper;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    private static DBManager mDbManager;

    private Context mContext;

    private DBManager(Context context) {
        this.mContext = context;
        // 初始化数据库信息
        mDevOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        getDaoMaster(context);
        getDaoSession(context);
    }

    public static DBManager getInstance(Context context) {
        if (null == mDbManager) {
            synchronized (DBManager.class) {
                if (null == mDbManager) {
                    mDbManager = new DBManager(context);
                }
            }
        }
        return mDbManager;
    }

    /**
     * 获取可读数据库
     *
     * @param context
     * @return
     */
    public static SQLiteDatabase getReadableDatabase(Context context) {
        if (null == mDevOpenHelper) {
            getInstance(context);
        }
        return mDevOpenHelper.getReadableDatabase();
    }

    /**
     * 获取可写数据库
     *
     * @param context
     * @return
     */
    public static SQLiteDatabase getWritableDatabase(Context context) {
        if (null == mDevOpenHelper) {
            getInstance(context);
        }

        return mDevOpenHelper.getWritableDatabase();
    }

    /**
     * 获取DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (null == mDaoMaster) {
            synchronized (DBManager.class) {
                if (null == mDaoMaster) {
                    MyOpenHelper helper = new MyOpenHelper(context,DB_NAME,null);
                    mDaoMaster = new DaoMaster(helper.getWritableDatabase());
                }
            }
        }
        return mDaoMaster;
    }

    /**
     * 获取DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        if (null == mDaoSession) {
            synchronized (DBManager.class) {
                mDaoSession = getDaoMaster(context).newSession();
            }
        }
        return mDaoSession;
    }
}
