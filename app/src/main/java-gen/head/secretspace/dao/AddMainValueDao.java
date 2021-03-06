package head.secretspace.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import head.secretspace.entity.AddMainValue;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ADD_MAIN_VALUE".
*/
public class AddMainValueDao extends AbstractDao<AddMainValue, Long> {

    public static final String TABLENAME = "ADD_MAIN_VALUE";

    /**
     * Properties of entity AddMainValue.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property SqlName = new Property(1, String.class, "sqlName", false, "SQL_NAME");
        public final static Property Value = new Property(2, Integer.class, "value", false, "VALUE");
        public final static Property Time = new Property(3, String.class, "time", false, "TIME");
        public final static Property Like = new Property(4, Integer.class, "like", false, "LIKE");
    }


    public AddMainValueDao(DaoConfig config) {
        super(config);
    }
    
    public AddMainValueDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ADD_MAIN_VALUE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"SQL_NAME\" TEXT," + // 1: sqlName
                "\"VALUE\" INTEGER," + // 2: value
                "\"TIME\" TEXT," + // 3: time
                "\"LIKE\" INTEGER);"); // 4: like
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ADD_MAIN_VALUE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, AddMainValue entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String sqlName = entity.getSqlName();
        if (sqlName != null) {
            stmt.bindString(2, sqlName);
        }
 
        Integer value = entity.getValue();
        if (value != null) {
            stmt.bindLong(3, value);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(4, time);
        }
 
        Integer like = entity.getLike();
        if (like != null) {
            stmt.bindLong(5, like);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, AddMainValue entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String sqlName = entity.getSqlName();
        if (sqlName != null) {
            stmt.bindString(2, sqlName);
        }
 
        Integer value = entity.getValue();
        if (value != null) {
            stmt.bindLong(3, value);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(4, time);
        }
 
        Integer like = entity.getLike();
        if (like != null) {
            stmt.bindLong(5, like);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public AddMainValue readEntity(Cursor cursor, int offset) {
        AddMainValue entity = new AddMainValue( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // sqlName
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // value
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // time
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4) // like
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, AddMainValue entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSqlName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setValue(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setTime(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setLike(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(AddMainValue entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(AddMainValue entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(AddMainValue entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
