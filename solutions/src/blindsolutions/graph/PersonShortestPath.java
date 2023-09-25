package blindsolutions.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class PersonShortestPath {

    public static void main(String[] args) {
        Map<Person,List<Person>> connections = new HashMap();
        Person src = new Person("A", "1");
        Person personB = new Person("B", "2");
        Person personC = new Person("C", "3");
        Person personD = new Person("D", "4");
        Person personE = new Person("E", "5");
        Person dest = new Person("F", "6");
        connections.put(src, new ArrayList<Person>());
        connections.put(personB, new ArrayList<Person>());
        connections.put(personC, new ArrayList<Person>());
        connections.put(personD, new ArrayList<Person>());
        connections.put(personE, new ArrayList<Person>());
        connections.put(dest, new ArrayList<Person>());

        /*
        Find path a -> f
        a -> b, c
        b -> c
        c -> e
        e -> f
        */
        connections.get(src).add(personB);
        connections.get(personB).add(src);

        // 	connections.get(src).add(personC);
        // 	connections.get(personC).add(src);

        connections.get(personB).add(personC);
        connections.get(personC).add(personB);

        connections.get(personC).add(personE);
        connections.get(personE).add(personC);

        connections.get(personE).add(dest);
        connections.get(dest).add(personE);


        List<Person> shortestPath=findShortestPath(connections, src, dest);
        for (Person person: shortestPath) {
            System.out.print(person.name + "->");
        }
    }
    static class Person {
        String name;
        String id;

        public Person() {
        }

        public Person(String name, String id) {
            this.name = name;
            this.id = id;
        }
    }

    // Creating class for storing a path, a path is basically list of people travelled on that path
    static class Path {
        List<Person> list = new ArrayList<Person>();

        public Path() {
        }

        public Path(Person person) {
            list.add(person);
        }

        public Person getLast() {
            int size = list.size();
            return (size > 0) ? list.get(size - 1) : new Person();
        }
    }

    static List<Person> findShortestPath(Map<Person, List<Person>> connections, Person src, Person dest) {
        // Priority queue of Paths, where we sort them based on the size of the list to get the shortest one when polled
        PriorityQueue<Path> queue = new PriorityQueue<Path>((a, b) -> a.list.size() - b.list.size());
        queue.add(new Path(src));

        while (!queue.isEmpty()) {
            Path path = queue.poll();
            Person person = path.getLast();

            if (person.equals(dest)) {
                return path.list;
            }

            for (Person neighbour : connections.getOrDefault(person, new ArrayList<Person>())) {
                Path nextPath = new Path();
                nextPath.list = new ArrayList<Person>(path.list); // We polled this path from the queue initially, now we will add the new neighbour to it and then we will offer it to the queue again
                nextPath.list.add(neighbour);
                queue.add(nextPath);
            }
        }
        return new ArrayList<Person>();
    }
}
