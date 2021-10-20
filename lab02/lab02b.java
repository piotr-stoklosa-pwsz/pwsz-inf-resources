import java.util.ArrayList;
import java.util.Random;

class Student {

    private static final String[] maleNames = {"Kuba", "Milan", "Allan", "Aleksy", "Robert", "Jerzy", "Denis", "Grzegorz", "Dominik", "Alfred", "Florian", "Antoni", "Bronisław", "Fryderyk", "Dominik", "Igor", "Gabriel", "Konrad", "Edward", "Adam"};
    private static final String[] maleSurnames = {"Czarnecki", "Kamiński", "Ostrowski", "Dąbrowski", "Sokołowski", "Kwiatkowski", "Brzeziński", "Chmielewski", "Sawicki", "Jaworski", "Michalak", "Pietrzak", "Krawczyk", "Woźniak", "Nowak", "Mazurek", "Pietrzak", "Krupa", "Michalak", "Wójcik", "Szulc", "Włodarczyk"};
    private static final String[] femaleNames = {"Ewa", "Emilia", "Adriana", "Iga", "Ida", "Magdalena", "Julia", "Jadwiga", "Aisha", "Kamila", "Oksana", "Czesława", "Aleksandra", "Blanka", "Monika", "Urszula", "Otylia", "Malwina"};
    private static final String[] femaleSurnames = {"Urbańska", "Zakrzewska", "Ostrowska", "Kozłowska", "Wasilewska", "Malinowska", "Wojciechowska", "Przybylska", "Tomaszewska", "Głowacka",  "Michalak", "Pietrzak", "Krawczyk", "Woźniak", "Nowak", "Mazurek", "Pietrzak", "Krupa", "Michalak", "Wójcik", "Szulc", "Włodarczyk"};
    private String studentNo;
    private String name;
    private String surname;
    private boolean isAttending;

    public void generatePlaceholderName() {
        Random rand = new Random();
        if(rand.nextBoolean()) { //female
            this.name = this.femaleNames[rand.nextInt(femaleNames.length)];
            this.surname = this.femaleSurnames[
                rand.nextInt(femaleSurnames.length)];
        } else { //male
            this.name = this.maleNames[rand.nextInt(maleNames.length)];
            this.surname = this.maleSurnames[
                rand.nextInt(maleSurnames.length)];
        }
    }

    public void setFullName(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentNo() {
        return this.studentNo;
    }

    public void setStudentStatus(boolean isAttending) {
        this.isAttending = isAttending;
    }

    public boolean getStudentStatus() {
        return this.isAttending;
    }

}

public class lab02b {

    public static final int students_count = 10;
    public static final double activeRatio = 0.8;

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<Student>();

        Random rand = new Random();
        for(int i = 0; i < students_count; i++) {
            Student student = new Student();
            student.setStudentNo(getRandomStudentNumber());
            student.generatePlaceholderName();
            student.setStudentStatus(rand.nextDouble() <= activeRatio);
            students.add(student);
        }

        System.out.println("Students group have been filled.");

        for(int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if(student.getStudentStatus())
                System.out.println(student.getName() + " " + student.getSurname() + " (" + student.getStudentNo() + ")");
        }
    }

    protected static String getRandomStudentNumber() {
        Random rand = new Random();
        return String.valueOf(rand.nextInt(2000) + 38000);
    }

}
