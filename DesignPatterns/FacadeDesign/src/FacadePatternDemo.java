// Sub system classes
class DVDPlayer {
    public void on() {
        System.out.println("DVD Player is ON");
    }

    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }

    public void off() {
        System.out.println("DVD Player is OFF");
    }
}

class Amplifier {
    public void on() {
        System.out.println("Amplifier is ON");
    }

    public void setVolume(int level) {
        System.out.println("Setting volume to " + level);
    }

    public void off() {
        System.out.println("Amplifier is OFF");
    }
}

class Projector {
    public void on() {
        System.out.println("Projector is ON");
    }

    public void wideScreenMode() {
        System.out.println("Projector in widescreen mode");
    }

    public void off() {
        System.out.println("Projector is OFF");
    }
}

class Lights {
    public void dim(int level) {
        System.out.println("Dimming lights to " + level + "%");
    }

    public void on() {
        System.out.println("Lights are ON");
    }
}

// Facade class

class HomeTheaterFacade {
    private DVDPlayer dvdPlayer;
    private Amplifier amplifier;
    private Projector projector;
    private Lights lights;

    public HomeTheaterFacade(DVDPlayer dvd, Amplifier amp, Projector proj, Lights light) {
        this.dvdPlayer = dvd;
        this.amplifier = amp;
        this.projector = proj;
        this.lights = light;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        lights.dim(10);
        projector.on();
        projector.wideScreenMode();
        amplifier.on();
        amplifier.setVolume(5);
        dvdPlayer.on();
        dvdPlayer.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting down movie theater...");
        dvdPlayer.off();
        amplifier.off();
        projector.off();
        lights.on();
    }
}


class FacadePatternDemo {
    public static void main(String[] args) {
        // create sub system objects
        DVDPlayer dvd = new DVDPlayer();
        Amplifier amp = new Amplifier();
        Projector projector = new Projector();
        Lights lights = new Lights();

        //create facade
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(dvd, amp, projector, lights);

        //use facade to perform complex operation
        homeTheater.watchMovie("Inception");
        homeTheater.endMovie();
    }
}