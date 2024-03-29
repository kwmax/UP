package com.kwmax.up.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.kwmax.up.model.ScheduleTodo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SCHEDULE_TODO".
*/
public class ScheduleTodoDao extends AbstractDao<ScheduleTodo, Long> {

    public static final String TABLENAME = "SCHEDULE_TODO";

    /**
     * Properties of entity ScheduleTodo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Userid = new Property(1, String.class, "userid", false, "USERID");
        public final static Property Star = new Property(2, String.class, "star", false, "STAR");
        public final static Property Date = new Property(3, String.class, "date", false, "DATE");
        public final static Property Time = new Property(4, String.class, "time", false, "TIME");
        public final static Property Name = new Property(5, String.class, "name", false, "NAME");
        public final static Property Content = new Property(6, String.class, "content", false, "CONTENT");
        public final static Property LastEdittime = new Property(7, String.class, "lastEdittime", false, "LAST_EDITTIME");
        public final static Property Done = new Property(8, String.class, "done", false, "DONE");
    }


    public ScheduleTodoDao(DaoConfig config) {
        super(config);
    }
    
    public ScheduleTodoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SCHEDULE_TODO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"USERID\" TEXT," + // 1: userid
                "\"STAR\" TEXT," + // 2: star
                "\"DATE\" TEXT," + // 3: date
                "\"TIME\" TEXT," + // 4: time
                "\"NAME\" TEXT," + // 5: name
                "\"CONTENT\" TEXT," + // 6: content
                "\"LAST_EDITTIME\" TEXT," + // 7: lastEdittime
                "\"DONE\" TEXT);"); // 8: done
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SCHEDULE_TODO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ScheduleTodo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userid = entity.getUserid();
        if (userid != null) {
            stmt.bindString(2, userid);
        }
 
        String star = entity.getStar();
        if (star != null) {
            stmt.bindString(3, star);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(4, date);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(5, time);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(6, name);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(7, content);
        }
 
        String lastEdittime = entity.getLastEdittime();
        if (lastEdittime != null) {
            stmt.bindString(8, lastEdittime);
        }
 
        String done = entity.getDone();
        if (done != null) {
            stmt.bindString(9, done);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ScheduleTodo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userid = entity.getUserid();
        if (userid != null) {
            stmt.bindString(2, userid);
        }
 
        String star = entity.getStar();
        if (star != null) {
            stmt.bindString(3, star);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(4, date);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(5, time);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(6, name);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(7, content);
        }
 
        String lastEdittime = entity.getLastEdittime();
        if (lastEdittime != null) {
            stmt.bindString(8, lastEdittime);
        }
 
        String done = entity.getDone();
        if (done != null) {
            stmt.bindString(9, done);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ScheduleTodo readEntity(Cursor cursor, int offset) {
        ScheduleTodo entity = new ScheduleTodo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // star
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // date
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // time
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // name
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // content
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // lastEdittime
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // done
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ScheduleTodo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setStar(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDate(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTime(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setContent(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setLastEdittime(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setDone(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ScheduleTodo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ScheduleTodo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ScheduleTodo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
