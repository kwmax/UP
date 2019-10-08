package com.kwmax.up.db;

import android.content.Context;

import com.kwmax.up.model.DiaryReview;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by kwmax on 2019/10/8.
 */
public class DiaryReviewsOperation {
    /**
     * 添加数据至数据库
     *
     * @param context
     * @param DiaryReview
     */
    public static void insertData(Context context, DiaryReview DiaryReview) {
        DBManager.getDaoSession(context).getDiaryReviewDao().insert(DiaryReview);
    }

    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<DiaryReview> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DBManager.getDaoSession(context).getDiaryReviewDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param DiaryReview
     */
    public static void saveData(Context context, DiaryReview DiaryReview) {
        DBManager.getDaoSession(context).getDiaryReviewDao().save(DiaryReview);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param DiaryReview    删除具体内容
     */
    public static void deleteData(Context context, DiaryReview DiaryReview) {
        DBManager.getDaoSession(context).getDiaryReviewDao().delete(DiaryReview);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        DBManager.getDaoSession(context).getDiaryReviewDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DBManager.getDaoSession(context).getDiaryReviewDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param DiaryReview
     */
    public static void updateData(Context context, DiaryReview DiaryReview) {
        DBManager.getDaoSession(context).getDiaryReviewDao().update(DiaryReview);
    }

    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<DiaryReview> queryAll(Context context) {
        QueryBuilder<DiaryReview> builder = DBManager.getDaoSession(context).getDiaryReviewDao().queryBuilder();
        return builder.build().list();
    }

    /**
     * 带查询条件查询
     *
     * @param context
     * @return
     */
    public static List<DiaryReview> queryWithCondition(Context context, WhereCondition condition) {
        QueryBuilder<DiaryReview> builder = DBManager.getDaoSession(context).getDiaryReviewDao().queryBuilder().where(condition);
        return builder.build().list();
    }
}
