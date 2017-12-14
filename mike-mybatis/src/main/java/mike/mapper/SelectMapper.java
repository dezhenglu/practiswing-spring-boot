package mike.mapper;

import mike.model.Event;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SelectMapper {

    @Select("SELECT * FROM event")
    List<Event> findAll();
}
