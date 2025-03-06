package org.example.section11.interfacepractice;
// 12/02/25 Section 11 Exercise 48
import java.util.List;

public interface ISaveable {
    List<String> write();
    void read(List<String> list);
}
