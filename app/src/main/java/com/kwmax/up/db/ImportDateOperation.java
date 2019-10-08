package com.kwmax.up.db;

import android.content.Context;

import com.kwmax.up.model.ImportDate;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by kwmax on 2019/10/8.
 */
public class ImportDateOperation {
    /**
     * 添加数据至数据库
     *
     * @param context
     * @param ImportDate
     */
    public static void insertData(Context context, ImportDate ImportDate) {
        DBManager.getDaoSession(context).getImportDateDao().insert(ImportDate);
    }

    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<ImportDate> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DBManager.getDaoSession(context).getImportDateDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param ImportDate
     */
    public static void saveData(Context context, ImportDate ImportDate) {
        DBManager.getDaoSession(context).getImportDateDao().save(ImportDate);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param ImportDate    删除具体内容
     */
    public static void deleteData(Context context, ImportDate ImportDate) {
        DBManager.getDaoSession(context).getImportDateDao().delete(ImportDate);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        DBManager.getDaoSession(context).getImportDateDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DBManager.getDaoSession(context).getImportDateDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param ImportDate
     */
    public static void updateData(Context context, ImportDate ImportDate) {
        DBManager.getDaoSession(context).getImportDateDao().update(ImportDate);
    }

    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<ImportDate> queryAll(Context context) {
        QueryBuilder<ImportDate> builder = DBManager.getDaoSession(context).getImportDateDao().queryBuilder();
        return builder.build().list();
    }

    /**
     * 带查询条件查询
     *
     * @param context
     * @return
     */
    public static List<ImportDate> queryWithCondition(Context context, WhereCondition condition) {
        QueryBuilder<ImportDate> builder = DBManager.getDaoSession(context).getImportDateDao().queryBuilder().where(condition);
        return builder.build().list();
    }
}
