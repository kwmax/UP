package com.kwmax.up.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.kwmax.up.model.UpTemplet;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "UP_TEMPLET".
*/
public class UpTempletDao extends AbstractDao<UpTemplet, Long> {

    public static final String TABLENAME = "UP_TEMPLET";

    /**
     * Properties of entity UpTemplet.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Userid = new Property(1, String.class, "userid", false, "USERID");
        public final static Property Typenum = new Property(2, String.class, "typenum", false, "TYPENUM");
        public final static Property Star = new Property(3, String.class, "star", false, "STAR");
        public final static Property Date = new Property(4, String.class, "date", false, "DATE");
        public final static Property Time = new Property(5, String.class, "time", false, "TIME");
        public final static Property Name = new Property(6, String.class, "name", false, "NAME");
        public final static Property Content = new Property(7, String.class, "content", false, "CONTENT");
        public final static Property LastEdittime = new Property(8, String.class, "lastEdittime", false, "LAST_EDITTIME");
        public final static Property Done = new Property(9, String.class, "done", false, "DONE");
    }


    public UpTempletDao(DaoConfig config) {
        super(config);
    }
    
    public UpTempletDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"UP_TEMPLET\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"USERID\" TEXT," + // 1: userid
                "\"TYPENUM\" TEXT," + // 2: typenum
                "\"STAR\" TEXT," + // 3: star
                "\"DATE\" TEXT," + // 4: date
                "\"TIME\" TEXT," + // 5: time
                "\"NAME\" TEXT," + // 6: name
                "\"CONTENT\" TEXT," + // 7: content
                "\"LAST_EDITTIME\" TEXT," + // 8: lastEdittime
                "\"DONE\" TEXT);"); // 9: done
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"UP_TEMPLET\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UpTemplet entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userid = entity.getUserid();
        if (userid != null) {
            stmt.bindString(2, userid);
        }
 
        String typenum = entity.getTypenum();
        if (typenum != null) {
            stmt.bindString(3, typenum);
        }
 
        String star = entity.getStar();
        if (star != null) {
            stmt.bindString(4, star);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(5, date);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(6, time);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(7, name);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(8, content);
        }
 
        String lastEdittime = entity.getLastEdittime();
        if (lastEdittime != null) {
            stmt.bindString(9, lastEdittime);
        }
 
        String done = entity.getDone();
        if (done != null) {
            stmt.bindString(10, done);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UpTemplet entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userid = entity.getUserid();
        if (userid != null) {
            stmt.bindString(2, userid);
        }
 
        String typenum = entity.getTypenum();
        if (typenum != null) {
            stmt.bindString(3, typenum);
        }
 
        String star = entity.getStar();
        if (star != null) {
            stmt.bindString(4, star);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(5, date);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(6, time);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(7, name);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(8, content);
        }
 
        String lastEdittime = entity.getLastEdittime();
        if (lastEdittime != null) {
            stmt.bindString(9, lastEdittime);
        }
 
        String done = entity.getDone();
        if (done != null) {
            stmt.bindString(10, done);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UpTemplet readEntity(Cursor cursor, int offset) {
        UpTemplet entity = new UpTemplet( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // typenum
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // star
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // date
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // time
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // name
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // content
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // lastEdittime
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // done
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UpTemplet entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTypenum(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setStar(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDate(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTime(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setContent(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setLastEdittime(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setDone(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UpTemplet entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UpTemplet entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UpTemplet entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}