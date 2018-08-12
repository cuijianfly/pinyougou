package com.pinyougou;

import com.pinyougou.mapper.TbAreasMapper;
import com.pinyougou.pojo.TbAreas;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.List;

public class MapperTest {


/*    @Test
    public void testMapper() throws Exception{

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(Resources.getResourceAsStream("mybatis/SqlMapConfig.xml"));
        SqlSession sqlSession = sessionFactory.openSession();
        TbAreasMapper mapper = sqlSession.getMapper(TbAreasMapper.class);
        TbAreas tbArea = mapper.selectByPrimaryKey(3);
            System.out.println(tbArea);
    }*/
}
