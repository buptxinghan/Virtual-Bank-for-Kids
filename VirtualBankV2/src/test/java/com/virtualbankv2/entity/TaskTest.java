package com.virtualbankv2.entity;
import com.virtualbankv2.entity.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void testTaskConstructor() {
        Task task = new Task("Do homework", 10.0, "Homework", "2024-05-10", "2024-05-15", "JohnDoe");
        assertNotNull(task);
        assertEquals("Do homework", task.getDescription());
        assertEquals(10.0, task.getReward());
        assertFalse(task.isCompleted());
        assertEquals("Homework", task.getTitle());
        assertEquals("2024-05-10", task.getStart());
        assertEquals("2024-05-15", task.getEnd());
        assertEquals("JohnDoe", task.getUserName());
    }

    @Test
    void testTaskGettersAndSetters() {
        Task task = new Task("Do homework", 10.0, "Homework", "2024-05-10", "2024-05-15", "JohnDoe");
        assertNotNull(task);

        task.setDescription("Study for the exam");
        assertEquals("Study for the exam", task.getDescription());

        task.setReward(20.0);
        assertEquals(20.0, task.getReward());

        task.setCompleted(true);
        assertTrue(task.isCompleted());

        task.setTitle("Exam Preparation");
        assertEquals("Exam Preparation", task.getTitle());

        task.setStart("2024-05-20");
        assertEquals("2024-05-20", task.getStart());

        task.setEnd("2024-05-25");
        assertEquals("2024-05-25", task.getEnd());

        task.setUserName("JaneDoe");
        assertEquals("JaneDoe", task.getUserName());
    }
}
