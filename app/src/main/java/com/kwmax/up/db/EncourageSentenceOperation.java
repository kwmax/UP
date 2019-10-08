package com.kwmax.up.db;

import android.content.Context;

import com.kwmax.up.model.EncourageSentence;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by kwmax on 2019/10/8.
 */
public class EncourageSentenceOperation {
    /**
     * 添加数据至数据库
     *
     * @param context
     * @param EncourageSentence
     */
    public static void insertData(Context context, EncourageSentence EncourageSentence) {
        DBManager.getDaoSession(context).getEncourageSentenceDao().insert(EncourageSentence);
    }

    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<EncourageSentence> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DBManager.getDaoSession(context).getEncourageSentenceDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param EncourageSentence
     */
    public static void saveData(Context context, EncourageSentence EncourageSentence) {
        DBManager.getDaoSession(context).getEncourageSentenceDao().save(EncourageSentence);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param EncourageSentence    删除具体内容
     */
    public static void deleteData(Context context, EncourageSentence EncourageSentence) {
        DBManager.getDaoSession(context).getEncourageSentenceDao().delete(EncourageSentence);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        DBManager.getDaoSession(context).getEncourageSentenceDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DBManager.getDaoSession(context).getEncourageSentenceDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param EncourageSentence
     */
    public static void updateData(Context context, EncourageSentence EncourageSentence) {
        DBManager.getDaoSession(context).getEncourageSentenceDao().update(EncourageSentence);
    }

    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<EncourageSentence> queryAll(Context context) {
        QueryBuilder<EncourageSentence> builder = DBManager.getDaoSession(context).getEncourageSentenceDao().queryBuilder();
        return builder.build().list();
    }

    /**
     * 带查询条件查询
     *
     * @param context
     * @return
     */
    public static List<EncourageSentence> queryWithCondition(Context context, WhereCondition condition) {
        QueryBuilder<EncourageSentence> builder = DBManager.getDaoSession(context).getEncourageSentenceDao().queryBuilder().where(condition);
        return builder.build().list();
    }
}
