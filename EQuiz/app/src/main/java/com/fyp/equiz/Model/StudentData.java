package com.fyp.equiz.Model;

public class StudentData {
        private String email,password,name, enrollment;

        public StudentData(){

        }

        public StudentData(String email, String password, String name, String enrollment) {
            this.email = email;
            this.password = password;
            this.name = name;
            this.enrollment = enrollment;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEnrollment() {
            return enrollment;
        }

        public void setEnrollment(String enrollment) {
            this.enrollment = enrollment;
        }
}
