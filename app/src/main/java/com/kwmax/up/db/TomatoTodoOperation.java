package com.kwmax.up.db;

import android.content.Context;

import com.kwmax.up.model.TomatoTodo;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by kwmax on 2019/10/8.
 */
public class TomatoTodoOperation {
    /**
     * 添加数据至数据库
     *
     * @param context
     * @param TomatoTodo
     */
    public static void insertData(Context context, TomatoTodo TomatoTodo) {
        DBManager.getDaoSession(context).getTomatoTodoDao().insert(TomatoTodo);
    }

    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<TomatoTodo> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DBManager.getDaoSession(context).getTomatoTodoDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param TomatoTodo
     */
    public static void saveData(Context context, TomatoTodo TomatoTodo) {
        DBManager.getDaoSession(context).getTomatoTodoDao().save(TomatoTodo);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param TomatoTodo    删除具体内容
     */
    public static void deleteData(Context context, TomatoTodo TomatoTodo) {
        DBManager.getDaoSession(context).getTomatoTodoDao().delete(TomatoTodo);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        DBManager.getDaoSession(context).getTomatoTodoDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DBManager.getDaoSession(context).getTomatoTodoDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param TomatoTodo
     */
    public static void updateData(Context context, TomatoTodo TomatoTodo) {
        DBManager.getDaoSession(context).getTomatoTodoDao().update(TomatoTodo);
    }

    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<TomatoTodo> queryAll(Context context) {
        QueryBuilder<TomatoTodo> builder = DBManager.getDaoSession(context).getTomatoTodoDao().queryBuilder();
        return builder.build().list();
    }

    /**
     * 带查询条件查询
     *
     * @param context
     * @return
     */
    public static List<TomatoTodo> queryWithCondition(Context context, WhereCondition condition) {
        QueryBuilder<TomatoTodo> builder = DBManager.getDaoSession(context).getTomatoTodoDao().queryBuilder().where(condition);
        return builder.build().list();
    }
}
