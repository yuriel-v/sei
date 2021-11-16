package io.sei.db.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class User
{
    private String name;
    private String email;
    private String registry;      // THIS is the PK!
    private String passwordHash;  // NOT the PK, SHA-512 hash
    private final List<Enrollment> enrolledSubjects;

    private User() {
        this.enrolledSubjects = new ArrayList<Enrollment>();
    }

    public User(
        String name, String email, String registry, String passwordHash,
        Enrollment... registries
    ) {
        this();
        this.name = name;
        this.email = email;
        this.registry = registry;
        this.passwordHash = passwordHash;

        if (registries.length > 0)
            this.enrolledSubjects.addAll(Arrays.asList(registries));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<Enrollment> getEnrolledSubjects() 
    {
        Predicate<Enrollment> active = x -> !x.isLocked();
        List<Enrollment> activeEnrolls = this.enrolledSubjects.stream()
                                                              .filter(active)
                                                              .collect(Collectors.<Enrollment>toList());
        return activeEnrolls;
    }

    public List<Enrollment> getAllEnrolledSubjects() {
        return enrolledSubjects;
    }
}
