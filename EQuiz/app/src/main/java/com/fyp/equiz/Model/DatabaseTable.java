package com.fyp.equiz.Model;

public class DatabaseTable {
    private static final String users_table = "Users";
    private static final String teachers_table = "Teachers";
    private static final String students_table = "Students";
    private static final String classes_table = "Classes";
    private static final String quiz_table = "Quiz";
    private static final String question_table = "Questions";


    public static String getUsers_table() {
        return users_table;
    }

    public static String getTeacher_table() {
        return teachers_table;
    }

    public static String getStudents_table() {
        return students_table;
    }

    public static String getClasses_table() {
        return classes_table;
    }

    public static String getQuiz_table() {
        return quiz_table;
    }

    public static String getQuestion_table() {
        return question_table;
    }
}
