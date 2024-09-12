import com.ssg.springtodoservice.domain.TodoVO;
import com.ssg.springtodoservice.dto.TodoDTO;
import com.ssg.springtodoservice.mapper.TodoMapper;
import com.ssg.springtodoservice.service.TodoServiceImpl;
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
public class TodoServiceTest {

    @Autowired(required = false)
    private TodoServiceImpl todoService;


    @Test
    public void testRegister() {
        TodoDTO dto = TodoDTO.builder().title("test2").dueDate(LocalDate.now()).writer("ssg").build();
        todoService.register(dto);
    }
}
