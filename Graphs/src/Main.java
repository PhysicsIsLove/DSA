import com.sun.source.tree.Tree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
//        List<List<Integer>> list=  new ArrayList<>();
//        list.add(List.of(3, 4));
//        list.get(0).set(0, 8);
//        System.out.println(list);

        Employee e1 = new Employee("Abhishek", 10);
        Employee e3 = new Employee("Abhishek", 10);
        Employee e2 = new Employee("Anand", 45);
        Set<Employee> set = new HashSet<>();

        set.add(e1);
        set.add(e2);

        set.remove(new Employee("Anand", 45));
        System.out.println("Set size " + set.size());

        Set<List<Integer>> listSet = new HashSet<>();
        listSet.add(List.of(2, 3));
        listSet.add(List.of(1,2));
//        listSet.remove(List.of(2,3));
        System.out.println("List set size "+ listSet.size());

        TreeSet<List<Integer>> listSet2 = new TreeSet<>(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0) - o2.get(0);
            }
        });
        listSet2.add(Arrays.asList(1,2));
        listSet2.add(Arrays.asList(1,3));
        System.out.println("List set size "+ listSet2.size());

    }
}

class Employee{
    String name;
    Integer age;

    public Employee(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return name.equals(employee.name) && age.equals(employee.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}