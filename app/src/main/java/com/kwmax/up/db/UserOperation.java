package com.kwmax.up.db;

import android.content.Context;
import com.kwmax.up.model.User;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by kwmax on 2019/10/8.
 */
public class UserOperation {
    /**
     * 添加数据至数据库
     *
     * @param context
     * @param User
     */
    public static void insertData(Context context, User User) {
        DBManager.getDaoSession(context).getUserDao().insert(User);
    }

    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<User> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DBManager.getDaoSession(context).getUserDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param User
     */
    public static void saveData(Context context, User User) {
        DBManager.getDaoSession(context).getUserDao().save(User);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param User    删除具体内容
     */
    public static void deleteData(Context context, User User) {
        DBManager.getDaoSession(context).getUserDao().delete(User);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        DBManager.getDaoSession(context).getUserDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DBManager.getDaoSession(context).getUserDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param User
     */
    public static void updateData(Context context, User User) {
        DBManager.getDaoSession(context).getUserDao().update(User);
    }

    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<User> queryAll(Context context) {
        QueryBuilder<User> builder = DBManager.getDaoSession(context).getUserDao().queryBuilder();
        return builder.build().list();
    }

    /**
     * 带查询条件查询
     *
     * @param context
     * @return
     */
    public static List<User> queryWithCondition(Context context, WhereCondition condition) {
        QueryBuilder<User> builder = DBManager.getDaoSession(context).getUserDao().queryBuilder().where(condition);
        return builder.build().list();
    }
}
