public class Client {
    public static void main(String[] args) {
        File file1 = new File("Resume.pdf");
        File file2 = new File("Photo.png");

        Folder personal = new Folder("Personal");
        personal.add(file1);
        personal.add(file2);

        Folder projects = new Folder("Projects");
        projects.add(new File("Design.docx"));
        projects.add(new File("Code.java"));

        Folder root = new Folder("Root");
        root.add(personal);
        root.add(projects);

        root.showDetails();
    }
}
