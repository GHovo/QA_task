package org.testing.sources.entity;

import java.util.*;

public class User {
    private static final List<String> testUserNames = Arrays.asList("Name1","Name2","Name3");
    private static final List<String> testUserGenders = Arrays.asList("Gender1","Gender2","Gender3");
    private static final List<String> testUserEmails = Arrays.asList("Email","Email2","Email3");
    private static final List<String> testUserStatuses = Arrays.asList("Status1","Status2","Status3");
        private String name;
        private String gender;
        private String email;
        private String status;

    public User(String name, String gender, String email, String status) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.status = status;
    }

    public Map<String, String> asMap() {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("name",name);
        userMap.put("gender",gender);
        userMap.put("email",email);
        userMap.put("status",status);

        return userMap;

    }

    public String getEmail() {
        return email;
    }

   public static User generateUser() {
       Random rand = new Random();
       String randomUserName = testUserNames.get(rand.nextInt(testUserNames.size()));
       String randomUserGender = testUserGenders.get(rand.nextInt(testUserGenders.size()));
       String randomUserEmail = testUserEmails.get(rand.nextInt(testUserEmails.size()));
       String randomUserStatus = testUserStatuses.get(rand.nextInt(testUserStatuses.size()));

       return new User(randomUserName,randomUserGender,randomUserEmail,randomUserStatus);
   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(gender, user.gender) && Objects.equals(email, user.email) && Objects.equals(status, user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, email, status);
    }
}
