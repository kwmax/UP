package com.kwmax.up.db;

import android.content.Context;

import com.kwmax.up.model.ScheduleTodo;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by kwmax on 2019/10/8.
 */
public class ScheduleTodoOperation {
    /**
     * 添加数据至数据库
     *
     * @param context
     * @param ScheduleTodo
     */
    public static void insertData(Context context, ScheduleTodo ScheduleTodo) {
        DBManager.getDaoSession(context).getScheduleTodoDao().insert(ScheduleTodo);
    }

    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<ScheduleTodo> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DBManager.getDaoSession(context).getScheduleTodoDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param ScheduleTodo
     */
    public static void saveData(Context context, ScheduleTodo ScheduleTodo) {
        DBManager.getDaoSession(context).getScheduleTodoDao().save(ScheduleTodo);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param ScheduleTodo    删除具体内容
     */
    public static void deleteData(Context context, ScheduleTodo ScheduleTodo) {
        DBManager.getDaoSession(context).getScheduleTodoDao().delete(ScheduleTodo);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        DBManager.getDaoSession(context).getScheduleTodoDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DBManager.getDaoSession(context).getScheduleTodoDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param ScheduleTodo
     */
    public static void updateData(Context context, ScheduleTodo ScheduleTodo) {
        DBManager.getDaoSession(context).getScheduleTodoDao().update(ScheduleTodo);
    }

    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<ScheduleTodo> queryAll(Context context) {
        QueryBuilder<ScheduleTodo> builder = DBManager.getDaoSession(context).getScheduleTodoDao().queryBuilder();
        return builder.build().list();
    }

    /**
     * 带查询条件查询
     *
     * @param context
     * @return
     */
    public static List<ScheduleTodo> queryWithCondition(Context context, WhereCondition condition) {
        QueryBuilder<ScheduleTodo> builder = DBManager.getDaoSession(context).getScheduleTodoDao().queryBuilder().where(condition);
        return builder.build().list();
    }
}
