import java.util.Arrays;
import java.util.Comparator;
class UserComparator<T> implements Comparator<User> {
  public int compare(User o1, User o2) {
    return o1.getAge() - o2.getAge();
  }
}

class User implements Comparable<User> {
  private String id;
  private int age;
  public User(String id, int age) {
    this.id = id;
    this.age = age;
  }
  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public int compareTo(User o) {
    return this.age - o.getAge();
  }
}

class Comparator_Test{
  public static void main(String[] args) 
  {
    User[] users = new User[] { new User("a", 30), new User("b", 20), new User("c", 40), new User("d", 23)};
    for (int i = 0; i < users.length; i++) {
      User user = users[i];
      System.out.print(user.getId() + " " + user.getAge() + " |");
    }

    System.out.println();
    // Arrays.sort(users);
    Arrays.sort(users, new UserComparator<User>());
    for (int i = 0; i < users.length; i++) {
      User user = users[i];
      System.out.print(user.getId() + " " + user.getAge() + " |");
    }
    System.out.println();
  }
}