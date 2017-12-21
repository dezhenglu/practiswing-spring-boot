package mike.mapper;

import mike.config.FugaMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@FugaMapper
@Mapper
public interface InsertMapper {

    @Insert("insert into event(name) values(#{name})")
    void insert(@Param("name") String name);
}
