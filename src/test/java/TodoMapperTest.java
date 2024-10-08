import com.ssg.springtodoservice.domain.TodoVO;
import com.ssg.springtodoservice.mapper.TodoMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTest {
    //TodoMapper의 getTime 메소드가 잘 실행되는 지 확인할게
    //단일 모드라서 외부에서 클래스를 가져와야함
    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testGetTime() {
        log.info(todoMapper.getTime());
    }

    @Test
    public void testInsert() {
        TodoVO todoVO = TodoVO.builder().title("test").dueDate(LocalDate.now()).writer("ssg").build();
        todoMapper.insert(todoVO);
    }

}
