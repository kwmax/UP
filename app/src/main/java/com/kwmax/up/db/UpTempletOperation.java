package com.kwmax.up.db;

import android.content.Context;

import com.kwmax.up.model.UpTemplet;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by kwmax on 2019/10/8.
 */
public class UpTempletOperation {
    /**
     * 添加数据至数据库
     *
     * @param context
     * @param UpTemplet
     */
    public static void insertData(Context context, UpTemplet UpTemplet) {
        DBManager.getDaoSession(context).getUpTempletDao().insert(UpTemplet);
    }

    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<UpTemplet> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DBManager.getDaoSession(context).getUpTempletDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param UpTemplet
     */
    public static void saveData(Context context, UpTemplet UpTemplet) {
        DBManager.getDaoSession(context).getUpTempletDao().save(UpTemplet);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param UpTemplet    删除具体内容
     */
    public static void deleteData(Context context, UpTemplet UpTemplet) {
        DBManager.getDaoSession(context).getUpTempletDao().delete(UpTemplet);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        DBManager.getDaoSession(context).getUpTempletDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DBManager.getDaoSession(context).getUpTempletDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param UpTemplet
     */
    public static void updateData(Context context, UpTemplet UpTemplet) {
        DBManager.getDaoSession(context).getUpTempletDao().update(UpTemplet);
    }

    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<UpTemplet> queryAll(Context context) {
        QueryBuilder<UpTemplet> builder = DBManager.getDaoSession(context).getUpTempletDao().queryBuilder();
        return builder.build().list();
    }

    /**
     * 带查询条件查询
     *
     * @param context
     * @return
     */
    public static List<UpTemplet> queryWithCondition(Context context, WhereCondition condition) {
        QueryBuilder<UpTemplet> builder = DBManager.getDaoSession(context).getUpTempletDao().queryBuilder().where(condition);
        return builder.build().list();
    }
}
