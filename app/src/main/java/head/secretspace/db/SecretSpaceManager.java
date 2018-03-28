package head.secretspace.db;

import android.content.Context;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import head.secretspace.dao.SecretspaceDao;
import head.secretspace.entity.Secretspace;

/**
 * Created by HEAD on 2017/6/16.
 */

public class SecretSpaceManager extends BaseDao<Secretspace> {

    public SecretSpaceManager(Context context) {
        super(context);
    }

    /***************************数据库查询*************************/

    /**
     * 通过ID查询对象
     * @param id
     * @return
     */
    private Secretspace loadById(long id){

        return daoSession.getSecretspaceDao().load(id);
    }

    /**
     * 获取某个对象的主键ID
     * @param student
     * @return
     */
    private long getID(Secretspace student){

        return daoSession.getSecretspaceDao().getKey(student);
    }

    /**
     * 通过名字获取Customer对象
     * @return
     */
    private List<Secretspace> getStudentByName(String key){
        QueryBuilder queryBuilder =  daoSession.getSecretspaceDao().queryBuilder();
        queryBuilder.where(SecretspaceDao.Properties.Name.eq(key));
        int size = queryBuilder.list().size();
        if (size > 0){
            return queryBuilder.list();
        }else{
            return null;
        }
    }

    /**
     * 通过名字获取Customer对象
     * @return
     */
    private List<Long> getIdByName(String key){
        List<Secretspace> students = getStudentByName(key);
        List<Long> ids = new ArrayList<Long>();
        int size = students.size();
        if (size > 0){
            for (int i = 0;i < size;i++){
                ids.add(students.get(i).getId());
            }
            return ids;
        }else{
            return null;
        }
    }

    /***************************数据库删除*************************/

    /**
     * 根据ID进行数据库的删除操作
     * @param id
     */
    private void deleteById(long id){

        daoSession.getSecretspaceDao().deleteByKey(id);
    }


    /**
     * 根据ID同步删除数据库操作
     * @param ids
     */
    private void deleteByIds(List<Long> ids){

        daoSession.getSecretspaceDao().deleteByKeyInTx(ids);
    }

    /***********************************
     * 在次添加一些Student特有的数据库操作语句
     * ************************************/
}
