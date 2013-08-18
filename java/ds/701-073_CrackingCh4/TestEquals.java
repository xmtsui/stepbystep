import java.util.HashMap;
import java.util.Map;
public class Ques04 {
    public static void main(String[] args) {
        Employee e1 = new Employee("Dave");
        Employee e2 = new Employee("Ori");
        EmployeeId eid1 = new EmployeeId(100);
        EmployeeId eid2 = new EmployeeId(200);
        Map<EmployeeId, Employee> map = new HashMap<EmployeeId, Employee>();
        map.put(eid1, e1);
        map.put(eid2, e2);
        Employee e3 = map.get(eid1);
        System.out.println(e3.equals(e1) + " " + (e3 == e1));
    }
}
class Employee{
    String name;
    public Employee(String name){
        this.name = name;
    }
    public int hashcode(){
        return name.hashCode();
    }
    public boolean equals(Object object){
        return this.name.equals(((Employee)object).getName());
    }
    public String getName(){
        return name;
    }
}
class EmployeeId{
    int id;
    public EmployeeId(int id){
        this.id = id;
    }
    public int hashcode(){
        return id;
    }
    public boolean equals(Object object){
        return this.id == ((EmployeeId)object).getId();
    }
    public int getId(){
      return id;
  }
}

