public class RealImage implements Image {

    private String fileName;
    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk(){
        System.out.println("Loading image from disk "+fileName);
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Image loaded from disk "+fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}
