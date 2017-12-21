package mike.mapper;

import mike.config.HogeMapper;
import mike.model.Event;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@HogeMapper
@Mapper
public interface SelectMapper {

    @Select("SELECT * FROM event")
    List<Event> findAll();
}
