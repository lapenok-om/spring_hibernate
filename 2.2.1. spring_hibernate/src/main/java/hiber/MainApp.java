package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      Car car1 = new Car("lada", 1);
      Car car2 = new Car("toyota", 2);
      user1.setCar(car1);
      user2.setCar(car2);
      userService.add(user1);
      userService.add(user2);
      //userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      //userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user.toString());
      }

      List<User> users1 = userService.listUsersByCar("toyota", 2);
      for (User user : users1) {
         System.out.println(user.toString());
      }

      context.close();
   }
}
