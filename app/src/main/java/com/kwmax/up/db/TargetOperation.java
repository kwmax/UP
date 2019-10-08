package com.kwmax.up.db;

import android.content.Context;

import com.kwmax.up.model.Target;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by kwmax on 2019/10/8.
 */
public class TargetOperation {
    /**
     * 添加数据至数据库
     *
     * @param context
     * @param Target
     */
    public static void insertData(Context context, Target Target) {
        DBManager.getDaoSession(context).getTargetDao().insert(Target);
    }

    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<Target> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DBManager.getDaoSession(context).getTargetDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param Target
     */
    public static void saveData(Context context, Target Target) {
        DBManager.getDaoSession(context).getTargetDao().save(Target);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param Target    删除具体内容
     */
    public static void deleteData(Context context, Target Target) {
        DBManager.getDaoSession(context).getTargetDao().delete(Target);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        DBManager.getDaoSession(context).getTargetDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DBManager.getDaoSession(context).getTargetDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param Target
     */
    public static void updateData(Context context, Target Target) {
        DBManager.getDaoSession(context).getTargetDao().update(Target);
    }

    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<Target> queryAll(Context context) {
        QueryBuilder<Target> builder = DBManager.getDaoSession(context).getTargetDao().queryBuilder();
        return builder.build().list();
    }

    /**
     * 带查询条件查询
     *
     * @param context
     * @return
     */
    public static List<Target> queryWithCondition(Context context, WhereCondition condition) {
        QueryBuilder<Target> builder = DBManager.getDaoSession(context).getTargetDao().queryBuilder().where(condition);
        return builder.build().list();
    }
}
